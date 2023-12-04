package com.jin.erp.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.erp.login.dao.LoginDAO;
import com.jin.erp.member.dto.LoginDTO;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDAO loginDAO;
	
	public boolean loginflag(LoginDTO loginDTO) {
		int result = loginDAO.loginfalg(loginDTO);
		if(result ==1) {
			return true;
		}
		return false;
	}
	
	public String getname(String name) {
		return loginDAO.getname(name);
	}

}
