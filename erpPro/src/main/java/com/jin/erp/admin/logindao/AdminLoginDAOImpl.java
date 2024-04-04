package com.jin.erp.admin.logindao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jin.erp.admin.loginVO.AdminLoginVO;

@Repository
public class AdminLoginDAOImpl implements AdminLoginDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	public AdminLoginVO adminsignIn(String adminId) {
		return sqlSession.selectOne("adminsignIn", adminId);
	}
	
}
