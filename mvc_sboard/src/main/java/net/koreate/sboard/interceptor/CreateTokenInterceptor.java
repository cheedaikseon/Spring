package net.koreate.sboard.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CreateTokenInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		String token = UUID.randomUUID().toString();
		System.out.println("CreateTokenInterpcetor token : " + token);
		session.setAttribute("csrf_token", token);
		
		//request.getSession().setAttribute("csrf_token", UUID.randomUUID().toString());
		return true;
	}
	
	

}
