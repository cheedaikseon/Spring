package net.koreate.sboard.dao;

import java.util.List;
import java.util.Map;

import net.koreate.sboard.util.SearchCriteria;
import net.koreate.sboard.vo.ReplyBoardVO;

public interface BoardDao {
	
	void register(ReplyBoardVO boardVo) throws Exception;

	void updateOrigin() throws Exception;
	
	/*List<ReplyBoardVO> listReplyCriteria() throws Exception;*/
	
	List<ReplyBoardVO> listReplyCriteria(SearchCriteria cri) throws Exception;
	
	void updateCnt(int bno) throws Exception;

	ReplyBoardVO readReply(int bno);
	
	int listReplyCount(SearchCriteria cri) throws Exception;
	
	void updateReply(ReplyBoardVO boardVo) throws Exception;

	void replyRegister(ReplyBoardVO boardVo) throws Exception;

	void update(ReplyBoardVO board) throws Exception;
	
	void delete(int bno) throws Exception;
	
	void addAttach(String fullName) throws Exception;
	
	List<String> getAttach(int bno) throws Exception;
	
	void deleteAttach(int bno) throws Exception;
	
	void replaceAttach(Map<String,Object> map) throws Exception;
	
}
