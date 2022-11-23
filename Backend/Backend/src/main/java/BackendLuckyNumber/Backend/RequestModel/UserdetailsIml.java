package BackendLuckyNumber.Backend.RequestModel;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.UserModal;

public class UserdetailsIml implements UserDetails {
	private UserModal infoUser;
	private String username;
	

	
	public UserdetailsIml(UserModal infoUser)
	{
		this.infoUser = infoUser;
		
	}
	
	public UserModal getInfoUser() {
		return infoUser;
	}


	public void setInfoUser(UserModal infoUser) {
		this.infoUser = infoUser;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return infoUser.getIduser();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
