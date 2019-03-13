package net.koreate.sboard.service;

import java.util.List;

import net.koreate.sboard.util.Criteria;
import net.koreate.sboard.util.PageMaker;
import net.koreate.sboard.vo.CommentVO;

public interface CommentService {
	
	// comment 작성
	void addComment(CommentVO vo) throws Exception;
	
	// comment 전체 목록  
	List<CommentVO> commentList(int bno) throws Exception;
	
	// comment 수정
	void modifyComment(CommentVO vo) throws Exception;
	
	// comment 삭제
	void removeComment(int cno) throws Exception;
	
	// 댓글 paging 처리
	PageMaker getPageMaker(int page, int bno) throws Exception;
	
	// 페이징 처리된 댓글 목록
	List<CommentVO> commentListPage(int bno, Criteria cri) throws Exception;
}
