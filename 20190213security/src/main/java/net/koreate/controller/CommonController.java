package net.koreate.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {
	
	@GetMapping("/errorForbidden")
	public String errorForbidden(Authentication auth, Model model){
		System.out.println("error Forbidden : " + auth);
		model.addAttribute("msg","ERROR FORBIDDEN");
		return "errorForbidden";
	}
	
	@GetMapping("login")
	public ModelAndView login(String error, String logout, ModelAndView mav) {
		System.out.println("error : " + error);
		System.out.println("logout : " + logout);
		
		if(error != null) {
			mav.addObject("error", "LOGIN ERROR");
		}
		
		if(logout != null) {
			mav.addObject("logout", "LOG OUT!!");
		}
		
		mav.setViewName("/user/login");
		return mav;
	}
	@GetMapping("/logout")
	public String logout() {		
		System.out.println("LOGOUT GET 요청");
	return "/user/logout";
	}
	@PostMapping("/logout")
	public String logoutPost() {
		System.out.println("----- logout 요청 -----");
		return "redirect:/";
	}
	

}
