package net.koreate.sboard.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CheckTokenInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("=====  CheckTokenIntercepter  ======");
		HttpSession session = request.getSession();
		String sessionToken = (String)session.getAttribute("csrf_token");
		System.out.println("sessionToken : " + sessionToken);
		String requestToken = request.getParameter("csrf_token");
		System.out.println("requestToken : " + requestToken);
		if(requestToken == null || requestToken.equals("") || !requestToken.equals(sessionToken)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근');");
			out.println("history.go(-1)");
			out.println("</script>");
			out.flush();
			return false;
		}
		
		System.out.println("=====  CheckTokenIntercepter  ======");
		return true;
	}

	
	
}
