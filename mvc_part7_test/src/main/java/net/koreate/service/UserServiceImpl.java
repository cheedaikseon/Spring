package net.koreate.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.dao.UserDAO;
import net.koreate.vo.LoginDTO;
import net.koreate.vo.UserVO;

@Service
public class UserServiceImpl implements UserService{
	
	@Inject
	UserDAO dao;

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}

	@Override
	public UserVO getUserByID(String uid) throws Exception {
		return dao.getUserByID(uid);
	}
}
