package com.jin.erp.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jin.erp.member.dto.LoginDTO;

@Controller
public class LoginController {
	
	
	@RequestMapping("/member/login")
	public String memberpage() {
		return "member/login";
	}
	
	// �α��� ȭ���� �����ִ� ��ûó��
    @GetMapping("/member/login")
    public void signIn(@ModelAttribute("message") String message, HttpServletRequest request, Model model) {
        // ��û ���� ��� �ȿ��� Referer��� Ű�� �ִµ�
        // ���� �ȿ��� �� �������� ������ �� ��𿡼� �Դ��� URI������ �������.
        String referer = request.getHeader("Referer");
        request.getSession().setAttribute("redirectURI", referer);
    }
    
 // �α��� ȭ���� �����ִ� ��ûó��
    @GetMapping("/member/singin")
    public String login(LoginDTO loginDTO, HttpServletRequest request, Model model) {
    	System.out.println("sss"+ loginDTO.getAccount());
        return "member/login";
    }

}
