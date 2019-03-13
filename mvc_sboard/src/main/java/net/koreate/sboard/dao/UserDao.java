package net.koreate.sboard.dao;

import net.koreate.sboard.vo.BanIPVO;
import net.koreate.sboard.vo.LoginDTO;
import net.koreate.sboard.vo.UserVO;

public interface UserDao {
	
	void updatePoint(UserVO uv) throws Exception;
	
	void signUp(UserVO vo) throws Exception;
	
	UserVO getUserByUID(String uid) throws Exception;
	
	UserVO signIn(LoginDTO dto) throws Exception;
	
	// IP 관련
	BanIPVO getBanIPVObyIP(String ip)throws Exception;
	
	void signInFail(String ip) throws Exception;
	
	void updateBanIPCnt(String ip)throws Exception;
	
	void removeBanIP(String ip)throws Exception;
	
}
