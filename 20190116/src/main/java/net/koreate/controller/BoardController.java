package net.koreate.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.service.BoardService;
import net.koreate.util.Criteria;
import net.koreate.util.PageMaker;
import net.koreate.vo.BoardVO;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	BoardService service;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void registerGet() {
	}
	
	/*
	HttpServletRequest request
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String writer = request.getParameter("writer");
	
	BoardVO board1 = new BoardVO();
	board1.setTitle(title);
	board1.setContent(content);
	board1.setWriter(writer);
	System.out.println(title);
	System.out.println(board1);
	*/
	/*@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPOST(BoardVO boardVo, Model model) {
		
		System.out.println("글작성 요청");
		System.out.println(boardVo);
		
		String msg = service.register(boardVo);
		model.addAttribute("result",msg);
		System.out.println("게시물 등록 완료");
		return "board/success";
	}*/
	
	@PostMapping("/register")
	public String registerPOST(BoardVO boardVo, RedirectAttributes rttr) {
		String msg = service.register(boardVo);
		rttr.addFlashAttribute("result",msg);
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="/listAll",method=RequestMethod.GET)
	public String listAll(Model model) {		
		List<BoardVO> boardList =service.listAll();
		model.addAttribute("boardList",boardList);
		return "/board/listAll";
	}
	
	/*	@PostMapping("/register")
	public String registerPOST(BoardVO boardVo, HttpServletRequest request) {
		String msg = service.register(boardVo);
		HttpSession session = request.getSession();
		session.setAttribute("result", msg);
		return "redirect:/board/listAll";
	}*/
	
	/*@RequestMapping(value="/listAll",method=RequestMethod.GET)
	public String listAll(Model model,HttpServletRequest request) {		
		List<BoardVO> boardList =service.listAll();
		model.addAttribute("boardList",boardList);
		HttpSession session = request.getSession();
		String msg = (String)session.getAttribute("result");
		System.out.println("listAll msg : " + msg);
		model.addAttribute("result",msg);
		session.removeAttribute("result");
		return "/board/listAll";
	}*/
	
	//@RequestMapping(value="/read", method=RequestMethod.GET)
	@GetMapping("/read")
	public String read(@RequestParam("bno") int bno, Model model) {
		System.out.println("bno : " + bno);
		model.addAttribute("board",service.read(bno));
		return "/board/read";
	}
	
	
	@GetMapping("/modify")
	public String modify(@RequestParam("bno") int bno,Model model) {
		System.out.println("게시물 수정 요청");
		BoardVO board = service.read(bno);
		model.addAttribute("board",board);
		return "board/modify";
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO boardVo, RedirectAttributes rttr) {
		System.out.println(boardVo);
		String msg = service.modify(boardVo);
		rttr.addFlashAttribute("result",msg);
		return "redirect:/board/read?bno="+boardVo.getBno();
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) {
		System.out.println("삭제요청 : " + bno);
		String msg = service.remove(bno);
		rttr.addFlashAttribute("result",msg);
		return "redirect:/board/listAll";
	}
	
	@GetMapping("/listCri")
	public String listCri(Criteria cri, Model model) {
		System.out.println("listCri 호출");
		List<BoardVO> list = service.listCri(cri);
		model.addAttribute("boardList",list);
		return "board/listAll";
	}
	
	@GetMapping("/listPage")
	public String listPage(Criteria cri, Model model) {
		model.addAttribute("boardList",service.listCri(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.totalCount());
		System.out.println("startPage : " + pageMaker.getStartPage());
		System.out.println("endPage : " + pageMaker.getEndPage());
		model.addAttribute(pageMaker);
		
		return "board/listPage";
	}
	
	
	@GetMapping("/readPage")
	public String readPage(
			@RequestParam("page")int page, 
			@RequestParam("bno") int bno,
			RedirectAttributes rttr) {
		
		service.updateViewCnt(bno);
		rttr.addAttribute("bno",bno);
		rttr.addAttribute("page",page);
		return "redirect:/board/readDetail";
	}
	
	@GetMapping("/readDetail")
	public String readDetail(
			Model model, 
			@ModelAttribute("bno") int bno, 
			@ModelAttribute("page") int page) {
		
		BoardVO board = service.read(bno);
		model.addAttribute("board",board);
		//model.addAttribute("page",page);
		return "board/readPage";
	}
	
	

}
