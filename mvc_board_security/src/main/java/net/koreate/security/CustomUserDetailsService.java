package net.koreate.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.koreate.dao.UserDAO;
import net.koreate.security.user.CustomUser;
import net.koreate.vo.UserVO;

public class CustomUserDetailsService implements UserDetailsService{
	
	@Inject
	UserDAO dao;	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("request username : " + username);
		UserVO vo = null;
		try {
			vo = dao.getUser(username);
			System.out.println(vo);
		} catch (Exception e) {}
		return vo == null ? null : new CustomUser(vo);
	}
	
	

}
