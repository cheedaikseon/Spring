package net.koreate.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.dao.MemberDao;
import net.koreate.vo.MemberVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class InsertTest {

	@Inject
	MemberDao dao;
	
	@Test
	public void insertMemeber() throws Exception {
		MemberVo vo = new MemberVo();
		vo.setUserid("idid");
		vo.setUserpw("pwpw");
		vo.setUsername("이름");
		vo.setEmail("email@email.com");
		System.out.println(vo.toString());
		System.out.println("----------  dao : " + dao + " --------");
		/*
		dao.insertMember(vo);*/
	}
	
}
