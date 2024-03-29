package com.jin.erp.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String signUp(Member member, RedirectAttributes ra) throws Exception{
        boolean flag = memberService.singup(member);
        ra.addFlashAttribute("msg", "reg-success");
        return flag ? "redirect:/member/login" : "redirect:/member/membership";
    }
	
	@RequestMapping("/member/idfind")
    public String idFindPage(){
        return "member/idfind";
    }
	
	@RequestMapping(value="/member/idsearch", method=RequestMethod.POST)
	@ResponseBody
    public ResponseEntity<String> idSearch(Member member, Model model) {
		
		try {
			Member dBSearchmember = memberService.idsearch(member);
			if(dBSearchmember != null) {
				model.addAttribute("member", dBSearchmember);
				return new ResponseEntity<>("success", HttpStatus.OK);
			}else {
				return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>("erorr", HttpStatus.BAD_REQUEST);
		}
		
    }
	

}
