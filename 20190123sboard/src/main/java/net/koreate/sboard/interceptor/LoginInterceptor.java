package net.koreate.sboard.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.koreate.sboard.dao.UserDao;
import net.koreate.sboard.service.UserService;
import net.koreate.sboard.vo.BanIPVO;
import net.koreate.sboard.vo.LoginDTO;
import net.koreate.sboard.vo.UserVO;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Inject
	UserService service;
	
	@Inject
	UserDao dao;
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("LoginInterceptor preHandle");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userInfo") != null) {
			session.removeAttribute("userInfo");
			//session.invalidate();
		}	
		
		String ip = getIp(request);
		System.out.println("preHandle ip : " + ip);
		BanIPVO banVo = dao.getBanIPVObyIP(ip);
		System.out.println("preHandle : "+banVo);
		if(banVo != null && banVo.getCnt() >= 5) {
			
			long saveTime = getTime(banVo.getBandate());
			
			if(saveTime > 0 ) {
				SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
				String now = sdf.format(new Date(saveTime));
				System.out.println("남은 시간 : " + now);
				
				RequestDispatcher rd = request.getRequestDispatcher("/user/signIn");
				request.setAttribute("message", "일정시간 동안 로그인 할 수 없습니다. 남은시간 : " + now);
				rd.forward(request, response);
				return false;
			}else {
				System.out.println("제한 초기화");
				dao.removeBanIP(ip);
			}		
		}		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		ModelMap modelObj = modelAndView.getModelMap();
		LoginDTO dto = (LoginDTO)modelObj.get("loginDTO");
		System.out.println("LoginInterceptor postHandle : " + dto);
		
		String ip = getIp(request);
		System.out.println("ip : " + ip);		
		BanIPVO banVo = dao.getBanIPVObyIP(ip);
		
		UserVO vo = service.signIn(dto);
		
		if(vo != null) {
			/*
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", vo);
			*/
			request.getSession().setAttribute("userInfo", vo);
			
			if(banVo != null) {
				dao.removeBanIP(ip);
				System.out.println("ban_ip 로그인 성공 초기화");
			}
			
			if(dto.isUseCookie()) {
				Cookie cookie = new Cookie("signInCookie",vo.getUid());
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*15);
				response.addCookie(cookie);
			}
		}else {
			int count = 5;
			String message = "";
			
			if(banVo == null) {
				System.out.println("최초실패");
				dao.signInFail(ip);
				count = count - 1;
			}else {
				System.out.println(banVo);
				dao.updateBanIPCnt(ip);
				count = count - (banVo.getCnt()+1);
			}
			
			if(count > 0) {
				message = "회원정보가 일치하지 않습니다. 남은횟수 : "+count;
			}else {
				message = "너무 많은 시도... 30분 동안 ip가 차단됩니다.";
			}
			
			modelAndView.addObject("message",message);
			modelAndView.setViewName("/user/signIn");
		}
	}	
	
	public String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		System.out.println("X-Forwarded-For : " + ip);
		if(ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
			System.out.println("Proxy-Client-IP : " + ip);
		}
		
		if(ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			System.out.println("WL-Proxy-Client-IP : " + ip);
		}
		
		if(ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			System.out.println("HTTP_CLIENT_IP : " + ip);
		}
		
		if(ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			System.out.println("HTTP_X_FORWARDED_FOR : " + ip);
		}
		
		if(ip == null) {
			ip = request.getRemoteAddr();
			System.out.println("remote addr : " + ip);
		}
		
		System.out.println(" ip : " + ip);
		return ip;
	}
	
	public long getTime(Date bandate) {
		/*long now = System.currentTimeMillis();
		System.out.println("now : "+ now);
		long banTime = bandate.getTime();
		System.out.println("banTime : "+ banTime);
		long diff = Math.abs(banTime - now);
		System.out.println("diff : " + diff);
		
		long saveTime = limit - diff;
		System.out.println("saveTime : " + saveTime);*/
		int limit = 1000*60*30; //
		System.out.println("limit :  " + limit);
		
		return limit - (System.currentTimeMillis() - bandate.getTime());
	}
	
	
}
