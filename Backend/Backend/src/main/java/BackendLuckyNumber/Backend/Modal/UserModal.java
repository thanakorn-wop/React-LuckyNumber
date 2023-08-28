package BackendLuckyNumber.Backend.Modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import BackendLuckyNumber.Backend.RequestModel.UserdetailsIml;
import lombok.Data;
@Data
@Entity(name = "user")
public class UserModal  {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private String id;
	@Column(name = "iduser")
	private String iduser;
	@Column(name = "password")
	private String password;
	@Column(name = "token")
	private String token;
	@Column(name = "timelogin")
	private String timelogin;
	@Column(name = "timelogout")
	private String timelogout;
	@Column(name = "status")
	private String status;
	@Column(name = "role")
	private String role;
	@Column(name = "nickname")
	private String nickname;
	@Column(name = "percent")
	private String percent;
	
	
	


	

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}
