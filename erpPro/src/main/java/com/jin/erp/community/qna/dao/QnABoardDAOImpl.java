package com.jin.erp.community.qna.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jin.erp.community.qna.vo.QnABoardVO;

@Repository
public class QnABoardDAOImpl implements QnABoardDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<QnABoardVO> listBoard() {
		return sqlSession.selectList("qnalist");
	}
	
	public int insertBoard(QnABoardVO qnaVO) {
		return sqlSession.insert("qnainsert", qnaVO);
	}
	
	public int editBoard(QnABoardVO qnaBoardVO) {
		return sqlSession.update("qnaedit", qnaBoardVO);
				
	}
	
	public QnABoardVO detailboard(int qnaNo) {
		return sqlSession.selectOne("qnaone", qnaNo);
				
	}
	
	public int deleteBoard(int seq) {
		return sqlSession.delete("qnadelete", seq);
	}
}
