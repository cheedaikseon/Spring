package net.koreate.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

public class LogoutInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Object obj = request.getSession().getAttribute("login");
		if(obj != null) {
			request.getSession().invalidate();
		}
		
/*		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("loginCookie")) {
				Cookie c = new Cookie("loginCookie","");
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}*/
		
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		if(loginCookie != null) {
			loginCookie.setPath("/");
			loginCookie.setMaxAge(0);
			response.addCookie(loginCookie);
		}		
		
		response.sendRedirect("/");
		return false;
	}
}
