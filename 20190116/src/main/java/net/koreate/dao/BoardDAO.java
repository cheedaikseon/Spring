package net.koreate.dao;

import java.util.List;

import net.koreate.util.Criteria;
import net.koreate.util.SearchCriteria;
import net.koreate.vo.BoardVO;

public interface BoardDAO {
	
	// 게시물 등록
	int create(BoardVO boardVo);
	
	// 전체 게시물 목록
	List<BoardVO> listAll();
	
	// 게시물 상세보기
	BoardVO read(int bno);
	
	// 게시물 수정
	int update(BoardVO boardVo);
	
	// 게시물 삭제
	int remove(int bno);
	
	// Criteria 객체를 통한  게시물 목록
	List<BoardVO> listCri(Criteria cri);
	
	// tbl_board의 전체 게시물 개수
	int totalCount();
	
	// 조회수 업데이트
	void updateViewCnt(int bno);
	
	// 검색된 게시물의 총 개수
	int searchListCount(SearchCriteria cri);
	
	// 검색된 결과물 리스트
	List<BoardVO> searchList(SearchCriteria cri);
	
}







