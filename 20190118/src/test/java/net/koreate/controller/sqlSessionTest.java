package net.koreate.controller;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class sqlSessionTest {

	@Inject
	SqlSession session;
	
	@Test
	public void testSqlSession() {
		String time = session.selectOne("net.koreate.MemberMapper.getTime");
		System.out.println("sqlSession getTime : " + time);
	}
	
	
}
