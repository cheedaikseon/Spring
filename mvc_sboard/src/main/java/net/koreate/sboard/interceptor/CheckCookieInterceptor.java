package net.koreate.sboard.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import net.koreate.sboard.service.UserService;
import net.koreate.sboard.vo.UserVO;

public class CheckCookieInterceptor extends HandlerInterceptorAdapter{
	
	@Inject
	UserService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("CheckCookie 시작");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo") != null) {
			return true;
		}
		
		Cookie cookie = WebUtils.getCookie(request, "signInCookie");
		if(cookie != null) {
			UserVO vo = service.getUserByUID(cookie.getValue());
			System.out.println("사용자 정보 : " + vo);
			if(vo != null) {
				session.setAttribute("userInfo", vo);
			}
		}
		System.out.println("CheckCookie 종료");
		return true;
	}
	
	

}
