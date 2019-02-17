package net.koreate.sboard.controller;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import net.koreate.sboard.service.UserService;
import net.koreate.sboard.vo.LoginDTO;
import net.koreate.sboard.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Inject
	UserService service;
	
	@RequestMapping("/signIn")
	public String signIn() {
		return "/user/signIn";
	}
	
	@RequestMapping("/signUp")
	public String signUp() {
		return "/user/signUp";
	}
	
	@PostMapping("/signInPost")
	public ModelAndView signInPost(LoginDTO dto,ModelAndView modelAndView) {
		System.out.println("loginDTO : " + dto);
		modelAndView.addObject("loginDTO", dto);
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
	
	@PostMapping("/signUpPost")
	public String signUpPost(UserVO vo,RedirectAttributes rttr) throws Exception{
		System.out.println("UserVO : " + vo);
		// 회원 가입 처리
		service.signUp(vo);		
		rttr.addFlashAttribute("message","회원가입 성공");
		return "redirect:/user/signIn";
	}
	
	@GetMapping("/signOut")
	public String signOut(HttpSession session,
						HttpServletRequest request,
						HttpServletResponse response) {
		
		if(session.getAttribute("userInfo") != null) {
			session.removeAttribute("userInfo");
			
			Cookie signInCookie = WebUtils.getCookie(request, "signInCookie");
			if(signInCookie != null) {
				signInCookie.setPath("/");
				signInCookie.setMaxAge(0);
				response.addCookie(signInCookie);
			}
		}
		
		return "redirect:/";
	}
	
	
	

}
