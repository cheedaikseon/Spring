package net.koreate.sboard.aop;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import net.koreate.sboard.vo.ReplyBoardVO;

@Component

@Aspect
public class AOPAdvice {
	
	@Before("execution(* net.koreate.sboard.service.MessageService*.*(..))")
	public void startLog(JoinPoint jp) {
		System.out.println("-----------------------------");
		System.out.println("------START LOG START--------");
		System.out.println("target : " + jp.getTarget());
		System.out.println("type : " + jp.getKind());
		System.out.println("parameters : " + Arrays.toString(jp.getArgs()));
		System.out.println("name : " + jp.getSignature().getName());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String now = sdf.format(new Date());
		System.out.println("start time : " + now);
		System.out.println("----------------------------");
		System.out.println("-------START LOG END--------");
	}
	
	@After("execution(* net.koreate.sboard.service.MessageService*.*(..))")
	public void endLog(JoinPoint jp) {		
		System.out.println("-----------------------------");
		System.out.println("--------END LOG START--------");
		System.out.println("type : " + jp.getKind());
		System.out.println("parameters : " + Arrays.toString(jp.getArgs()));
		System.out.println("name : " + jp.getSignature().getName());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String now = sdf.format(new Date());
		System.out.println("start time : " + now);
		System.out.println("----------------------------");
		System.out.println("---------END LOG END--------");
	}
	
	@Around("execution(* net.koreate.sboard.service.MessageService*.readMessage(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("-----------------------------");
		System.out.println("--------AROUND START---------");
		long startTime  = System.currentTimeMillis();
		
		Object o = pjp.proceed();
		
		long endTime  = System.currentTimeMillis();
		System.out.println(pjp.getSignature().getName()+"걸린시간 : "+(endTime-startTime));
		System.out.println("----------AROUND END---------");
		System.out.println("-----------------------------");
		
		return o;
	}
	@Around("execution(* net.koreate.sboard.service.BoardService*.registReply(..))")
	public void insertBoardCheck(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("insert Check Around Start");
		
		Object[] parameters = pjp.getArgs();
		
		ReplyBoardVO vo = (ReplyBoardVO)parameters[0];
		System.out.println("insert Check vo : " + vo);
		
		pjp.proceed();
		
		
		System.out.println("insert Check Around end");
	}
	
	@Around("execution(void net.koreate.sboard.service.BoardService*.*(..))")
	public void boardCheck(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("boardCheck Around Start");
		
		Object[] parameters = pjp.getArgs();
		
		for(Object o : parameters) {
			System.out.println("around boardCheck : " + o.toString());
		}
		pjp.proceed();
		
		System.out.println("boardCheck Around end");
	}
	
	@Around("execution(!void net.koreate.sboard.service.BoardService*.*(..))")
	public Object boardCheck2(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("boardCheck2 !void START");
		Object obj = pjp.proceed();
		System.out.println(obj.toString());	
		
		if(pjp.getSignature().getName().equals("listReplyCriteria")) {
			if(obj instanceof List) {
				List<ReplyBoardVO> list = (List<ReplyBoardVO>)obj;
				for(ReplyBoardVO vo : list) {
					System.out.println(vo.toString());
				}
				
				/*ReplyBoardVO vo = new ReplyBoardVO();
				vo.setBno(1);
				vo.setTitle("안녕하세요");
				vo.setShowboard("y");
				List<ReplyBoardVO> list1 = new ArrayList<ReplyBoardVO>();
				list1.add(vo);
				obj = list1;*/
			}
		}
		System.out.println("boardCheck2 !void END");
		return obj;
	}
	

}
