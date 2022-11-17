package BackendLuckyNumber.Backend;

import java.security.SecureRandom;
import java.sql.Date;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;

public class GenJwt {
		private static final String ALGORITHM_MD5 = "MD5";
		private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
		private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
		public  String generateNewToken(String username,String functionname) throws NoSuchAlgorithmException {
		 //   byte[] randomBytes = username.getBytes();
		    MessageDigest md = MessageDigest.getInstance(ALGORITHM_MD5);
		    if (null != username) {
            	md.update(username.getBytes());
            }
		    if (null != functionname) {
            	md.update(functionname.getBytes());
            }
		    return toHex(md.digest());
		
		}
		
		 private String toHex(byte[] buffer) {
		        StringBuffer sb = new StringBuffer(buffer.length * 2);
		        for (int i = 0; i < buffer.length; i++) {
		            sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
		            sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
		        }
		        return sb.toString();
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
