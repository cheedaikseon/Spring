package net.koreate.sboard.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.koreate.sboard.service.BoardService;
import net.koreate.sboard.vo.ReplyBoardVO;
import net.koreate.sboard.vo.UserVO;

public class AuthInterceptor extends HandlerInterceptorAdapter{
	
	@Inject
	BoardService service;
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("====AuthInterceptor preHandle====");
		
		String requestUri = request.getRequestURI();
		System.out.println("requestUri : " + requestUri);
		
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("userInfo");
		
		if(obj == null) {
			response.sendRedirect("/user/signIn");
			return false;
		}else {
			
			if(requestUri.equals("/sboard/register")) {
				System.out.println("새글 작성 ");
				return true;
			}
			
			String num = request.getParameter("bno");
			System.out.println("bno : " + num);
			
			UserVO userVo = (UserVO)obj;
			
			if(num != null && !num.equals("")) {
				int bno = Integer.parseInt(num);
				
				if(requestUri.equals("/sboard/replyRegister")) {
					System.out.println("답변글");
					return true;
				}				
				
				ReplyBoardVO board = service.readReply(bno);
				
				if(board.getUno() == userVo.getUno()) {
					return true;
				}else {
					response.sendRedirect("/sboard/readPage?bno="+bno);
					return false;
				}
			}
		}
		System.out.println("====AuthInterceptor preHandle====");
		return true;
	}
	


}
