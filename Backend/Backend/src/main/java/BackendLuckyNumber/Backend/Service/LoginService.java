package BackendLuckyNumber.Backend.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import BackendLuckyNumber.Backend.GenJwt;
import BackendLuckyNumber.Backend.TokenManager;
import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.RefreshToken;
import BackendLuckyNumber.Backend.Modal.TokenModal;
import BackendLuckyNumber.Backend.Modal.UserModal;

//import BackendLuckyNumber.Backend.Repo.InfoUserRepo;
import BackendLuckyNumber.Backend.Repo.UserRepo;
import BackendLuckyNumber.Backend.RequestModel.JwtRequestModel;
import BackendLuckyNumber.Backend.RequestModel.LoginReqModel;
import BackendLuckyNumber.Backend.ResponseModel.LoginResModal;
import BackendLuckyNumber.Backend.ResponseModel.UserLoginResModal;

@Service
public class LoginService {

	@Autowired UserRepo userRepo;
	@Autowired TokenManager jwt;
//	@Autowired private JwtUserDetailsService userDetailsService;
	// @Autowired InfoUserRepo infoUserRepo;

	public UserLoginResModal validateLoginService(JwtRequestModel userLogin) {
		List<UserModal> arrdataUser = new ArrayList<UserModal>();
//		InfoUserModal infoUser = new InfoUserModal();
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		UserModal dataUser = new UserModal();
		UserLoginResModal res  = new UserLoginResModal();
		TokenModal token = new TokenModal();
		Boolean validatepass = false;
		String status = "A";
		String passUser = "";
//		String jwtToken = "";
		try {

			dataUser = userRepo.findidUser(userLogin.getUsername());
			// final UserDetails userDetails =
			// userDetailsService.loadUserByUsername(userLogin.getUsername());

			if (!StringUtils.isEmpty(dataUser)) {

				validatepass = b.matches(userLogin.getPassword(), dataUser.getPassword());
				if (validatepass) {
					RefreshToken jwtToken = jwt.generateJwtToken(dataUser);
//					token.setAccessToken(jwtToken);
					LocalDateTime myDateObj = LocalDateTime.now();
					DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					String formattedDate = myDateObj.format(myFormatObj);
					System.out.println("token is " + jwtToken);
					if (dataUser.getStatus().equals("I") || dataUser.getStatus().equals("A")) {

						dataUser.setStatus(status);
						dataUser.setTimelogin(formattedDate);
						dataUser.setToken(jwtToken.getAccessToken());
						userRepo.updateStatusLoginUser(status, formattedDate, dataUser.getToken(),userLogin.getUsername(),dataUser.getPassword());
						res.setUsername(dataUser.getIduser());
						res.setAccessToken(jwtToken.getAccessToken());
						res.setRefreshToken(jwtToken.getRefreshToken());
						res.setStatus("A");
						res.setRole(dataUser.getRole());
					}
					// case status == lock, user will not be able to access web site
					arrdataUser.add(dataUser);

				} else {
					res.setStatus("invalid");
//					arrdataUser.add(dataUser);
				}

			} 
		} catch (Exception e) {
			System.out.println("Service LoginUser " + e);
		}

		return res;

	}

//	public  InfoUserModal getInfoUser()
//	{
//		InfoUserModal infoUser = new InfoUserModal();
//		 try {
//			  infoUser = infoUserRepo.findByIdSeller();
//		 }catch(Exception e)
//		 {
//			 System.out.print("Error happen is  = "+e );
//		 }
//		
//		return infoUser;
//	}

	public UserModal getUser(String username) {
		UserModal userDetails = userRepo.findidUser(username);
		return userDetails;
	}

	public Boolean validateLogout(LoginReqModel userLogin) {
		List<UserModal> arrdataUser = new ArrayList<UserModal>();
		UserModal dataUser = new UserModal();
		Boolean validateToken = false;
		String status = "I";
		Boolean updateStatus = false;
		try {

			dataUser = userRepo.findTokenUser(userLogin.getToken());
			if (!StringUtils.isEmpty(dataUser)) {
				validateToken = validateToken(userLogin.getToken(), dataUser.getToken());
				if (validateToken) {
					LocalDateTime myDateObj = LocalDateTime.now();
					DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					String formattedDate = myDateObj.format(myFormatObj);
					userRepo.updateStatusLogoutUser(status, formattedDate, dataUser.getToken());
					updateStatus = true;
				}

			}
		} catch (Exception e) {
			System.out.println("Service LoginUser " + e);
		}

		return updateStatus;

	}

	public Boolean comparePass(String pass, String tokenPass) {
		GenJwt genjwt = new GenJwt();
		String decodePass = genjwt.deCode(tokenPass);
		System.out.println("decode = " + decodePass);
		if (pass.equals(decodePass)) {
			return true;
		}

		return false;
	}

	public Boolean validateToken(String tokenUser, String TokeDB) {
		GenJwt genjwt = new GenJwt();
		String decodeTokenDB = genjwt.deCode(TokeDB);
		String decodeTokenUser = genjwt.deCode(tokenUser);
		System.out.println("decode = " + tokenUser);
		if (TokeDB.equals(tokenUser)) {
			return true;
		}
		return false;
	}

	public UserModal register(LoginReqModel userLogin) {
		UserModal dataUser = new UserModal();
		GenJwt genjwt = new GenJwt();
		UserModal usermodal = new UserModal();
		String encodedPassword = new BCryptPasswordEncoder().encode(userLogin.getPassword());
//		String tokenpass = "";
//		Boolean DuplicateRegister = false;
		// String encodedPassword = BCryptPasswordDecoder(userLogin.getPassword());
		try {
			dataUser = userRepo.findidUser(userLogin.getIduser());
			if (!StringUtils.isEmpty(dataUser)) {
				dataUser = null;
				return dataUser;
			} else {
//				tokenpass = genjwt.encodeData(userLogin.getPassword());
				usermodal.setIduser(userLogin.getIduser());
				usermodal.setPassword(encodedPassword);
				usermodal.setRole(userLogin.getRole());
				usermodal.setNickname(userLogin.getNickname());
				usermodal.setStatus("I");
				userRepo.save(usermodal);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return usermodal;

	}
}
