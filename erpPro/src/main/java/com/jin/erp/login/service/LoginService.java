package com.jin.erp.login.service;

import com.jin.erp.member.dto.LoginDTO;

public interface LoginService {
	
	public boolean loginflag(LoginDTO loginDTO)throws Exception;
	public String getname(String name);
	
}
