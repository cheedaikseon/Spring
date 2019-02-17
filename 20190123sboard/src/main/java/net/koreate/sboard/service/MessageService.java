package net.koreate.sboard.service;

import net.koreate.sboard.vo.MessageVO;

public interface MessageService {
	
	void addMessage(MessageVO vo) throws Exception;

	MessageVO readMessage(String uid,int mno) throws Exception;
}
