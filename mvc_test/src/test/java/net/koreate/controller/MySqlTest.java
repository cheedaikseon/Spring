package net.koreate.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

public class MySqlTest {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Seoul";
	String user = "java";
	String pass ="java";
	
	@Test
	public void testMysql() {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("db 연결 성공");
			con.setAutoCommit(false);
			String sql = "INSERT INTO test_board(title,content,writer) VALUES(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "첫번째 게시물");
			pstmt.setString(2, "냉무");
			pstmt.setString(3, "최기근");
			pstmt.executeUpdate();
			con.commit();			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스를 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("connection 연결 정보가 잘못되었습니다.");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {}
		}
		
	}
	
	

}
