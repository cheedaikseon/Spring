package net.koreate.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {
	
	@RequestMapping("doA")
	public void doA() {
		System.out.println("doA call!!");
	}
	
	@RequestMapping("doB")
	public String doB() {
		System.out.println("doB");
		return "home";
	}
	
}
