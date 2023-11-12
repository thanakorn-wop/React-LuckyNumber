package BackendLuckyNumber.Backend.ResponseModel;

import javax.persistence.Column;

public class TokenResponseModal {
	
	private String idToken;
	
	private String token;
	
	private String tokenExpired;
	
	private String refreshToken;
	
	private String refreshTokenExpired;
	
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
