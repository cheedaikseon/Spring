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
				System.out.println("����̹��� ã�� �� �����ϴ�.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("���� ������ ��ġ���� �ʽ��ϴ�");
		}
		
	}

}
