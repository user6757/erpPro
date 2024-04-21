package com.jin.erp.community.dao;

import java.util.List;

import com.jin.erp.community.vo.FileVO;
import com.jin.erp.community.vo.FreeBoardVO;

public interface FreeBoardDAO {
	
	public List<FreeBoardVO> listBoard(FreeBoardVO freevo);
	
	public List<FreeBoardVO> search(FreeBoardVO freevo);
	
	public int insertBoard(FreeBoardVO freeVO);
	
	public int editBoard(FreeBoardVO freeVO);
	
	public FreeBoardVO detailboard(FreeBoardVO freeVO)throws Exception;
	
	public int deleteBoard(int seq);
	
	public int freetotal();
	
	public int fileSave(FileVO fileVO);
	
	public FileVO filesearch(int seq)throws NullPointerException;
	
	public int fileEq(int seq);
	
	public int freecntUp(FreeBoardVO freeBoardVO);

}
