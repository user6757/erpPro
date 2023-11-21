package com.jin.erp.community.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.erp.community.dao.FreeBoardDAO;
import com.jin.erp.community.vo.FreeBoardVO;
import com.jin.erp.community.vo.Page;

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
	
	public int insertBoard(FreeBoardVO freeVO) {
		freeVO.setCnt(0);
		return freeDAO.insertBoard(freeVO);
	}
	
	public int editBoard(FreeBoardVO freeVO) {
		return freeDAO.editBoard(freeVO);
	}
	
	public FreeBoardVO detailboard(FreeBoardVO freeVO) {
		return freeDAO.detailboard(freeVO);
	}
	
	public int deleteBoard(int seq) {
		return freeDAO.deleteBoard(seq);
	}
	
	public int freetotal() {
		return freeDAO.freetotal();
	}

}
