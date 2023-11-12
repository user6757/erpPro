package com.jin.erp.community.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.erp.community.qna.dao.QnABoardDAO;
import com.jin.erp.community.qna.vo.QnABoardVO;

@Service
public class QnABoardServiceImpl implements QnABoardService{
	
	@Autowired
	private QnABoardDAO qnaDAO;
	
	public List<QnABoardVO> listBoard() {
		return qnaDAO.listBoard();
	}
	
	public int insertBoard(QnABoardVO qnaVO) {
		qnaVO.setQnacnt(0);
		return qnaDAO.insertBoard(qnaVO);
	}
	
	public int editBoard(QnABoardVO qnaVO) {
		return qnaDAO.editBoard(qnaVO);
	}
	
	public QnABoardVO detailboard(QnABoardVO qnaVO) {
		return qnaDAO.detailboard(qnaVO);
	}
	
	public int deleteBoard(int seq) {
		return qnaDAO.deleteBoard(seq);
	}

}
