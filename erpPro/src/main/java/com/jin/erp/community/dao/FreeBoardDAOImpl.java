package com.jin.erp.community.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jin.erp.community.vo.FileVO;
import com.jin.erp.community.vo.FreeBoardVO;

@Repository
public class FreeBoardDAOImpl implements FreeBoardDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<FreeBoardVO> listBoard(FreeBoardVO freevo) {
		return sqlSession.selectList("freelist", freevo);
	}
	
	@Override
	public List<FreeBoardVO> search(FreeBoardVO freevo){
		return sqlSession.selectList("freesearch", freevo);
	}
	
	@Override
	public int insertBoard(FreeBoardVO freeVO) {
		return sqlSession.insert("freeinsert", freeVO);
	}
	
	@Override
	public int editBoard(FreeBoardVO freeVO) {
		return sqlSession.update("freeedit", freeVO);
				
	}
	
	@Override
	public FreeBoardVO detailboard(FreeBoardVO freeVO) throws Exception{
		return sqlSession.selectOne("freeone", freeVO);
				
	}
	
	@Override
	public int deleteBoard(int seq) {
		return sqlSession.delete("freedelete", seq);
	}
	
	@Override
	public int freetotal() {
		return sqlSession.selectOne("freetotal");
	}
	
	@Override
	public int fileSave(FileVO fileVO) {
		return sqlSession.insert("filesave", fileVO);
	}
	
	@Override
	public FileVO filesearch(int seq)throws NullPointerException{
		return sqlSession.selectOne("filesearch", seq);
	}
	
	@Override
	public int fileEq(int seq) {
		return sqlSession.selectOne("fileEq", seq);
	}
	
	@Override
	public int freecntUp(FreeBoardVO freeBoardVO) {
		return sqlSession.update("freeDetail_cntup", freeBoardVO);
	}
}
