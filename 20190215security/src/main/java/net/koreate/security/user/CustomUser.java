package net.koreate.security.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import net.koreate.vo.AuthVO;
import net.koreate.vo.UserVO;

@Getter
public class CustomUser extends User{

	private UserVO user;
	
	private static final long serialVersionUID = 1L;

	public CustomUser(String username, String password,Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);

	}
	
	public CustomUser(UserVO vo) {
		super(vo.getUid(),vo.getUpw(), authorities(vo.getAuthList()));
		this.user = vo;
	}
	
	
	public static Collection<? extends GrantedAuthority> authorities(List<AuthVO> auth){
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(AuthVO a : auth) {
			authorities.add(new SimpleGrantedAuthority(a.getAuth()));
		}
		return authorities;
	}

}
