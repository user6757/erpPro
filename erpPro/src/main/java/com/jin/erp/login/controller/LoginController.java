package com.jin.erp.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jin.erp.community.vo.FreeBoardVO;
import com.jin.erp.login.service.LoginService;
import com.jin.erp.member.dto.LoginDTO;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
    @GetMapping(value="/member/login")
    public void login(@ModelAttribute("message") String message, HttpServletRequest request) {
    	System.out.println("ㅈㅈㅈ"+ message);
    	String referer = request.getHeader("Referer");
    	request.getSession().setAttribute("redirectURI", referer);
    }
    
    // 로그인
    @RequestMapping(value="/member/login", method= RequestMethod.POST)
    public String login(LoginDTO loginDTO, HttpServletRequest request,
    		HttpSession session) throws Exception{
    	boolean loginflag = loginService.loginflag(loginDTO);
    	if(loginflag==true) {
    		session.setMaxInactiveInterval(18000);
    		session.setAttribute("userid", loginService.getname(loginDTO.getAccount()));
    		return "index";
    	}
        return "member/login";
    }
    
    @RequestMapping(value="/member/sign-out")
	public String logout(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	if(session != null) {
    		session.invalidate();
    	}
		return "index";
	}

}
