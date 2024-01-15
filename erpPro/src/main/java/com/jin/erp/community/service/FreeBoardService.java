package com.jin.erp.community.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jin.erp.community.vo.FileVO;
import com.jin.erp.community.vo.FreeBoardVO;
import com.jin.erp.community.vo.Page;
import com.oreilly.servlet.MultipartRequest;

public interface FreeBoardService {
	
	public List<FreeBoardVO> listBoard(FreeBoardVO freevo, Page page);
	
	public int insertBoard(FreeBoardVO freevo, MultipartRequest multipart);
	
	public int editBoard(FreeBoardVO freevo);
	
	public FreeBoardVO detailboard(FreeBoardVO freeBoardVO) throws Exception;
	
	public int deleteBoard(int seq);
	
	public int freetotal();
	
	public FileVO fileSave(FileVO fileVO);
	
	public FileVO filesearch(int seq)throws NullPointerException;

}
