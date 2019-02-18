package net.koreate.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler{

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response,
			Authentication auth)
			throws IOException, ServletException {
		System.out.println("\r\n============ CustomLogoutSuccessHandler ============\r\n");
		System.out.println("auth : "+auth);
		if(auth != null) {
			System.out.println(((User)auth.getPrincipal()).getUsername());
		}
		response.sendRedirect("/");
		System.out.println("\r\n============ CustomLogoutSuccessHandler ============\r\n");
	}
}
