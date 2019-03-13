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
			AccessDeniedException ex) throws IOException, ServletException {
		System.out.println("ErrorAccessDenied !!!!");
		System.out.println("exception : " + ex.getMessage() +" 요청 : " + request.getRequestURI());
		
		//response.sendRedirect("/errorForbidden");
		RequestDispatcher rd = request.getRequestDispatcher("/errorForbidden");
		request.setAttribute("SPRING_SECURITY_403_EXCEPTION", ex);
		rd.forward(request, response);		
	}
}
