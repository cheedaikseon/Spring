package net.koreate.sboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import net.koreate.sboard.service.CommentService;
import net.koreate.sboard.util.PageMaker;
import net.koreate.sboard.vo.CommentVO;

@RestController
@RequestMapping("/comments")
//@Log
@Log4j
public class CommentController {
	
	@Inject
	CommentService cs;
	
	@PostMapping("")
	public ResponseEntity<String> register(@RequestBody CommentVO vo){
		System.out.println("comments 요청");
		log.info("화이팅!");
		ResponseEntity<String> entity = null;
		try {
			cs.addComment(vo);
			entity = new ResponseEntity<>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@GetMapping("/all/{bno}")
	public ResponseEntity<List<CommentVO>> list(@PathVariable("bno") int bno){
		ResponseEntity<List<CommentVO>> entity = null;
		System.out.println(bno);
		try {
			List<CommentVO> list = cs.commentList(bno);
			entity = new ResponseEntity<>(list,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// 댓글 수정
	/*@PatchMapping("/{cno}")*/
	@RequestMapping(value="/{cno}", method={RequestMethod.PUT,RequestMethod.PATCH})
	public ResponseEntity<String> update(
			@PathVariable("cno") int cno,
			@RequestBody CommentVO vo){
		vo.setCno(cno);
		ResponseEntity<String> entity = null;
		
		try {			
			cs.modifyComment(vo);
			entity = new ResponseEntity<>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@DeleteMapping("/{cno}")
	public ResponseEntity<String> delete(@PathVariable("cno") int cno){
		ResponseEntity<String> entity = null;
		try {
			cs.removeComment(cno);
			entity = new ResponseEntity<>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@GetMapping("/{bno}/{page}")
	public ResponseEntity<Map<String,Object>> listPage(
			@PathVariable("bno") int bno,
			@PathVariable("page") int page){
		ResponseEntity<Map<String,Object>> entity = null;
		
		try {
			Map<String,Object> map = new HashMap<>();
			PageMaker pageMaker = cs.getPageMaker(page, bno);
			List<CommentVO> list = cs.commentListPage(bno, pageMaker.getCri());
			map.put("list", list);
			map.put("pageMaker", pageMaker);			
			
			entity = new ResponseEntity<>(map,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	/*@GetMapping("/{bno}/{page}")
	public ResponseEntity<DataListPageMaker> listPage(
			@PathVariable("bno") int bno,
			@PathVariable("page") int page){
		ResponseEntity<DataListPageMaker> entity = null;
		
		try {
			PageMaker pageMaker = cs.getPageMaker(page, bno);
			List<CommentVO> list = cs.commentListPage(bno, pageMaker.getCri());
			DataListPageMaker data = new DataListPageMaker();
			data.setList(list);
			data.setPageMaker(pageMaker);
			
			entity = new ResponseEntity<>(data,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}*/
	
	
	
}
