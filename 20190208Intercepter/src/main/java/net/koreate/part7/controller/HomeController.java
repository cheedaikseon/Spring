package net.koreate.part7.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@GetMapping("test1")
	public String test1() {
		System.out.println("---------homeController 요청 처리----------");
		System.out.println("---------homeController 요청 종료----------");
		
		return "home"; 
	}

	@GetMapping("test2")
	public String test2(Model model) {
		System.out.println("---------homeController test2 요청 처리----------");
		
		model.addAttribute("result","test2 job");
		
		System.out.println("---------homeController test2 요청 종료----------");
		
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
