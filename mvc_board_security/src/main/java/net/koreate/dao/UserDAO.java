package net.koreate.dao;

import net.koreate.vo.UserVO;

public interface UserDAO {
	
	UserVO getUser(String uid)throws Exception;
	
	void join(UserVO vo) throws Exception;
	
	void insertAuth(String uid) throws Exception;
	
}
