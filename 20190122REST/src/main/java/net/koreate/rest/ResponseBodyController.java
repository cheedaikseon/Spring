package net.koreate.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.koreate.vo.SampleVO;

//@Controller
@RestController
public class ResponseBodyController {
	
	
	@RequestMapping("/hello")
	public /*@ResponseBody*/ String hello() {
		return "Hello Hi Hi ByeBye";
	}
	
	@GetMapping("/sendVo")
	//@ResponseBody restcontroller 로 대체 가능
	public SampleVO sendVo() {
		SampleVO sample = new SampleVO();
		sample.setName("모리");
		sample.setAge(30);
		return sample;
	}
	
	@GetMapping("/sendList")
	public List<SampleVO> sendSampleVoList(){
		List<SampleVO> list = new ArrayList<>();
		
		for(int i=0; i<10;i++) {
			SampleVO sample = new SampleVO();
			sample.setName("모찌"+i);
			sample.setAge(i);
			list.add(sample);
		}
		return list;		
	}
	
	@GetMapping("/sendMap")
	public Map<Integer,SampleVO> sendSampeMap(){
		Map<Integer,SampleVO> map = new HashMap<>();
		
		for(int i=0; i<50; i++) {
			SampleVO sample = new SampleVO();
			sample.setName("홍길동"+i);
			sample.setAge(i);
			map.put(i, sample);
		}
		return map;
	}
	
	@GetMapping("/sendErrorAuth")
	public ResponseEntity<SampleVO> sendSampleAuth(){
		return new ResponseEntity<>(new SampleVO(),HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/sendErrorNot")
	public ResponseEntity<List<SampleVO>> sendError(){
		List<SampleVO> list = new ArrayList<>();
		
		for(int i=0; i<30; i++) {
			SampleVO	s = new SampleVO();
			s.setName("모띠");
			s.setAge(18);
			list.add(s);
		}
		return new ResponseEntity<>(list,HttpStatus.NOT_FOUND);

	}
	
}







