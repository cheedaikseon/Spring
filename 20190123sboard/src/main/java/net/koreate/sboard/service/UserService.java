package net.koreate.sboard.service;

import net.koreate.sboard.vo.LoginDTO;
import net.koreate.sboard.vo.UserVO;

public interface UserService {
	
	void signUp(UserVO vo)throws Exception;
	
	UserVO getUserByUID(String uid) throws Exception;
	
	UserVO signIn(LoginDTO dto)throws Exception;
	
}
