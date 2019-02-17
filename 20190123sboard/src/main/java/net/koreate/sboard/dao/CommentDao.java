package net.koreate.sboard.dao;

import java.util.List;
import java.util.Map;

import net.koreate.sboard.vo.CommentVO;

public interface CommentDao {
	
	void addComment(CommentVO vo) throws Exception;
	
	List<CommentVO> list(int bno) throws Exception;
	
	void update(CommentVO vo) throws Exception;
	
	void delete(int cno) throws Exception;
	
	int count(int bno) throws Exception;
	
	List<CommentVO> listPage(Map<String, Object> map) throws Exception;
}
