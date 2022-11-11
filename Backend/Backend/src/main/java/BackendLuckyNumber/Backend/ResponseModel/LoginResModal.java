package BackendLuckyNumber.Backend.ResponseModel;


import javax.persistence.Column;

import BackendLuckyNumber.Backend.Header;

public class LoginResModal {
	private Header header;
	private String iduser;
	private String timelogin;
	private String timelogout;
	private String status;
	private String token;
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTimelogin() {
		return timelogin;
	}
	public void setTimelogin(String timelogin) {
		this.timelogin = timelogin;
	}
	public String getTimelogout() {
		return timelogout;
	}
	public void setTimelogout(String timelogout) {
		this.timelogout = timelogout;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	
}
