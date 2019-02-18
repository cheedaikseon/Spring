package net.koreate.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test")
@Controller
public class TestController {

	@GetMapping("/all")
	public String all() {
		return "/test/all";
	}

	@GetMapping("/memberShip")
	public String memberShip(HttpSession session) {
		System.out.println("\r\n=========== TestController memberShip ===========\r\n");
		Object principal = 
				SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if(principal != null) {
			UserDetails ud = (UserDetails)principal;
			System.out.println(ud);
		}

		Enumeration<String> session_keys = session.getAttributeNames();
		int i = 0;
		String s_name="";
		String s_value="";

		while(session_keys.hasMoreElements()) {
			i++;
			s_name=session_keys.nextElement();
			s_value=session.getAttribute(s_name).toString();
			
			System.out.println("session_name [" +s_name+"] session_value [" +s_value+"] " + i);
		}



		return "/test/memberShip";
	}

	@GetMapping("/master")
	public String Master() {
		return "/test/master";
	}

}
