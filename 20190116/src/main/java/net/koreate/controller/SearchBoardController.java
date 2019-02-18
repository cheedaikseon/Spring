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
import net.koreate.util.PageMaker;
import net.koreate.util.SearchCriteria;
import net.koreate.vo.BoardVO;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {

	@Inject
	BoardService service;	
	
	
	// 검색 게시물 전체 목록 -> paging 처리
	// /WEB-INF/views/sboard/list.jsp
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(SearchCriteria cri, Model model) {
		// paging 처리된 전체 게시물 목록 model 
		int searchListCount = service.searchListCount(cri);
		System.out.println(searchListCount);
		System.out.println("cri : " +cri);
		
		List<BoardVO> list = service.searchList(cri);
		model.addAttribute("list",list);
		model.addAttribute("cri",cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(searchListCount);
		
		model.addAttribute("maker",pageMaker);
		
		return "/sboard/list";
	}
	
	// 한개의 게시물 정보
	// /WEB-INF/views/sboard/readPage.jsp
	@GetMapping("readPage")
	public String readPage(
			@ModelAttribute("cri") SearchCriteria cri,
			@RequestParam("bno") int bno,
			Model model, RedirectAttributes rttr) {
		// 요청된 한개의 게시물 model
		
		
		/*BoardVO board = service.read(bno);
		model.addAttribute("board",board);*/
		service.updateViewCnt(bno);
		rttr.addAttribute("bno",bno);
		
		return "redirect:readPageDetail";
	}
	@GetMapping("/readPageDetail")
	public String readPageDetail(@ModelAttribute("cri") SearchCriteria cri, @RequestParam("bno") int bno,
			Model model) {
		System.out.println("read detail");
		BoardVO board = service.read(bno);
		model.addAttribute("board",board);
		return "sboard/readPage";
		
	}
	
	
	// 글작성 페이지 요청
	// /WEB-INF/views/sboard/register.jsp
	@GetMapping("register")
	public String register() {
		// 글작성 화면 
		return "sboard/register";
	}
	
	// 글작성 완료
	// browser를 통해 요청을 변경 -> /sboard/list 
	@PostMapping("register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		System.out.println("게시글 등록 요청");
		String msg = service.register(board);
		rttr.addFlashAttribute("result",msg);
		return "redirect:/sboard/list";
	}
	
	// 글 수정창 요청
	// /WEB-INF/views/sboard/modifyPage.jsp
	@GetMapping("modifyPage")
	public String modifyPage(
				@RequestParam("bno") int bno,
				Model model,
				@ModelAttribute("cri") SearchCriteria cri) {
		// 게시물을 수정하기 위해 기존 정보 제공
		BoardVO board = service.read(bno);
		model.addAttribute("board",board);
		return "sboard/modifyPage";
	}
	
	// 글 수정 
	// browser 요청 변경 -> /sboard/readPage 
	@PostMapping("modifyPage")
	public String modifyPage(
			BoardVO board,
			SearchCriteria cri,
			RedirectAttributes rttr) {
		System.out.println("modifyPage : "+board);
		System.out.println("modifyPage : "+cri);
		
		rttr.addAttribute("bno",board.getBno());
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		String msg = service.modify(board);
		rttr.addFlashAttribute("result",msg);
		return "redirect:/sboard/readPage";
	}
	
	// 게시물 삭제
	// 삭제후 browser 요청 변경 -> /sboard/removePage
	@PostMapping("removePage")
	public String removePage(@RequestParam("bno") int bno, SearchCriteria cri, RedirectAttributes rttr) {
		System.out.println("게시물 삭제 요청");
		
		String msg = service.remove(bno);
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		rttr.addFlashAttribute("result",msg);
		
		return "redirect:/sboard/list";
	}
	
}
