package net.koreate.sboard;


import javax.inject.Inject;


import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.apache.ibatis.session.SqlSession;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources/context/root-context.xml"})
public class MyBatisTest {

	@Inject
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testConnection() {
		try(SqlSession session = sqlSessionFactory.openSession()){
			System.out.println("연결 정보 객체 생성 완료 : " + session);
			System.out.println("con : "+ session.getConnection());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
