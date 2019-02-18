package net.koreate.service;

import java.util.List;

import net.koreate.util.Criteria;
import net.koreate.util.SearchCriteria;
import net.koreate.vo.BoardVO;

public interface BoardService {
	
	String register(BoardVO board);

	List<BoardVO> listAll();
	
	BoardVO read(int bno);
	
	String modify(BoardVO boardVo);
	
	String remove(int bno);
	
	List<BoardVO> listCri(Criteria cri);
	
	int totalCount();
	
	void updateViewCnt(int bno);
	
	// 검색된 게시물의 총 개수
	int searchListCount(SearchCriteria cri);
	
	// 검색된 결과물 리스트
	List<BoardVO> searchList(SearchCriteria cri);
	
	
	
	
}
