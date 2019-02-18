package net.koreate.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomNoOpPasswordEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		System.out.println("============= CustomNoOpPasswordEncoder -> encode =============\r\n");
		System.out.println("before encoder : " + rawPassword.toString());
		String hashed = BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(12));
		System.out.println("hashed : " + hashed);
		System.out.println("\r\n================================================================\r\n");
				return hashed;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		System.out.println("============= CustomNoOpPasswordEncoder -> matches =============\r\n");
		System.out.println("rawPassword : " + rawPassword + ": " + encodedPassword );
		System.out.println("\r\n=================================================================\r\n");
		return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
		//return rawPassword.toString().equals(encodedPassword);
	}
}
