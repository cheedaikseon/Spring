package net.koreate.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomNoOpPasswordEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		System.out.println("before encode : " + rawPassword.toString());
		/*String hashed = BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(12));
		System.out.println("hashed"+hashed);
		return hashed;*/
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		/*System.out.println("rawPassword : " + rawPassword.toString() + " : " + encodedPassword);
		return BCrypt.checkpw(rawPassword.toString(), encodedPassword);*/
		return rawPassword.toString().equals(encodedPassword);
	}
	
	
	
}
