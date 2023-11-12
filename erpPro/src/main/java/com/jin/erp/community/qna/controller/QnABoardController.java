package com.jin.erp.community.qna.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jin.erp.HomeController;
import com.jin.erp.community.qna.service.QnABoardService;
import com.jin.erp.community.qna.vo.QnABoardVO;

@Controller
public class QnABoardController {

	
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private QnABoardService qnaBoardService;
	
	@RequestMapping("/qna/list")
	public String qnalist(Model model, QnABoardVO qnavo) {
		List<QnABoardVO> list = qnaBoardService.listBoard();
		System.out.println("확인");
		System.out.println("리스트확인");
		model.addAttribute("qna", list);
		return "community/qna/qnaBoardlist";
	}
	
	@RequestMapping("/qna/write")
	public String qnawrite() {
		return "community/qnaBoard/freeBoardwrite";
	}
	
	@RequestMapping("/qna/save")
	public String qnasave(QnABoardVO qnavo) {
		qnaBoardService.insertBoard(qnavo);
		return "redirect:/free/list";
	}
	
	@RequestMapping("/qna/update")
	public String freeupdate(QnABoardVO freevo) {
		int result = qnaBoardService.editBoard(freevo);
		if(result > 0) {
			return "redirect:/free/list";
		}else {
			return "community/qnaBoard/qnaBoardedit";
		}
	}
	
	@RequestMapping("/qna/editpage")
	public String qnaeditpage(int seq, Model model) {
//		QnABoardVO dbqna = new QnABoardVO();
//		dbqna.setSeq(seq);
//		model.addAttribute("free", freevo);
		return "community/freeBoard/freeBoardedit";
	}
	
	@RequestMapping("/qna/delete")
	public String qnadelete(int seq) {
//		if(result > 0) {
//			return "redirect:/free/list";
//		}else {
//			return "community/freeBoard/freeBoardedit";
//		}
		return "";
	}
}
