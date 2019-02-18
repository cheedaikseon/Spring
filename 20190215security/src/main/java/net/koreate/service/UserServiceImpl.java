package net.koreate.service;

import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.koreate.dao.UserDAO;
import net.koreate.vo.UserVO;

@Service
public class UserServiceImpl implements UserService{

	@Inject
	UserDAO dao;
	
	@Inject
	PasswordEncoder encoder;
	
	@Transactional
	@Override
	public void join(UserVO vo) throws Exception {
		// 회원 등록
		vo.setUpw(encoder.encode(vo.getUpw()));
		dao.join(vo);
		dao.insertAuth(vo.getUid());		
	}
}
