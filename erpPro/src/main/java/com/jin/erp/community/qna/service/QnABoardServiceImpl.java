package com.jin.erp.community.qna.service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
		List<QnABoardVO> list = qnaDAO.listBoard();
		for(QnABoardVO qnaBoardVO : list) {
			qnaBoardVO.setStrdate(strdate(qnaBoardVO));
		}
		return list;
	}
	
	public int insertBoard(QnABoardVO qnaVO) {
		qnaVO.setQnacnt(0);
		return qnaDAO.insertBoard(qnaVO);
	}
	
	public int editBoard(QnABoardVO qnaVO) {
		if(qnaDAO.editBoard(qnaVO) == 1) {
			System.out.println("여기");
			QnABoardVO dBqnaBoardVO = qnaDAO.detailboard(qnaVO.getQnano());
			if(dBqnaBoardVO.getQnano() != null && dBqnaBoardVO.getQnano() > 0 && dBqnaBoardVO.getQnano() == qnaVO.getQnano()) {
				return dBqnaBoardVO.getQnano();
			}else {
				return 0;
			}
		}else {
			return 0;
		}
		
	}
	
	public QnABoardVO detailboard(int qnaNo) {
		return qnaDAO.detailboard(qnaNo);
	}
	
	public int deleteBoard(int seq) {
		return qnaDAO.deleteBoard(seq);
	}
	
	public int qnalistGetnum() {
		List<QnABoardVO> list = qnaDAO.listBoard();
		for(QnABoardVO qnaboard : list) {
			return qnaboard.getQnano();
		}
		return 0;
	}
	
	private String strdate(QnABoardVO qnaBoardVO) {
		SimpleDateFormat setregdate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = qnaBoardVO.getQnaregdate();
		return setregdate.format(date);
	}

}
