package BackendLuckyNumber.Backend;

import java.security.SecureRandom;
import java.sql.Date;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;

public class GenJwt {

		private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
		private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
		public static String generateNewToken() {
		    byte[] randomBytes = new byte[24];
		    secureRandom.nextBytes(randomBytes);
		    return base64Encoder.encodeToString(randomBytes);
		
		}
		
		public static String encodeData(String data) {
			  Base64.Encoder encoder = Base64.getEncoder();  
			String encodeStr = encoder.encodeToString(data.getBytes());  
		    return encodeStr;
		}
		
		public static String deCode(String data) {
			Base64.Decoder decoder = Base64.getUrlDecoder();  
			String decodeStr = new String(decoder.decode(data));  
			System.out.println("decode = "+decodeStr);
		    return decodeStr;
		}
	
}
