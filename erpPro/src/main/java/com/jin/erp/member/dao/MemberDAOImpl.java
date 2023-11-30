package com.jin.erp.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jin.erp.member.domain.Member;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	public int idcheck(String account) {
		return sqlSession.selectOne("idcheck", account);
	}
	
	public int singup(Member member) {
		return sqlSession.insert("singup", member);
	}
}
