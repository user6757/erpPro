package com.jin.erp.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@EnableAsync
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private MemberService memberService;
	
	@Autowired 
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping(value="/member/membership", method= {RequestMethod.POST, RequestMethod.GET})
	public void memberShip() {
	}
	
	@PostMapping(value="/member/idcheck")
	@ResponseBody
	public String idcheck(String account) {
		return Integer.toString(memberService.idcheck(account));
	}
	
	@PostMapping(value="/member/signup")
    public String signUp(Member member, HttpServletRequest request,
    		RedirectAttributes redirect, Model model, Exception e) throws Exception{
		try {
			boolean signUpResult = memberService.singup(member);
	        if(signUpResult) {
	        	redirect.addFlashAttribute("msge", "reg-success");
	        	return "redirect:/member/login";
	        }else {
	        	redirect.addFlashAttribute("msge", "reg-error");
	        	return "member/membership";
	        }
		}catch(Exception exception) {
			exception.printStackTrace();
			redirect.addFlashAttribute("msge", "reg-error");
			return "redirect:/member/membership";
		}
        
    }
	
	@RequestMapping(value="/member/idfind", method= RequestMethod.GET)
    public void idFindPage(){
    }
	
	@RequestMapping(value="/member/passwordfind", method= RequestMethod.GET)
    public void passwordFindPage(){
    }
	
	@RequestMapping(value="/member/searchfind_ok", method= RequestMethod.POST)
	public String passwordFindok(@RequestParam String account, String email, RedirectAttributes redirect) {
		Map<String, Object> message = memberService.passwordfind(account, email);
		redirect.addFlashAttribute("password", message.get("message"));
		return "redirect:/member/joinfind_ok";
	}
	
	@RequestMapping(value="/member/joinfind_ok", method= RequestMethod.GET)
	public String passwordFindok(@ModelAttribute("password") String password) {
		return "/member/passwordfind";
	}
	
	@RequestMapping(value="/member/idsearch", method=RequestMethod.POST)
    public ResponseEntity<String> idSearch(Member member, Model model) {
		try {
			Member dBSearchmember = memberService.idsearch(member);
			if(dBSearchmember != null) {
				return new ResponseEntity<>(dBSearchmember.getAccount(), HttpStatus.OK);
			}else {
				return new ResponseEntity<>("N", HttpStatus.OK);
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>("erorr", HttpStatus.BAD_REQUEST);
		}
    }
	

}
