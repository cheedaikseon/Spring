package net.koreate.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class MySqlConnectTest {
	
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Seoul";
	String USER = "java";
	String PASS = "java";
	
	@Test
	public void testConnection() {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Connection : " + con);
		} catch (ClassNotFoundException e) {
			System.out.println("����̹��� ã������.");
		} catch (SQLException e) {
			System.out.println("������ �ȵ�.");
		} finally{
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {}
		}
	}
	

}
