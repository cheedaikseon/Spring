package net.koreate.sboard.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CheckRefererInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("CheckRefererInterceptor check 시작 ");
		String now_url = request.getRequestURL().toString();
		System.out.println("현재 요청 URL : " + now_url);
		
		String old_url = request.getHeader("referer");
		System.out.println("이전 요청 URL : " + old_url);
		
		if(old_url == null || old_url.equals("")) {
			System.out.println("이전 요청이 존재 하지 않음");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근');");
			out.println("location.href='/';");
			out.println("</script>");
			out.flush();
			return false;
		}
		
		
		System.out.println("CheckRefererInterceptor check 종료 ");
		return true;
		
		
	}
	
	

}
