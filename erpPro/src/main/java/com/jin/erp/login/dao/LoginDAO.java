package com.jin.erp.login.dao;

import com.jin.erp.member.dto.LoginDTO;

public interface LoginDAO {
	
	public int loginfalg(LoginDTO loginDTO);
	public String getname(String name);

}
