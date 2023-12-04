package com.jin.erp.login.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jin.erp.member.dto.LoginDTO;

@Repository
public class LoginDAOImpl implements LoginDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	public int loginfalg(LoginDTO loginDTO) {
		return sqlSession.selectOne("loginflag", loginDTO);
	}
	
	public String getname(String name) {
		return sqlSession.selectOne("getname", name);
	}

}
