package net.koreate.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class ErrorAccessDenied implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		System.out.println("\r\n========== ERROR ACCESS DENIED!!! ==========\r\n");
		
		System.out.println("exception : " + accessDeniedException.getMessage() + " 요청 " + request.getRequestURI()+"\r\n");
		
		//response.sendRedirect("/errorForbidden");
		RequestDispatcher rd = request.getRequestDispatcher("/errorForbidden");
		request.setAttribute("SPRING_SECURITY_403_EXCEPTION", accessDeniedException);
		rd.forward(request, response);		
	}	

}
