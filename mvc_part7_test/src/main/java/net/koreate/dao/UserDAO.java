package net.koreate.dao;

import net.koreate.vo.LoginDTO;
import net.koreate.vo.UserVO;

public interface UserDAO {
	UserVO login(LoginDTO dto) throws Exception;
	
	UserVO getUserByID(String uid) throws Exception;
}
