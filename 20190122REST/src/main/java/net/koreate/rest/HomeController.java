package net.koreate.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.koreate.vo.SampleVO;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home( Model model) {
		return "home";
	}
	
	@GetMapping("/ajaxTest")
	public String ajaxTest() {
		return "ajaxTest";
	}
	
	@GetMapping("/getSample")
	@ResponseBody
	public SampleVO getSample(SampleVO sample, Model model) {
		System.out.println("getSample 요청 들어옴");
		System.out.println("sample : " + sample);
		return sample;
	}
	
	@PostMapping("/getSample")
	@ResponseBody
	public List<SampleVO> getSamplePOST(SampleVO sample) {
		System.out.println("postSample 요청 들어옴");
		System.out.println("sample : " + sample);
		List<SampleVO> list = new ArrayList<>();
		list.add(sample);
		for(int i=0; i<10; i++) {
			SampleVO sampleVo = new SampleVO();
			sampleVo.setName("모찌 : " + i);
			sampleVo.setAge(i);
			list.add(sampleVo);
		}
		return list;
	}
	
	//@RequestMapping(value="/getSample2", method=RequestMethod.PUT)
	@PutMapping("/getSample2")
	@ResponseBody
	public SampleVO getSample2(@RequestBody SampleVO sample) {
		System.out.println("put getSample2 : " + sample);
		return sample;
	}
	
	
}




