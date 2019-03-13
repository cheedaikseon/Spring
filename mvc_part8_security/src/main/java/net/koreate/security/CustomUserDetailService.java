package net.koreate.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.koreate.dao.MemberMapper;
import net.koreate.vo.CustomUser;
import net.koreate.vo.MemberVO;

public class CustomUserDetailService implements UserDetailsService{

	@Inject
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Load User  : " + username);
		MemberVO vo = memberMapper.read(username);
		System.out.println("사용자 정보 : " + vo);
		return vo == null ? null : new CustomUser(vo);
	}

	
}
