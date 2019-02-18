package net.koreate.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.dao.BoardDao;
import net.koreate.util.Criteria;
import net.koreate.vo.BoardVo;


@Service
public class BoardServiceImpl implements BoardService{
	@Inject
	private BoardDao dao;
	
	
	public String regist(BoardVo board) throws Exception {
		
		int result =dao.create(board);
		System.out.println("result : " + result);
		String message = "";
		if(result != 0) {
			message = "SUCCESS";
		}else {
			message = "FAIELD";
		}
		
		return message;
	}

	
	public BoardVo read(Integer bno) throws Exception {
		return dao.read(bno);
	}

	
	public void modify(BoardVo board) throws Exception {
		dao.update(board);
	}

	
	public void remove(Integer bno) throws Exception {
		dao.delete(bno);
	}

	
	public List<BoardVo> listAll() throws Exception {
		return dao.listAll();
	}
	
	
	public List<BoardVo> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}
	
	
	public void updateCnt(int bno) throws Exception {
		System.out.println("service bno " + bno);
		dao.updateCnt(bno);
		
	}

	
	public int listCountCriteria(Criteria cri) {
		return dao.listCountCriteria(cri);
	}
}
