package net.koreate.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.service.BoardService;
import net.koreate.util.Criteria;
import net.koreate.util.PageMaker;
import net.koreate.vo.BoardVo;


@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public void registerGET(BoardVo board,Model model) throws Exception{
		System.out.println("레지스터 GET 방식 호출");
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerPOST(BoardVo board,RedirectAttributes rttr) throws Exception{
		System.out.println("레지스터 POST 방식 호출");
		System.out.println(board.toString());
		String result = service.regist(board);
		rttr.addFlashAttribute("result",result);
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value="/read",method=RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception{
		model.addAttribute("boardVO",service.read(bno));
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno, Model model) throws Exception{
		model.addAttribute("boardVO",service.read(bno));
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modifyPOST(BoardVo boardVO, RedirectAttributes rttr) throws Exception{
		service.modify(boardVO);
		rttr.addFlashAttribute("result","SUCCESS");
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception{
		service.remove(bno);
		rttr.addFlashAttribute("result","SUCCESS");
		return "redirect:/board/listAll";
	}


	@RequestMapping(value="/readPage",method=RequestMethod.GET)
	public String readPage(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri,RedirectAttributes rttr) throws Exception{
		rttr.addAttribute("bno",bno);
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		service.updateCnt(bno);
		System.out.println("update");
		return "redirect:/board/readUpdate";
	}
	
	@RequestMapping(value="/readUpdate",method=RequestMethod.GET)
	public String readUpdate(@ModelAttribute("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		System.out.println(cri);
		System.out.println("bno : "+bno);
		model.addAttribute("boardVO",service.read(bno));
		return "/board/readPage";
	}
	
	
	@RequestMapping(value="/removePage",method=RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, Criteria cri, RedirectAttributes rttr) throws Exception{
		service.remove(bno);
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addFlashAttribute("msg","SUCCESS");
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value="/modifyPage",method=RequestMethod.GET)
	public void modifyGET(int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		model.addAttribute("boardVO",service.read(bno));
	}
	
	@RequestMapping(value="/modifyPage",method=RequestMethod.POST)
	public String modifyPOST(BoardVo board, Criteria cri, RedirectAttributes rttr) throws Exception{
		service.modify(board);
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addFlashAttribute("msg","SUCCESS");
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value="/listPage",method=RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") Criteria cri,Model model) throws Exception{
		System.out.println(System.currentTimeMillis());
		model.addAttribute("list",service.listCriteria(cri));
		PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		model.addAttribute("pageMaker",pageMaker);
	}
	
	
}
