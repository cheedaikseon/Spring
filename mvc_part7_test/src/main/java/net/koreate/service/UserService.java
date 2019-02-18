package net.koreate.service;

import net.koreate.vo.LoginDTO;
import net.koreate.vo.UserVO;

public interface UserService {
	
	UserVO login(LoginDTO dto) throws Exception;
	
	UserVO getUserByID(String uid) throws Exception;
}
