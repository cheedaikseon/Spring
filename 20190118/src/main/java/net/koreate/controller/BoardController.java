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
import net.koreate.vo.BoardVO;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	BoardService service;
	
	@GetMapping("/register")
	public String register() {
		return "/board/register";
	}
	
	@PostMapping("/register")
	public String registerPOST(BoardVO boardVo, RedirectAttributes rttr) {
		String msg = service.register(boardVo);
		rttr.addFlashAttribute("result",msg);
		return "redirect:/board/listAll";
	}
	
	@GetMapping("/listAll")
	public String listAll(Model model) {		
		List<BoardVO> boardList =service.listAll();
		model.addAttribute("boardList",boardList);
		return "/board/listAll";
	}
	
	@GetMapping("/read")
	public String read(@RequestParam("bno") int bno, Model model) {
		model.addAttribute("board",service.read(bno));
		return "/board/read";
	}
	@GetMapping("modify")
	public String modify(@RequestParam("bno") int bno, Model model) {
		System.out.println("수정 요청");
		BoardVO board = service.read(bno);
		model.addAttribute("board",board);
		return "board/modify";
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO boardvo, RedirectAttributes rttr) {
		String msg = service.modify(boardvo);
		rttr.addFlashAttribute("result",msg);
		return "redirect:/board/read?bno="+boardvo.getBno();
		
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) {
		String msg = service.remove(bno);
		rttr.addFlashAttribute("result",msg);
		return "redirect:/board/listAll";
	}
	
	
}
