package com.jin.erp.member.dao;

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
	public int singup(Member member) {
		return sqlSession.insert("singup", member);
	}
	
	@Override
	public Member idsearch(Member member) {
		return sqlSession.selectOne("idsearch", member);
	}
}
