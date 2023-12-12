package com.jin.erp.mroom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jin.erp.HomeController;

@Controller
public class MrromController {
	
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/mroom/mrlist")
	public String mroomlistpage() {
		return "mroom/mrlist";
	}
	
	@RequestMapping(value="/mroom/mrwrite")
	public String mroomwrite() {
		return "mroom/mrwrite";
	}

}
