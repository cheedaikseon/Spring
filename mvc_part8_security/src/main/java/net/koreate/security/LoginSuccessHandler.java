package net.koreate.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		System.out.println("Login Success");
		
		List<String> roleNames = new ArrayList<>();
		
		for(GrantedAuthority authority : auth.getAuthorities()) {
			String authoryNames = authority.getAuthority();
			System.out.println("names : " + authoryNames);
			roleNames.add(authoryNames);			
		}
		
		User user = (User)auth.getPrincipal();
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("ROLE NAMES : " + roleNames);
		
		if(roleNames.contains("ROLE_MASTER")) {
			response.sendRedirect("/test/master");
			return;
		}
		
		if(roleNames.contains("ROLE_MEMBERSHIP")) {
			response.sendRedirect("/test/memberShip");
			return;
		}
		response.sendRedirect("/");
		System.out.println("권한이 없음");
	}
	
	

}
