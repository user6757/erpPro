package com.jin.erp.admin;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jin.erp.HomeController;
import com.jin.erp.admin.loginVO.AdminLoginVO;
import com.jin.erp.admin.loginservice.AdminLoginService;

@Controller
public class AdminMemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private AdminLoginService adminLoginService;
	
	@Autowired
	public AdminMemberController(AdminLoginService adminLoginService) {
		this.adminLoginService = adminLoginService;
	}
	
	@RequestMapping(value="/admin/login")
	public String adminLoginPage() {
		return "admin/admin_login";
	}
	
//	@RequestMapping(value="/admin/login")
//	public String adminLogin(AdminLoginVO adminLoginVO) {
//		
//		return "admin/admin_login";
//	}
	
	@RequestMapping(value="/admin/singnIn", method=RequestMethod.POST)
	@ResponseBody
	public String adminsignIn(String adminId, String adminPw, HttpSession session) {
		AdminLoginVO adminLoginVO = adminLoginService.adminsignIn(adminId);
		if(adminLoginVO == null) {
			return "N";
		}else {
			if(adminLoginVO.getAdminId().equals(adminId)) {
				if(adminLoginVO.getAdminPw().equals(adminPw)) {
					session.setAttribute("adminid", adminLoginVO.getAdminId());
					session.setMaxInactiveInterval(18000);
					return "Y";
				}else {
					return "O";
				}
			}else {
				return "O";
			}
		}
	}
	
	
	
	
	

}
