package net.koreate.web;

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
	public void testConnection() {
		System.out.println("testConnect");
		try {
			Class.forName(driver);
				Connection con = null;
				try {
					con = DriverManager.getConnection(url, user, pass);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("con : " + con);
				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
