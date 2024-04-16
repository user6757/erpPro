package com.jin.erp.community.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.erp.community.dao.FreeBoardDAO;
import com.jin.erp.community.vo.FileVO;
import com.jin.erp.community.vo.FreeBoardVO;
import com.jin.erp.community.vo.Page;
import com.oreilly.servlet.MultipartRequest;

@Service
public class FreeBoardServiceImpl implements FreeBoardService{
	
	@Autowired
	private FreeBoardDAO freeDAO;
	
	public List<FreeBoardVO> listBoard(FreeBoardVO freevo, Page page) {
		
		freevo.setStart((page.getPage()-1)*freevo.getLimit()+1);
		freevo.setLimit(freevo.getStart()+freevo.getLimit()-1);
		
		if(freevo.getType() != null && freevo.getKeyword() != null) {
			
			List<FreeBoardVO> searchlist = freeDAO.search(freevo);
			
			for(FreeBoardVO freeBoardVO : searchlist) {
				freeBoardVO.setStrRegdate(strdate(freeBoardVO));
			}
			return searchlist;
		}else {
			List<FreeBoardVO> list= freeDAO.listBoard(freevo);
			for(FreeBoardVO freeBoardVO : list) {
				freeBoardVO.setStrRegdate(strdate(freeBoardVO));
			}
			return list;
		}
	}
	
	private String strdate(FreeBoardVO freeBoardVO) {
		SimpleDateFormat setregdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = freeBoardVO.getRegdate();
		return setregdate.format(date);
	}
	
	public int insertBoard(FreeBoardVO freeVO, MultipartRequest multipart) {
		freeVO.setCnt(0);
		freeVO.setTitle(multipart.getParameter("title"));
		freeVO.setWriter(multipart.getParameter("writer"));
		freeVO.setContent(multipart.getParameter("content"));
		return freeDAO.insertBoard(freeVO);
	}
	
	public int editBoard(FreeBoardVO freeVO) {
		return freeDAO.editBoard(freeVO);
	}
	
	public FreeBoardVO detailboard(FreeBoardVO freeBoardVO) throws Exception{
		FreeBoardVO dbfreeboardVO = freeDAO.detailboard(freeBoardVO);
		int result = freeDAO.fileEq(dbfreeboardVO.getSeq());
		if(result >0) {
			FileVO fileVO = freeDAO.filesearch(dbfreeboardVO.getSeq());
			dbfreeboardVO.setFilename(fileVO.getFileName());
		}
		dbfreeboardVO.setStrRegdate(strdate(dbfreeboardVO));
		return dbfreeboardVO;
	}
	
	public int deleteBoard(int seq) {
		return freeDAO.deleteBoard(seq);
	}
	
	public int freetotal() {
		return freeDAO.freetotal();
	}
	
	public FileVO fileSave(FileVO fileVO) {
		if(freeDAO.fileSave(fileVO)==1) {
			fileVO = freeDAO.filesearch(fileVO.getBno());
			return fileVO;
		}else {
			return fileVO;
		}
	}
	
	public FileVO filesearch(int seq)throws NullPointerException{
		return freeDAO.filesearch(seq);
	}

}
