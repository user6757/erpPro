package com.jin.erp.community.qna.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jin.erp.community.qna.service.QnABoardService;
import com.jin.erp.community.qna.vo.QnABoardVO;

@Controller
public class QnABoardController {

	private static final Logger logger = LoggerFactory.getLogger(QnABoardController.class);
	@Autowired
	private QnABoardService qnaBoardService;
	
	@RequestMapping(value="/qna/list", method= {RequestMethod.POST, RequestMethod.GET})
	public String qnalist(Model model, QnABoardVO qnavo) {
		List<QnABoardVO> list = qnaBoardService.listBoard();
		model.addAttribute("qna", list);
		return "community/qnaBoard/qnaBoardlist";
	}
	
	@RequestMapping(value="/qna/write", method= RequestMethod.GET)
	public String qnawrite() {
		return "community/qnaBoard/qnaBoardwrite";
	}
	
	@RequestMapping(value="/qna/save", method= RequestMethod.POST)
	public String qnasave(QnABoardVO qnaBoardVO) {
		int result = qnaBoardService.insertBoard(qnaBoardVO);
		return result == 1 ? "redirect:/qna/qnadetail?qnano="+ qnaBoardService.qnalistGetnum() +" ":"redirect:/qna/wrwite";
	}
	
	@RequestMapping(value="/qna/qnadetail", method= {RequestMethod.POST, RequestMethod.GET})
	public String qnaDetail(int qnano, Model model) {
		QnABoardVO qnaBoardVO = qnaBoardService.detailboard(qnano);
		model.addAttribute("qna", qnaBoardVO);
		return "community/qnaBoard/qnaBoarddetail";
	}
	
	@RequestMapping(value="/qna/update", method= RequestMethod.POST)
	public String qnaupdate(QnABoardVO qnaBoardVO, Model model) {
		int resultNum = qnaBoardService.editBoard(qnaBoardVO);
		if(resultNum > 0) {
			
			return "redirect:/qna/qnadetail?qnano="+resultNum;
		}else {
			return "redirect:/qna/list";
		}
	}
	
	@RequestMapping(value="/qna/editpage", method= {RequestMethod.POST, RequestMethod.GET})
	public String qnaeditpage(int qnano, Model model) {
		model.addAttribute("qna", qnaBoardService.detailboard(qnano));
		return "community/qnaBoard/qnaBoardmodify";
	}
	
	@RequestMapping(value="/qna/delete", method= {RequestMethod.POST, RequestMethod.GET})
	public String qnadelete(int seq) {
		return qnaBoardService.deleteBoard(seq) == 1? "redirect:/qna/list":"community/qnaBoard/qnaBoarddetail";
	}
}
