package net.koreate.sboard.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.sboard.dao.UserDao;
import net.koreate.sboard.vo.LoginDTO;
import net.koreate.sboard.vo.UserVO;

@Service
public class UserServiceImpl implements UserService{
	
	@Inject
	UserDao dao;
	

	@Override
	public void signUp(UserVO vo) throws Exception{
		dao.signUp(vo);
	}
	
	@Override
	public UserVO getUserByUID(String uid) throws Exception{
		return dao.getUserByUID(uid);
	}

	@Override
	public UserVO signIn(LoginDTO dto) throws Exception {
		return dao.signIn(dto);
	}
	
	
	
	
}
