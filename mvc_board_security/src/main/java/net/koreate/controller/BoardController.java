package net.koreate.controller;

import javax.inject.Inject;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.security.user.CustomUser;
import net.koreate.service.BoardService;
import net.koreate.util.Criteria;
import net.koreate.util.PageMaker;
import net.koreate.vo.BoardVo;


@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	private BoardService service;
	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public void registerGET(BoardVo board,Model model) throws Exception{
		System.out.println("레지스터 GET 방식 호출");
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerPOST(BoardVo board,RedirectAttributes rttr) throws Exception{
		System.out.println("레지스터 POST 방식 호출");
		System.out.println(board.toString());
		String result = service.regist(board);
		rttr.addFlashAttribute("result",result);
		return "redirect:/board/listPage";
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
	
	@PreAuthorize("isAuthenticated() and ((principal.username == #uid) or hasRole('ROLE_MASTER'))")
	@RequestMapping(value="/removePage",method=RequestMethod.POST)
	public String remove(@RequestParam("uid") String uid, @RequestParam("bno") int bno, Criteria cri, RedirectAttributes rttr) throws Exception{
		System.out.println("remove request uid : " + uid);
		service.remove(bno);
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addFlashAttribute("msg","SUCCESS");
		return "redirect:/board/listPage";
	}
	
	@Secured({"ROLE_USER","ROLE_MEMBERSHIP","ROLE_MASTER"})
	@RequestMapping(value="/modifyPage",method=RequestMethod.POST)
	public String modifyGET(Authentication auth,@RequestParam("uid") String uid, int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		System.out.println("modifyPage request uid : " + uid);
		CustomUser user = (CustomUser)auth.getPrincipal();
		String sessionUid = user.getUsername();
		System.out.println("session uid : " + sessionUid);
		if(uid.equals(sessionUid)) {
			System.out.println("일치!");
			model.addAttribute("boardVO",service.read(bno));
			return "/board/modifyPage";
		}else {
			System.out.println("틀림!");
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value="/modifyAccept",method=RequestMethod.POST)
	public String modifyPOST(BoardVo board, Criteria cri, RedirectAttributes rttr) throws Exception{
		System.out.println("modifyAccept request uid : " + board.getUid());
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
