package net.koreate.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import net.koreate.dao.MemberMapper;
import net.koreate.vo.MemberVO;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations= {"classpath:context/root/root-context.xml"})
public class MemberTest {
	@Inject
	MemberMapper memberMapper;
	
	@Test
	public void testMember() {
		System.out.println("testMember : "+memberMapper);
		MemberVO vo = memberMapper.read("master45");
		if(vo != null) {
			System.out.println(vo.toString());
		}
	}
}
