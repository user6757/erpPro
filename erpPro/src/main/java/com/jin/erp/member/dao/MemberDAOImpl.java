package com.jin.erp.member.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jin.erp.member.domain.Member;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int idcheck(String account) {
		return sqlSession.selectOne("idcheck", account);
	}
	
	@Override
	public int singup(Member member) throws SQLException{
		return sqlSession.insert("member_singup", member);
	}
	
	@Override
	public Member idsearch(Member member) {
		return sqlSession.selectOne("idsearch", member);
	}
	
	@Override
	public int passwordfind(Member member) {
		return sqlSession.selectOne("password_find", member);
	}
	
	@Override
	public int setpassword(int password) {
		return sqlSession.update("passowrd_set", password);
	}
}
