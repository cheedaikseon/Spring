package net.koreate.controller;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.dao.MemberDAO;
import net.koreate.vo.MemberVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class memberDaoTest {
	
	@Inject
	MemberDAO dao;
	
/*	@Test
	public void testInsertMember() {
		MemberVo member = new MemberVo();
		member.setUserid("user05");
		member.setUserpw("user05");
		member.setUsername("user05");
		dao.insertMember(member);
	}*/
	
	@Test
	public void testReadMember() {
		MemberVo m = dao.readMember("user01");
		System.out.println("testReadMember : "+ m);
		List<MemberVo> memberList = dao.readMemberList();
		for(MemberVo member :  memberList) {
			System.out.println("¸®½ºÆ®" + member);}
	}
	@Test
	public void testReadMemberWithPass() {
		MemberVo m2 = dao.readMemberWithPass("user01","user01");
		System.out.println("testReadMember : "+ m2);
		
	}
	
}
