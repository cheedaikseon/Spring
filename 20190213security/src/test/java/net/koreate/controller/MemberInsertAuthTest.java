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
public class MemberInsertAuthTest {

	@Inject
	DataSource ds;
	
	@Test
	public void testInsert() {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			String sql = "INSERT INTO security_member_auth(uid,auth) VALUES(?,?)";
			pstmt  = con.prepareStatement(sql);
			for(int i = 0; i<50; i++) {
				if(i < 40) {
					pstmt.setString(1,"user"+i);
					pstmt.setString(2, "ROLE_USER");
				}else if(i < 45) {
					pstmt.setString(1, "manager"+i);
					pstmt.setString(2, "ROLE_MEMBERSHIP");
				}else {
					pstmt.setString(1, "master"+i);
					pstmt.setString(2, "ROLE_MASTER");
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
