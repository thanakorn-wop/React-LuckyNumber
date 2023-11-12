package BackendLuckyNumber.Backend;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.RefreshToken;
import BackendLuckyNumber.Backend.Modal.TokenModal;
import BackendLuckyNumber.Backend.Modal.UserModal;
import BackendLuckyNumber.Backend.Repo.TokenRepo;
import BackendLuckyNumber.Backend.RequestModel.LoginReqModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.UUID;
@Component
public class TokenManager implements Serializable {
	/** 
	*
	*/
	private static final long serialVersionUID = 7008375124389347049L;
	public static final long TOKEN_VALIDITY = 10 * 60 * 60;
	@Value("${secret}")
	private String jwtSecret;
	

	@Autowired TokenRepo tokenRepo;

	public RefreshToken generateJwtToken(String idUser ) {
		String refreshToken = UUID.randomUUID().toString();
		RefreshToken token = new RefreshToken();
		Map<String, Object> claims = new HashMap<>();
		String newToken = accessToken(idUser,refreshToken);
		token.setAccessToken(newToken);
		token.setRefreshToken(refreshToken);
		return token;
	}

	public String accessToken(String idUser,String refreshToken)
	{
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().setClaims(claims).setSubject(idUser)
		.setId(refreshToken)
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
		.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public Boolean validateJwtToken(String token, UserDetails userDetails) {
		TokenModal tokenDb  = tokenRepo.getToken(token);
		if(StringUtils.isEmpty(tokenDb.getIdToken()))
		{
			throw new NullPointerException("Token is invalid");
		}
		String userName = getUsernameFromToken(token);
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		Boolean isTokenExpired = claims.getExpiration().before(new Date());
		System.out.println("isTokenExpired = "+isTokenExpired);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired);
	}

	public String getUsernameFromToken(String token) {
		final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
//	public RefreshToken refreshToken()
//	{
//		Map<String, Object> claims = new HashMap<>();
//		Jwts.builder().seti
//		return Jwts.builder().setClaims(claims).setSubject(userDetails.getIduser())
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
//				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
//	}
}