package com.jin.erp.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jin.erp.HomeController;
import com.jin.erp.member.domain.Member;
import com.jin.erp.member.service.MemberService;

@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/member/memberpage")
	public String memberpage() {
		return "member/membership";
	}
	
	@PostMapping(value="/member/idcheck")
	@ResponseBody
	public boolean idcheck(String account) {
		return memberService.idcheck(account)== 1? true : false;
	}
	
	@PostMapping("/member/signup")
    public String signUp(Member member, RedirectAttributes ra) {
        boolean flag = memberService.singup(member);
        ra.addFlashAttribute("msg", "reg-success");
        return flag ? "redirect:/member/login" : "redirect:/member/membership";
    }
	

}
