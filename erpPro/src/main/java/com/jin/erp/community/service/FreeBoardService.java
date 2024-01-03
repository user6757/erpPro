package com.jin.erp.community.service;

import java.util.List;

import com.jin.erp.community.vo.FileVO;
import com.jin.erp.community.vo.FreeBoardVO;
import com.jin.erp.community.vo.Page;

public interface FreeBoardService {
	
	public List<FreeBoardVO> listBoard(FreeBoardVO freevo, Page page);
	
	public int insertBoard(FreeBoardVO freevo);
	
	public int editBoard(FreeBoardVO freevo);
	
	public FreeBoardVO detailboard(FreeBoardVO freeVO);
	
	public int deleteBoard(int seq);
	
	public int freetotal();
	
	public int fileSave(FileVO fileVO);

}
