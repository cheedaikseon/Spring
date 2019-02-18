package net.koreate.web;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class MysqlConnectTest {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/myData?serverTimezone=Asia/Seoul";
	String user = "java";
	String pass = "java";
	
	
	@Test
	public void test() {
		try {
			Class.forName(driver);
			try {
				Connection con = DriverManager.getConnection(url,user,pass);
				System.out.println(con);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("드라이버를 찾을 수 없습니다.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("연결 정보가 일치하지 않습니다");
		}
		
	}

}
