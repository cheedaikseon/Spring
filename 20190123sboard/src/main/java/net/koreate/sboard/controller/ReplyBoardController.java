package net.koreate.sboard.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.sboard.service.BoardService;
import net.koreate.sboard.util.PageMaker;
import net.koreate.sboard.util.SearchCriteria;
import net.koreate.sboard.vo.ReplyBoardVO;

@Controller
@RequestMapping("/sboard/*")
public class ReplyBoardController {
	
	@Inject
	BoardService service;	
	
	@GetMapping("/register")
	public String registerGET() {
		return "/sboard/register";
	}
	
	@PostMapping("/register")
	public String registerPOST(ReplyBoardVO board) throws Exception {
		System.out.println("register post : " + board);
		
		service.registReply(board);
		return "redirect:/sboard/listReply";
	}
	
	@GetMapping("/listReply")
	public String boardList(
			@ModelAttribute("cri") SearchCriteria cri,
			Model model) throws Exception{
		System.out.println("listReply cri : " + cri);
		List<ReplyBoardVO> list = service.listReplyCriteria(cri);
		
		PageMaker pageMaker = service.getPageMaker(cri);
		System.out.println(pageMaker);
		model.addAttribute("pageMaker",pageMaker);
		model.addAttribute("list",list);
		
		return "/sboard/listReply";
	}
	
	@GetMapping("/readPage")
	public String readPage(
			@RequestParam("bno") int bno,
			RedirectAttributes rttr) throws Exception{
		// 죄회수 증가
		service.updateCnt(bno);
		rttr.addAttribute("bno",bno);
		
		//return "redirect:/sboard/read?bno="+bno;
		
		return "redirect:/sboard/read";
	}
	
	@GetMapping("/read")
	public String read(@RequestParam("bno") int bno,Model model) throws Exception{
		// 게시물 정보 획득 후 view에 전달
		ReplyBoardVO board = service.readReply(bno);
		model.addAttribute("boardVo",board);
		return "/sboard/readPage";
	}
	
	@GetMapping("/replyRegister")
	public String replyRegister(@RequestParam("bno") int bno, Model model) throws Exception{
		model.addAttribute("boardVo",service.readReply(bno));
		return "/sboard/replyRegister";
	}
	
	@PostMapping("/replyRegister")
	public String replyRegister(ReplyBoardVO board) throws Exception{
		service.replyRegister(board);
		return "redirect:/sboard/listReply";
	}
	
	@GetMapping("/modifyPage")
	public String modifyPage(@RequestParam("bno") int bno, Model model) 
	throws Exception {
		model.addAttribute("boardVo",service.readReply(bno));
		return "/sboard/modifyPage";
	}
	
	@PostMapping("/modifyPage")
	public String modifyPage(ReplyBoardVO board) throws Exception{
		System.out.println("modify : " + board);
		service.modify(board);
		return "redirect:/sboard/listReply";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") int bno) throws Exception{
		service.remove(bno);
		return "redirect:/sboard/listReply";
	}
	
	@GetMapping("/getAttach/{bno}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("bno") int bno) throws Exception{
		return service.getAttach(bno);
	}
	
	
}
