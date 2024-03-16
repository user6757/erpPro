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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jin.erp.HomeController;
import com.jin.erp.community.service.FreeBoardServiceImpl;
import com.jin.erp.community.vo.FileVO;
import com.jin.erp.community.vo.FreeBoardVO;
import com.jin.erp.community.vo.Page;
import com.oreilly.servlet.MultipartRequest;

@Controller
@RequestMapping("/free/*")
public class FreeBoardController {

private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private FreeBoardServiceImpl freeBoardService;
	
	
	@RequestMapping(value="list", method= {RequestMethod.POST, RequestMethod.GET})
	public String freelist(Model model, FreeBoardVO freeBoardVO, HttpServletRequest request) {
		System.out.println("ddsasdsa");
		System.out.println("ssss");
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
	
	@RequestMapping("write")
	public String freewritePage() {
		return "community/freeBoard/freeBoardwrite";
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String freesave(FreeBoardVO freeBoardVO, Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) throws Exception{
		String savefolder=request.getRealPath("/resources/upload");
		int fileSize = 5*1024*1024;
		MultipartRequest multi = new MultipartRequest(request, savefolder, fileSize, "UTF-8");
		File upFile = multi.getFile("files");
		
		freeBoardService.insertBoard(freeBoardVO, multi);
		
		Page paging = new Page();
		List<FreeBoardVO> list = freeBoardService.listBoard(freeBoardVO, paging);
		
		for(FreeBoardVO freevo : list) {
			redirectAttributes.addAttribute("seq", freevo.getSeq());
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
				fileVO.setBno(freevo.getSeq());
				FileVO dBfileVO = freeBoardService.fileSave(fileVO);
				freevo.setBno(dBfileVO.getBno());
				freevo.setFilename(dBfileVO.getFileName());
			}
			return "redirect:/free/detail";
		}
		return "community/freeBoard/freeBoardwrite";
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String freeupdate(FreeBoardVO freevo) {
		int result = freeBoardService.editBoard(freevo);
		if(result > 0) {
			return "redirect:/free/detail?seq="+freevo.getSeq();
		}else {
			return "community/freeBoard/freeBoardedit";
		}
	}
	
	@RequestMapping(value="detail", method= {RequestMethod.POST, RequestMethod.GET})
	public String freefiledetail(@RequestParam int seq, Model model) throws Exception{
		FreeBoardVO pathfreeBoardVO = new FreeBoardVO();
		pathfreeBoardVO.setSeq(seq);
		FreeBoardVO freeBoardVO = freeBoardService.detailboard(pathfreeBoardVO);
		model.addAttribute("free", freeBoardVO);
		return "community/freeBoard/freeBoarddetail";
	}
	
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public String freemodify(FreeBoardVO freeBordVO, Model model) throws Exception{
		FreeBoardVO freevo =freeBoardService.detailboard(freeBordVO);
		model.addAttribute("free", freevo);
		return "community/freeBoard/freeBoardmodify";
	}
	
	@RequestMapping(value="delete", method= {RequestMethod.POST, RequestMethod.GET})
	public String freedelete(int seq) {
		return freeBoardService.deleteBoard(seq) ==1 ? "redirect:/free/list" : "community/freeBoard/freeBoardedit";
	}
}
