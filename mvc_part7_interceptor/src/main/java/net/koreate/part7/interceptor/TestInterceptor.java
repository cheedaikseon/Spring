package net.koreate.part7.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TestInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		System.out.println("preHandle START ");
		
		HandlerMethod method = (HandlerMethod)handler;
		Method methodObj = method.getMethod();
		
		System.out.println("Bean : " + method.getBean());
		System.out.println("method : " + methodObj);
		System.out.println("preHandle END ");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle START ");
		
		Map<String, Object> map = modelAndView.getModel();
		System.out.println(map.size());
		for(String o : map.keySet()) {
			System.out.println("key : " + o);
			System.out.println("value : " + map.get(o));
		}
			
		System.out.println("view : " + modelAndView.getViewName());
		
		Object result = modelAndView.getModel().get("result");
		if(result == null) {
			modelAndView.addObject("result","postHandle job");
		}		
		System.out.println("postHandle END ");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion START ");
		
		System.out.println("afterCompletion END ");
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("afterConcurrentHandlingStarted Start ");
		System.out.println("afterConcurrentHandlingStarted END ");
		
	}
	
	

}
