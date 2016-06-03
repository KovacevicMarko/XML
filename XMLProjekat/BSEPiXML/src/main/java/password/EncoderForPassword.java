package password;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class EncoderForPassword {
	
	public EncoderForPassword() {}

	public static String getEncodedPassword(String password, String salt) {
		
		String generatedPassword = null;
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
		
		generatedPassword = encoder.encodePassword(password, salt);
		
		return generatedPassword;
	}
}
