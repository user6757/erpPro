package com.jin.erp.community.controller;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jin.erp.HomeController;
import com.jin.erp.community.service.FreeBoardServiceImpl;
import com.jin.erp.community.vo.FileVO;
import com.jin.erp.community.vo.FreeBoardVO;
import com.jin.erp.community.vo.Page;
import com.oreilly.servlet.MultipartRequest;

@Controller
public class FreeBoardController {

	
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private FreeBoardServiceImpl freeBoardService;
	
	@RequestMapping("/free/list")
	public String freelist(Model model, FreeBoardVO freeBoardVO, HttpServletRequest request) {
		
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
	
	@RequestMapping(value="/free/save", method=RequestMethod.POST)
	public String freesave(FreeBoardVO freeBoardVO, HttpServletRequest request) throws Exception{
		String savefolder=request.getRealPath("/resources/upload");
		System.out.println("ÆÄÀÏ:"+ savefolder);
		int fileSize = 5*1024*1024;
		MultipartRequest multi = new MultipartRequest(request,savefolder,fileSize, "UTF-8");
		File upFile = multi.getFile("files");
		
		if(upFile != null) {
			String fileName = upFile.getName();
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH)+1;
			int date = cal.get(Calendar.DATE);
			String homedir = savefolder+"/"+year+"-"+month+"-"+date;
			File path = new File(homedir);
			if(!path.exists()) {
				path.mkdir();
			}
			
			Random ran = new Random();
			int random = ran.nextInt(1000000000);
			
			int index = fileName.lastIndexOf(".");
			
			String fileException = fileName.substring(index+1);
			
			String refileName = "member"+year+month+date+random+"."+fileException;
			String fileDBName = "/"+year+"-"+month+"-"+date+"/"+refileName;
			upFile.renameTo(new File(homedir+"/"+refileName));
			FileVO fileVO = new FileVO();
			fileVO.setFileName(fileDBName);
			fileVO.setBno(freeBoardVO.getSeq());
		}
		freeBoardService.insertBoard(freeBoardVO);
		Page paging = new Page();

		if(request.getParameter("page") != null) {
			paging.setPage(Integer.parseInt(request.getParameter("page")));
		}

		List<FreeBoardVO> list = freeBoardService.listBoard(freeBoardVO, paging);
		for(FreeBoardVO freevo : list) {
			return "redirect:/free/detail?seq="+freevo.getSeq();
		}
		return "community/freeBoard/freeBoardwrite";
	}
	
	@RequestMapping("/free/update")
	public String freeupdate(FreeBoardVO freevo) {
		int result = freeBoardService.editBoard(freevo);
		if(result > 0) {
			return "redirect:/free/detail?seq="+freevo.getSeq();
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
