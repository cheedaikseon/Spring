package net.koreate.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.koreate.service.UserService;
import net.koreate.vo.LoginDTO;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	@Inject
	UserService service;
	
	@GetMapping("login")
	public String login() {
		return "/user/login";
	}
	
	@PostMapping("loginPost")
	public String loginPost(LoginDTO login, Model model) 
			throws Exception{
		System.out.println("loginDTO : " + login);
		model.addAttribute("loginDTO",login);
		
	/*	UserVO vo = service.login(login);
		if(vo != null) {
			model.addAttribute("login", vo);
		}*/
		return "/user/loginPost";
	}
	
	@GetMapping("logout")
	public void logOut() {}
	
	
}
