package com.jin.erp.community.qna.dao;

import java.util.List;

import com.jin.erp.community.qna.vo.QnABoardVO;

public interface QnABoardDAO {
	
	public List<QnABoardVO> listBoard();
	
	public int insertBoard(QnABoardVO qnaVO);
	
	public int editBoard(QnABoardVO qnaVO);
	
	public QnABoardVO detailboard(int qnaNo);
	
	public int deleteBoard(int seq);

}
