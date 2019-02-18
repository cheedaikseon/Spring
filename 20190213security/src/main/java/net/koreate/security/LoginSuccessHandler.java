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
			Authentication authentication) throws IOException, ServletException {
		System.out.println("\r\n======== Login Success Handler ========\r\n");
		System.out.println("Login Success\r\n");

		List<String> roleNames = new ArrayList<>();
		for(GrantedAuthority authority : authentication.getAuthorities()) {
			String authorityName = authority.getAuthority();
			System.out.println("names : " + authorityName);
			roleNames.add(authority.getAuthority());
		}
		
		User user = (User)authentication.getPrincipal();
		System.out.println("username = " + user.getUsername());
		System.out.println("password = " + user.getPassword());
		System.out.println("ROLE_NAME = " + roleNames);
		System.out.println("\r\n=======================================\r\n");
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
