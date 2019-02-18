package net.koreate.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.dao.BoardDAO;
import net.koreate.util.Criteria;
import net.koreate.util.SearchCriteria;
import net.koreate.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	BoardDAO dao;
	
	public String register(BoardVO board) {
		int result = dao.create(board);
		return getResult(result);
	}
	
	public List<BoardVO> listAll(){
		return dao.listAll();
	}
	
	public BoardVO read(int bno) {
		return dao.read(bno);
	}
	
	public String modify(BoardVO boardVo) {
		int result = dao.update(boardVo);
		return getResult(result);
	}
	
	public String remove(int bno) {
		int result = dao.remove(bno);
		return getResult(result);
	}
	
	public List<BoardVO> listCri(Criteria cri){
		return dao.listCri(cri);
	}
	
	public int totalCount() {
		return dao.totalCount();
	}
	
	
	public void updateViewCnt(int bno) {
		dao.updateViewCnt(bno);
	}
	
	
	public int searchListCount(SearchCriteria cri) {
		return dao.searchListCount(cri);
	}
	
	public List<BoardVO> searchList(SearchCriteria cri){
		return dao.searchList(cri);
	}
	
	
	
	public String getResult(int result) {
		String msg = "FAIL";
		if(result > 0 ) {
			msg = "SUCCESS";
		}
		return msg;
	}
	
	
	
}
