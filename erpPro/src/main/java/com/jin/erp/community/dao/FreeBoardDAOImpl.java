package com.jin.erp.community.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jin.erp.community.vo.FreeBoardVO;

@Repository
public class FreeBoardDAOImpl implements FreeBoardDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<FreeBoardVO> listBoard(FreeBoardVO freevo) {
		return sqlSession.selectList("freelist", freevo);
	}
	
	public List<FreeBoardVO> search(FreeBoardVO freevo){
		return sqlSession.selectList("freesearch", freevo);
	}
	
	public int insertBoard(FreeBoardVO freeVO) {
		return sqlSession.insert("freeinsert", freeVO);
	}
	
	public int editBoard(FreeBoardVO freeVO) {
		return sqlSession.update("freeedit", freeVO);
				
	}
	
	public FreeBoardVO detailboard(FreeBoardVO freeVO) {
		return sqlSession.selectOne("freeone", freeVO);
				
	}
	
	public int deleteBoard(int seq) {
		return sqlSession.delete("freedelete", seq);
	}
	
	public int freetotal() {
		return sqlSession.selectOne("freetotal");
	}
}
