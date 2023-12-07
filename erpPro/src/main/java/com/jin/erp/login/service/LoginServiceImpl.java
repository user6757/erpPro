package com.jin.erp.login.service;

import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.erp.login.dao.LoginDAO;
import com.jin.erp.member.dto.LoginDTO;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDAO loginDAO;
	
	public boolean loginflag(LoginDTO loginDTO) throws Exception{
		MessageDigest pw = MessageDigest.getInstance("SHA-512");
        pw.update(loginDTO.getPassword().getBytes("UTF-8"));
        byte[] getpassword = pw.digest();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<getpassword.length; i++){
            sb.append(Integer.toString((getpassword[i] & 0xff)+ 0x100, 16).substring(1));
        }
        loginDTO.setPassword(sb.toString());
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
