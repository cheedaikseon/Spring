package net.koreate.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.dao.MemberDAO;
import net.koreate.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberDaoTest {
	
	@Inject	
	MemberDAO dao;
	
	@Test
	public void testSession() {
		dao.insertMember(new MemberVO("id004","pw004","최기근","hap0p9y@nate.com"));	
	}

}
