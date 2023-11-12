package BackendLuckyNumber.Backend.Modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "token")
public class TokenModal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_token")
	private String idToken;
	@Column(name = "token")
	private String token;
	@Column(name = "token_expired")
	private String tokenExpired;
	@Column(name = "refresh_token")
	private String refreshToken;
	@Column(name = "refresh_token_expired")
	private String refreshTokenExpired;
	@Column(name = "user")
	private String user;
	
	public String getIdToken() {
		return idToken;
	}
	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getTokenExpired() {
		return tokenExpired;
	}
	public void setTokenExpired(String tokenExpired) {
		this.tokenExpired = tokenExpired;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getRefreshTokenExpired() {
		return refreshTokenExpired;
	}
	public void setRefreshTokenExpired(String refreshTokenExpired) {
		this.refreshTokenExpired = refreshTokenExpired;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
}
