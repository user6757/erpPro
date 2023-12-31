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

import com.jin.erp.community.vo.FreeBoardVO;
import com.jin.erp.login.service.LoginService;
import com.jin.erp.member.dto.LoginDTO;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	// 로그인 화면을 열어주는 요청처리
    @GetMapping("/member/login")
    public void signIn(@ModelAttribute("message") String message, HttpServletRequest request, Model model) {
        // 요청 정보 헤더 안에는 Referer라는 키가 있는데
        // 여기 안에는 이 페이지로 진입할 때 어디에서 왔는지 URI정보가 들어있음.
        String referer = request.getHeader("Referer");
        request.getSession().setAttribute("redirectURI", referer);
    }
    
    // 로그인 화면을 열어주는 요청처리
    @PostMapping("/member/login")
    public String login(LoginDTO loginDTO,
    		HttpServletRequest request,
    		Model model,
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
