package net.koreate.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomLoginFailHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException ex) throws IOException, ServletException {
	System.out.println("\r\n===== onAuthenticationFailure ====\r\n");
	System.out.println("Login FAILE!!\r\n");
	System.out.println(ex.getMessage());
	response.sendRedirect("/user/login");
	System.out.println("\r\n=========================================\r\n");
		
	}

}
