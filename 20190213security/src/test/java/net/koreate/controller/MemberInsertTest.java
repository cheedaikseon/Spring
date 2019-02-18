package net.koreate.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations= {
	"classpath:context/root/root-context.xml",
	"classpath:context/root/security/security-context.xml"
})
public class MemberInsertTest {

	@Inject
	PasswordEncoder pwdEncoder;
	
	@Inject
	DataSource ds;
	
	@Test
	public void testInsert() {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			System.out.println("연결 성공");
			String encodeStr = pwdEncoder.encode("비밀번호");
			// $2a$10$mp2aDT6DjKICFCvLgK3IFu9oG9cixCkjZphQ0PQfrHewowBcZRn1u
			// $2a$10$yGJbPtfWQkyG61gdG.J8w.jHR9Gk543vvYEOJw5b/rZgWZqbqSBzC
			// $2a$10$IrKwnwMVLJb45BxL8//8leo50jcSw9UC4GYcc7YF7duB/uNUL5lru
			// $2a$10$f8QoC7ZATiR6ROJswt.dHO7nJufFuziO0jDQeRI/J1zW8xzy9qCyq
			System.out.println(encodeStr);			
			
			String sql = "INSERT INTO security_member(uid,upw,uname) VALUES(?,?,?)";
			pstmt  = con.prepareStatement(sql);
			for(int i = 0; i<50; i++) {
				pstmt.setString(2, pwdEncoder.encode("pw"+i));
				if(i < 40) {
					pstmt.setString(1,"user"+i);
					pstmt.setString(3, "일반사용자 "+ i);
				}else if(i < 45) {
					pstmt.setString(1, "manager"+i);
					pstmt.setString(3, "운영자"+i);
				}else {
					pstmt.setString(1, "master"+i);
					pstmt.setString(3, "관리자"+i);
				}
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {if(pstmt != null) {pstmt.close();}} catch (SQLException e) {}
			try {if(con != null) {con.close();}} catch (SQLException e) {}
		}
	}
}
