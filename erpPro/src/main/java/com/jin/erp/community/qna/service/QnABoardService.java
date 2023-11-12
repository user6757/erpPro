package com.jin.erp.community.qna.service;

import java.util.List;

import com.jin.erp.community.qna.vo.QnABoardVO;

public interface QnABoardService {
	
	public List<QnABoardVO> listBoard();
	
	public int insertBoard(QnABoardVO qnavo);
	
	public int editBoard(QnABoardVO qnavo);
	
	public QnABoardVO detailboard(QnABoardVO qnaVO);
	
	public int deleteBoard(int seq);

}
