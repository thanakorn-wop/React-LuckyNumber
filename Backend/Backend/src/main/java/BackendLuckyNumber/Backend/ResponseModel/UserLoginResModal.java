package BackendLuckyNumber.Backend.ResponseModel;

import BackendLuckyNumber.Backend.Modal.RefreshToken;
import BackendLuckyNumber.Backend.Modal.TokenModal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResModal extends TokenModal {
	private String username;
	private String role;
	private String status;
	
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	

}
