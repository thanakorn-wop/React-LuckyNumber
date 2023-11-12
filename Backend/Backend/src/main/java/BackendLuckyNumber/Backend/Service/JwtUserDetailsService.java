package BackendLuckyNumber.Backend.Service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.UserModal;
import BackendLuckyNumber.Backend.RequestModel.UserdetailsIml;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	LoginService loginService;
	// @Autowired InfoUserRepo infoUser;

	@Override
	public UserDetails loadUserByUsername(String idUser) throws UsernameNotFoundException {

		try {
			UserModal userDetails = loginService.getUser(idUser);
		
			if (null != userDetails ) {

				return new UserdetailsIml(userDetails);

			}

		} catch (Exception e) {
			System.out.println("ERROR loadUserByUsername");
		}
		return null;

	}
}