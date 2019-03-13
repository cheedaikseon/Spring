package net.koreate.part7.controller;

import java.util.concurrent.Callable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@GetMapping("test1")
	public String test1() {
		System.out.println("home Controller test1 요청 처리");
		
		System.out.println("home Controller test1 요청 종료");
		return "home";
	}
	
	@GetMapping("test2")
	public String test2(Model model) {
		System.out.println("home Controller test2 요청 처리");
		System.out.println("test2 ... 호출....");
		
		model.addAttribute("result", "test2 job");
		
		System.out.println("home Controller test2 요청 종료");
		
		return "home";
	}
	
	
	@ResponseBody
	@GetMapping("test3")
	public Callable<String> test3(){
		System.out.println("Home Controller test3 . Thread : " +
							Thread.currentThread().getName()
						  );
		Callable<String> callable = null;
		callable  = new Callable<String>() {
			
			public String call() throws Exception {
				
				System.out.println(
									"test3 #async task started Thread : " +
									Thread.currentThread().getName()
								  );
				Thread.sleep(500);
				System.out.println("controller async Thread finished");
				
				return "async result";
			}
		};
		return callable;
	}
	
	
	
	
}
