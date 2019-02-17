package net.koreate.sboard.dao;

import net.koreate.sboard.vo.MessageVO;

public interface MessageDao {

	void create(MessageVO vo) throws Exception;
	
	void updateMessage(int mno) throws Exception;
	
	MessageVO readMessage(int mno) throws Exception;
}