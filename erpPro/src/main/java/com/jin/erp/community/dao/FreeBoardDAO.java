package com.jin.erp.community.dao;

import java.util.List;

import com.jin.erp.community.vo.FreeBoardVO;

public interface FreeBoardDAO {
	
	public List<FreeBoardVO> listBoard(FreeBoardVO freevo);
	
	public List<FreeBoardVO> search(FreeBoardVO freevo);
	
	public int insertBoard(FreeBoardVO freeVO);
	
	public int editBoard(FreeBoardVO freeVO);
	
	public FreeBoardVO detailboard(FreeBoardVO freeVO);
	
	public int deleteBoard(int seq);
	
	public int freetotal();

}
