package BackendLuckyNumber.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import BackendLuckyNumber.Backend.Service.JwtUserDetailsService;
@Configuration
public class Authentication {
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private TokenManager tokenManager;
	public Boolean validateToken(String token)
	{
		UserDetails userDetails = userDetailsService.loadUserByUsername(token);
		
		return tokenManager.validateJwtToken(token, userDetails);
	}

}
