package net.koreate.controller;


import javax.inject.Inject;


import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.apache.ibatis.session.SqlSession;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MyBatisTest {

	@Inject
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testConnection() {
		try(SqlSession session = sqlSessionFactory.openSession()){
			System.out.println("session생성 완료!!!! session : "+ session);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
