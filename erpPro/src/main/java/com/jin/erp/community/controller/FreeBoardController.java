package com.jin.erp.community.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jin.erp.HomeController;
import com.jin.erp.community.service.FreeBoardServiceImpl;
import com.jin.erp.community.vo.FreeBoardVO;
import com.jin.erp.community.vo.Page;

@Controller
public class FreeBoardController {

	
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private FreeBoardServiceImpl freeBoardService;
	
	@RequestMapping("/free/list")
	public String freelist(Model model, FreeBoardVO freeBoardVO, HttpServletRequest request) {
		System.out.println("�ΰ�");
		Page paging = new Page();

		if(request.getParameter("page") != null) {
			paging.setPage(Integer.parseInt(request.getParameter("page")));
		}

		List<FreeBoardVO> list = freeBoardService.listBoard(freeBoardVO, paging);

		paging.setTotal(freeBoardService.freetotal());

		paging.setMaxpage((int)((double)paging.getTotal()/freeBoardVO.getLimit()+0.99));

		paging.setStartpage(((int)((double)paging.getPage()/freeBoardVO.getLimit()+0.99)-1)*freeBoardVO.getLimit()+1);

		paging.setEndpage(paging.getMaxpage());

		if(paging.getEndpage() > paging.getStartpage()+freeBoardVO.getLimit()-1) {
			paging.setEndpage(freeBoardVO.getStart()+freeBoardVO.getLimit()-1);
		}
		model.addAttribute("free", list);
		model.addAttribute("pm", paging);
		model.addAttribute("search", freeBoardVO);
		return "community/freeBoard/freeBoardlist";
	}
	
	@RequestMapping("/free/write")
	public String freewrite() {
		return "community/freeBoard/freeBoardwrite";
	}
	
	@RequestMapping("/free/save")
	public String freesave(FreeBoardVO freevo) {
		freeBoardService.insertBoard(freevo);
		return "redirect:/free/list";
	}
	
	@RequestMapping("/free/update")
	public String freeupdate(FreeBoardVO freevo) {
		int result = freeBoardService.editBoard(freevo);
		if(result > 0) {
			return "redirect:/free/list";
		}else {
			return "community/freeBoard/freeBoardedit";
		}
	}
	
	@RequestMapping("/free/detail")
	public String freedetail(int seq, Model model) {
		FreeBoardVO dbfree = new FreeBoardVO();
		dbfree.setSeq(seq);
		model.addAttribute("free", freeBoardService.detailboard(dbfree));
		return "community/freeBoard/freeBoarddetail";
	}
	
	@RequestMapping("/free/modify")
	public String freemodify(int seq, Model model) {
		FreeBoardVO dbfree = new FreeBoardVO();
		dbfree.setSeq(seq);
		FreeBoardVO freevo =freeBoardService.detailboard(dbfree);
		model.addAttribute("free", freevo);
		return "community/freeBoard/freeBoardmodify";
	}
	
	@RequestMapping("/free/delete")
	public String freedelete(int seq) {
		int result = freeBoardService.deleteBoard(seq);
		if(result > 0) {
			return "redirect:/free/list";
		}else {
			return "community/freeBoard/freeBoardedit";
		}
	}
}
