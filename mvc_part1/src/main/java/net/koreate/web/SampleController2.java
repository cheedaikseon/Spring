package net.koreate.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.koreate.web.vo.ProductVo;

@Controller
public class SampleController2 {

	@RequestMapping("doC")
	public String doC(Model model) {
		
		model.addAttribute("msg","MESSAGE");
		
		return "result";
	}
	
	@RequestMapping(value="doD")
	public String doD(
			@RequestParam("message") String message,
			@ModelAttribute("msg") String msg) {
		
		System.out.println("message : " + message);
		System.out.println("msg : " + msg);
		System.out.println("doD");
		
		return "result";
	}
	
	@RequestMapping("doE")
	public String doE(Model model) {
		
		ProductVo product = new ProductVo("Sample",10000);
		model.addAttribute("product",product);
		
		ProductVo product1 = new ProductVo("Smaple2",20000);
		model.addAttribute(product1);
		
		return "product";
	}
	
	@RequestMapping("doF")
	public ModelAndView doF() {
		ModelAndView mav = new ModelAndView();
		ProductVo product = new ProductVo("Sample3",30000);
		mav.addObject(product);
		mav.setViewName("product");		
		return mav;
	}
	
	
}
