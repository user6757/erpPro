package com.jin.erp.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jin.erp.HomeController;

@Controller
public class AdminMemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value="/admin/login")
	public String adminLoginPage() {
		
		return "admin/admin_login";
	}
	
	

}
