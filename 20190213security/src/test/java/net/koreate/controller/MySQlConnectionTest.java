package net.koreate.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations= {
		"classpath:context/root/root-context.xml"
})
public class MySQlConnectionTest {
	
	@Inject
	DataSource ds;
	
	@Test
	public void testConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();
			System.out.println("dataSource 연결 성공 : " + con);
		} catch (SQLException e) {
			System.out.println("연결 정보 오류 : "+ e.getMessage());
		} finally {
			try {
				if(con != null) {con.close();}
			} catch (SQLException e) {}
		}
	}
}
