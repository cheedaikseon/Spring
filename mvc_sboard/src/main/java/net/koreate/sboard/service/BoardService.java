package net.koreate.sboard.service;

import java.util.List;

import net.koreate.sboard.util.PageMaker;
import net.koreate.sboard.util.SearchCriteria;
import net.koreate.sboard.vo.ReplyBoardVO;

public interface BoardService {

	// 게시물 작성
	void registReply(ReplyBoardVO boardVo) throws Exception;
	// 검색결과에 따른 페이징 처리된 게시물 리스트
	List<ReplyBoardVO> listReplyCriteria(SearchCriteria cri)throws Exception;
	//조회수 업데이트
	void updateCnt(int bno) throws Exception;
	// 게시물 상세정보
	ReplyBoardVO readReply(int bno) throws Exception;
	// 답변글 작성
	void replyRegister(ReplyBoardVO boardVo) throws Exception;
	// 글 수정
	void modify(ReplyBoardVO board)throws Exception;
	// 글 삭제
	void remove(int bno)throws Exception;
	// model에 담기 위한 pageMaker 객체 생성 반환
	PageMaker getPageMaker(SearchCriteria cri) throws Exception;
	// 게시물에 등록된 첨부파일 목록
	List<String> getAttach(int bno) throws Exception;
	

}
