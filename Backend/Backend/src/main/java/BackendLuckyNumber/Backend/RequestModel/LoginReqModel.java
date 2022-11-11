package BackendLuckyNumber.Backend.RequestModel;

import BackendLuckyNumber.Backend.Header;

public class LoginReqModel {
	private Header header;
	private String iduser;
	private String password;
	private String token;
	
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public String getIduser() {
		return iduser;
	}
	public void setIduser(String iduser) {
		this.iduser = iduser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
