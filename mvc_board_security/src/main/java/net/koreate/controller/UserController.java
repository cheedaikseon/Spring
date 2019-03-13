package net.koreate.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.koreate.service.UserService;
import net.koreate.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Inject
	UserService service;
	
	@RequestMapping("/login")
	public String login() {
		return "/user/login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "/user/logout";
	}
	
	@GetMapping("/join")
	public String join() {
		return "/user/join";
	}
	
	@PostMapping("/joinPost")
	public String joinPost(UserVO vo) throws Exception{
		System.out.println("post mapping vo : " + vo);
		service.join(vo);
		return "redirect:/user/login";
	}
}
