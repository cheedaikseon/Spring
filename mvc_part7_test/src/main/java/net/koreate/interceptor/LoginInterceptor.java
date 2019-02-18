package net.koreate.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.koreate.service.UserService;
import net.koreate.vo.LoginDTO;
import net.koreate.vo.UserVO;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	
	
	@Inject
	UserService service;
	
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("LoginInterceptor preHandle");
		HttpSession session = request.getSession();
		if(session.getAttribute("login") != null) {
			System.out.println("session 값 존재 data clear");
			session.removeAttribute("login");
		}		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("LoginInterceptor postHandle");
		
		ModelMap modelMap = modelAndView.getModelMap();
		LoginDTO dto = (LoginDTO)modelMap.get("loginDTO");
		System.out.println("postHandle : " + dto);
		//LoginDTO dto1 = (LoginDTO)modelAndView.getModel().get("loginDTO");
		
		UserVO vo = service.login(dto);
		
		HttpSession session = request.getSession();
		
		if(vo != null) {
			
			System.out.println("login user : " + vo);
			
			session.setAttribute("login", vo);
			
			if(dto.isUseCookie()) {
				System.out.println("리멤버 미 체크");
				Cookie cookie = new Cookie("loginCookie",vo.getUid());
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*7);
				response.addCookie(cookie);
				System.out.println("Session ID : " + session.getId());
				System.out.println("쿠키 생성 완료");
			}
		}else {
			response.sendRedirect("/user/login");
			return;
		}	
		
		Object dest = session.getAttribute("dest");
		response.sendRedirect(dest != null ? (String)dest : "/");		
		
	}
	
	
	

}
