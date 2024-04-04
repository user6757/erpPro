package com.jin.erp.admin.loginservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.erp.admin.loginVO.AdminLoginVO;
import com.jin.erp.admin.logindao.AdminLoginDAO;

@Service
public class AdminLoginServiceImpl implements AdminLoginService{
	
	private AdminLoginDAO adminLoginDAO;
	
	@Autowired public AdminLoginServiceImpl(AdminLoginDAO adminLoginDAO) {
		this.adminLoginDAO = adminLoginDAO;
	}
	
	public AdminLoginVO adminsignIn(String adminId) {
		return adminLoginDAO.adminsignIn(adminId);
	}
	
}
