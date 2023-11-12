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
import BackendLuckyNumber.Backend.Repo.TokenRepo;
//import BackendLuckyNumber.Backend.Repo.InfoUserRepo;
import BackendLuckyNumber.Backend.Repo.UserRepo;
import BackendLuckyNumber.Backend.RequestModel.JwtRequestModel;
import BackendLuckyNumber.Backend.RequestModel.LoginReqModel;
import BackendLuckyNumber.Backend.ResponseModel.LoginResModal;
import BackendLuckyNumber.Backend.ResponseModel.TokenModalRespones;
import BackendLuckyNumber.Backend.ResponseModel.UserLoginResModal;

@Service
public class LoginService {

	@Autowired UserRepo userRepo;
	@Autowired TokenManager jwt;
	@Autowired  TokenRepo tokenRepo;
	


	public UserLoginResModal validateLoginService(JwtRequestModel userLogin) {
		List<UserModal> arrdataUser = new ArrayList<UserModal>();
//		InfoUserModal infoUser = new InfoUserModal();
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		List<UserModal> dataUser = new ArrayList<>();
		UserLoginResModal res  = new UserLoginResModal();
		TokenModalRespones token = new TokenModalRespones();
		TokenModal addToken = new TokenModal();
		Boolean validatepass = false;
		String status = "A";
		
//		String jwtToken = "";
		Integer index = 0;
		try {

			dataUser = userRepo.findidUser(userLogin.getUsername());
			// final UserDetails userDetails =
			// userDetailsService.loadUserByUsername(userLogin.getUsername());

			if (!StringUtils.isEmpty(dataUser) && dataUser.size()>0) {

				for(UserModal data : dataUser)
				{
					validatepass = b.matches(userLogin.getPassword(), data.getPassword());
					if (validatepass) {
						RefreshToken jwtToken = jwt.generateJwtToken(dataUser.get(index).getIduser());
//						token.setAccessToken(jwtToken);
						LocalDateTime myDateObj = LocalDateTime.now();
						DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
						String formattedDate = myDateObj.format(myFormatObj);
						System.out.println("token is " + jwtToken);
						if (dataUser.get(index).getStatus().equals("I") || dataUser.get(index).getStatus().equals("A")) {
	
							dataUser.get(index).setStatus(status);
							dataUser.get(index).setTimelogin(formattedDate);
//							dataUser.get(index).setToken(jwtToken.getAccessToken());
							userRepo.updateStatusLoginUser(status, formattedDate,userLogin.getUsername(),dataUser.get(index).getPassword());
							res.setUsername(dataUser.get(index).getIduser());
							res.setAccessToken(jwtToken.getAccessToken());
							res.setRefreshToken(jwtToken.getRefreshToken());
							res.setStatus("A");
							res.setRole(dataUser.get(index).getRole());
							addToken.setToken(jwtToken.getAccessToken());
							addToken.setRefreshToken(jwtToken.getRefreshToken());
							addToken.setUser(dataUser.get(index).getId());
							arrdataUser.add(dataUser.get(index));
							tokenRepo.save(addToken);
							break;
						}
						// case status == lock, user will not be able to access web site
						

					} else {
						res.setStatus("invalid");
//						arrdataUser.add(dataUser);
					}
					index+=1;
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
		List<UserModal> userDetails = userRepo.findidUser(username);
		UserModal data = new UserModal();
		if(userDetails.size()>0)
		{
			
			data.setId(userDetails.get(0).getId());
			data.setIduser(userDetails.get(0).getIduser());
			data.setNickname(userDetails.get(0).getNickname());
			data.setPassword(userDetails.get(0).getPassword());
			data.setPercent(userDetails.get(0).getPercent());
			data.setRole(userDetails.get(0).getRole());
			data.setStatus(userDetails.get(0).getStatus());
		}
		return data;
	}

	public Boolean validateLogout(LoginReqModel userLogin) {
		List<UserModal> arrdataUser = new ArrayList<UserModal>();
		UserModal dataUser = new UserModal();
		Boolean validateToken = false;
		String status = "I";
		Boolean updateStatus = false;
		try {

			TokenModal token = tokenRepo.getToken(userLogin.getToken());
			if (!StringUtils.isEmpty(token)) {
//				validateToken = validateToken(userLogin.getToken(), dataUser.getToken());
				if (true) {
					LocalDateTime myDateObj = LocalDateTime.now();
					DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					String formattedDate = myDateObj.format(myFormatObj);
//					Integer resultDelete = tokenRepo.deleteToken(token.getIdToken());
					userRepo.updateStatusLogoutUser(status, formattedDate,userLogin.getIduser());
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
		List<UserModal> dataUser = new ArrayList<>();
		GenJwt genjwt = new GenJwt();
		UserModal usermodal = new UserModal();
		String encodedPassword = new BCryptPasswordEncoder().encode(userLogin.getPassword());
//		String tokenpass = "";
//		Boolean DuplicateRegister = false;
		// String encodedPassword = BCryptPasswordDecoder(userLogin.getPassword());
		try {
			dataUser = userRepo.findidUser(userLogin.getIduser());
			if (!StringUtils.isEmpty(dataUser) && dataUser.size()<1) {
				usermodal.setIduser(userLogin.getIduser());
				usermodal.setPassword(encodedPassword);
				usermodal.setRole(userLogin.getRole());
				usermodal.setNickname(userLogin.getNickname());
				usermodal.setStatus("I");
				userRepo.save(usermodal);
			} 
//				tokenpass = genjwt.encodeData(userLogin.getPassword());
				
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return usermodal;

	}
}
