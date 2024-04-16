package com.jin.erp.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jin.erp.HomeController;
import com.jin.erp.member.domain.Member;
import com.jin.erp.member.service.MemberService;

@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/member/membership")
	public void memberShip(String msg, Model model) {
		
		if(msg != null) {
			model.addAttribute("message", msg);
		}
	}
	
	@PostMapping(value="/member/idcheck")
	@ResponseBody
	public String idcheck(String account) {
		return Integer.toString(memberService.idcheck(account));
	}
	
	@PostMapping(value="/member/signup")
	@ExceptionHandler(Exception.class)
    public String signUp(Member member, HttpServletRequest request, RedirectAttributes redirect, Model model, Exception e) throws Exception{
		
        boolean flag = memberService.singup(member);
        if(flag) {
        	redirect.addAttribute("msg", "reg-success");
        	return "redirect:/member/login";
        }else {
        	redirect.addAttribute("msg", "reg-error");
        	return "redirect:/member/membership";
        }
        
    }
	
	@RequestMapping("/member/idfind")
    public void idFindPage(){
    }
	
	@RequestMapping(value="/member/idsearch", method=RequestMethod.POST, produces="application/json")
    public ResponseEntity<Object> idSearch(Member member, Model model) {
		try {
			Member dBSearchmember = memberService.idsearch(member);
			if(dBSearchmember != null) {
				return new ResponseEntity<>(dBSearchmember, HttpStatus.OK);
			}else {
				return new ResponseEntity<>("N", HttpStatus.OK);
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>("erorr", HttpStatus.BAD_REQUEST);
		}
    }
	

}
