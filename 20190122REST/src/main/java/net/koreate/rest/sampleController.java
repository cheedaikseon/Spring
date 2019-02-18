package net.koreate.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.koreate.vo.SampleVO;


@Controller
public class sampleController {
	
	@GetMapping("/testJSON")
	public String toJson(Model model) {
		model.addAttribute("Hello hihihihi byebye");
		return "JSON";
	}
	
	@GetMapping("/sendSampleVO")
	public String sendSampleVO(Model model) {
		SampleVO vo = new SampleVO();
		vo.setName("양성주");
		vo.setAge(18);
		model.addAttribute("sample",vo);
		return "JSON";
	}
	

}
