package net.koreate.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import net.koreate.service.BoardService;
import net.koreate.service.UserService;
import net.koreate.vo.BoardVo;
import net.koreate.vo.UserVO;

public class AuthInterceptor extends HandlerInterceptorAdapter{
	
	@Inject
	UserService service;
	
	@Inject 
	BoardService boardService;
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		String btnStr = request.getParameter("bno");
		System.out.println("Auth Interceptor=======================");
		int bno = 0;
		if(btnStr != null) {
			bno = Integer.parseInt(btnStr);
			System.out.println("bno : " + bno);
			BoardVo board = boardService.read(bno);
			System.out.println("작성자 : "+board.getUno());
			Object user = session.getAttribute("login"); 
			if(user != null && ((UserVO)user).getUno() != board.getUno()) {
				response.sendRedirect("/");
				return false;
			}			
		}else {
			System.out.println("bno 값이 존재 하지 않음");
		}
		
		if(session.getAttribute("login") == null) {
			
			String uri = request.getRequestURI();
			System.out.println("uri : " + uri);
			String query = request.getQueryString();
			System.out.println("query : " + query);
			
			if(query == null || query.equals("null")) {
				query = "";
			}else {
				query = "?"+query;
			}
			
			if(request.getMethod().equals("GET")) {
				System.out.println("요청 : " + (uri+query));
				session.setAttribute("dest",(uri+query));
			}			
			
			Cookie cookie = WebUtils.getCookie(request, "loginCookie");
			
			if(cookie != null) {
				String uid = cookie.getValue();
				System.out.println("uid : " + uid);
				
				// 사용자 정보 획득
				UserVO vo = service.getUserByID(uid);
				
				if(vo != null) {
					session.setAttribute("login", vo);
					return true;
				}
			}
			
			if(!uri.equals("/")) {
				response.sendRedirect("/user/login");
				return false;
			}
			
		}
		return true;
	}
}
