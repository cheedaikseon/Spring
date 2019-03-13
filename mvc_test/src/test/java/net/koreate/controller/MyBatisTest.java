package net.koreate.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MybatisTest {
	
	@Inject
	SqlSessionFactory sqlFactory;
	
	@Test
	public void testFactory() {
		System.out.println("sqlFactory : "+sqlFactory);
	}
	
	@Test
	public void testSession() {
		
		SqlSession session = sqlFactory.openSession();
		System.out.println("sqlSession : " + session);
		/*String namespace = "net.koreate.Member.insertMember";
		session.insert(namespace);*/
		
		Map<String,String> map = new HashMap<>();
		map.put("userid", "id001");
		map.put("userpw", "pw001");
		map.put("username", "최기근");
		map.put("email", "hap0p9y@nate.com");
		
		String namespace = "net.koreate.Member";
		
		session.insert(namespace+".insertMember",map);
		
		
	}

}
