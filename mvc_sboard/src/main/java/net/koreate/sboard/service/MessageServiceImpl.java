package net.koreate.sboard.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.koreate.sboard.dao.MessageDao;
import net.koreate.sboard.dao.UserDao;
import net.koreate.sboard.vo.MessageVO;
import net.koreate.sboard.vo.UserVO;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Inject
	UserDao userDao;
	
	@Inject 
	MessageDao messageDao;	

	@Transactional
	@Override
	public void addMessage(MessageVO vo) throws Exception {
		System.out.println("addMessage service 호출 ");
		System.out.println("addMessage vo : " + vo);
		
		// 발신 사용자 포인트 10 증가
		UserVO uv = new UserVO();
		uv.setUid(vo.getSender());
		uv.setUpoint(10);		
		userDao.updatePoint(uv);
		
		// 메시지 등록
		messageDao.create(vo);
		
		System.out.println("addMessage 종료");
	}

	@Override
	public MessageVO readMessage(String uid, int mno) throws Exception {
		System.out.println("readMessage service 호출 ");
		System.out.println("readMessage service  uid : " + uid);
		System.out.println("readMessage service  mno : " + mno);
		
		// 메시지 상태 변경  opendate - > 현재시간
		messageDao.updateMessage(mno);
		
		// 메시지를 읽은 대상자 포인트 변경 기존  + 5
		UserVO uv = new UserVO();
		uv.setUid(uid);
		uv.setUpoint(5);
		userDao.updatePoint(uv);
		
		MessageVO mv = messageDao.readMessage(mno);
		System.out.println("readMessage 종료");
		return mv;
	}

	
	
	
}
