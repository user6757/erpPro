package com.jin.erp.mroom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jin.erp.HomeController;
import com.jin.erp.mroom.service.MroomService;
import com.jin.erp.mroom.vo.MroomVO;

@Controller
public class MroomController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private MroomService mroomService;
	
	@RequestMapping(value="/mroom/mrlist", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView mroomlist(MroomVO mroomVO) {
		List<MroomVO> mroomlist = mroomService.mrview(mroomVO);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("mroom/mrlist");
		mv.addObject("mlist", mroomlist);
		return mv;
	}
	
	@RequestMapping(value="/mroom/mrwrite")
	public String mroomwrite() {
		return "mroom/mrwrite";
	}
	
	@RequestMapping(value="/mroom/mrsave", method=RequestMethod.POST)
	public String mroomSave(MroomVO mroomVO) {
		return mroomService.mroomSave(mroomVO)==1 ? "redirect:/mroom/mrlist":"mroom/mrwrite";
	}

}
