package net.koreate.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.dao.BoardDAO;
import net.koreate.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	BoardDAO dao;
	
	public String register(BoardVO board) {
		int result = dao.register(board);
		return getResult(result);
	}
	
	public List<BoardVO> listAll(){
		return dao.listAll();
	}
	
	public String getResult(int result) {
		String msg = "fail";
		if(result > 0 ) {
			msg = "success";
		}
		return msg;
	}

	@Override
	public BoardVO read(int bno) {
		
		return dao.read(bno);
	}

	@Override
	public String modify(BoardVO boardvo) {
		int result = dao.modify(boardvo);
		return getResult(result);
	}

	@Override
	public String remove(int bno) {
		int result = dao.remove(bno);
		return getResult(result);
	}

	
	
	
}
