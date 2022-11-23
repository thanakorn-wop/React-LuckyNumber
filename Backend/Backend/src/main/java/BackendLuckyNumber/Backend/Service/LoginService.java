package BackendLuckyNumber.Backend.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import BackendLuckyNumber.Backend.GenJwt;
import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.UserModal;
import BackendLuckyNumber.Backend.Repo.Base;
import BackendLuckyNumber.Backend.Repo.InfoUserRepo;
import BackendLuckyNumber.Backend.Repo.LoginRepo;
import BackendLuckyNumber.Backend.RequestModel.JwtRequestModel;
import BackendLuckyNumber.Backend.RequestModel.LoginReqModel;
import BackendLuckyNumber.Backend.ResponseModel.LoginResModal;

@Service
public class LoginService {
	
	@Autowired LoginRepo loginRepo;
	@Autowired Base base;
	@Autowired InfoUserRepo infoUserRepo;
	
	
	
	public List<UserModal> validateLoginService(JwtRequestModel userLogin)
	{
		List<UserModal> arrdataUser = new ArrayList<UserModal>();
		InfoUserModal infoUser = new InfoUserModal();
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		UserModal dataUser = new UserModal();
		Boolean validatepass = false;
		String status = "A";
		String passUser = "";
		try {
			
			dataUser = loginRepo.findidUser(userLogin.getUsername());
			if(!StringUtils.isEmpty(dataUser))
				 {
				  validatepass = b.matches(userLogin.getPassword(),dataUser.getPassword());
				  if(validatepass)
				  {
					LocalDateTime myDateObj = LocalDateTime.now();
					DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					String formattedDate = myDateObj.format(myFormatObj);
					System.out.println("check time = "+formattedDate);
					if(dataUser.getStatus().equals("I") || dataUser.getStatus().equals("A"))
					 {
					   
					   dataUser.setStatus(status);
					   dataUser.setTimelogin(formattedDate);
					   loginRepo.updateStatusLoginUser(status,formattedDate,dataUser.getToken());
					  // infoUser = infoUserRepo.findByIdSeller(dataUser.getId());
					   //System.out.print(infoUser.getTotalLostPrice());
					 }
					  arrdataUser.add(dataUser);
				  }
				  else {
					  dataUser.setPassword("invalid");
					  arrdataUser.add(dataUser);  
				  }
				  	    
				 }
			else {
				 arrdataUser.add(dataUser);  
			}
		}catch(Exception e)
		{
			System.out.println("Service LoginUser "+ e);
		}
		
	
		return arrdataUser;
		
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
	
	public UserModal getUser(String username)
	{
		UserModal userDetails = loginRepo.findidUser(username);
	 return userDetails;	
	}
	public Boolean validateLogout(LoginReqModel userLogin)
	{
		List<UserModal> arrdataUser = new ArrayList<UserModal>();
		UserModal dataUser = new UserModal();
		Boolean validateToken = false;
		String status = "I";
		Boolean updateStatus = false;
		try {
			
			dataUser = loginRepo.findTokenUser(userLogin.getToken());
			if(!StringUtils.isEmpty(dataUser))
				 {
				validateToken = validateToken(userLogin.getToken(),dataUser.getToken());
				  if(validateToken)
				  {
					LocalDateTime myDateObj = LocalDateTime.now();
					DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					String formattedDate = myDateObj.format(myFormatObj);
					loginRepo.updateStatusLogoutUser(status,formattedDate,dataUser.getToken());
					updateStatus = true;
				  }
				 
				  	    
				 }
		}catch(Exception e)
		{
			System.out.println("Service LoginUser "+ e);
		}
		
	
		return updateStatus;
		
	}
	
	public Boolean comparePass(String pass, String tokenPass)
	{
		GenJwt genjwt = new GenJwt();
		String decodePass = genjwt.deCode(tokenPass);
		System.out.println("decode = "+decodePass);
		if(pass.equals(decodePass))
		{
			return true;
		}
		
		return false;
	}
	
	public Boolean validateToken(String tokenUser , String TokeDB)
	{
		GenJwt genjwt = new GenJwt();
		String decodeTokenDB = genjwt.deCode(TokeDB);
		String decodeTokenUser = genjwt.deCode(tokenUser);
		System.out.println("decode = "+tokenUser);
		if(TokeDB.equals(tokenUser))
		{
			return true;
		}
		return false;
	}
	public UserModal register(LoginReqModel userLogin)
	{
		UserModal dataUser = new UserModal();
		GenJwt genjwt = new GenJwt();
		UserModal usermodal = new UserModal();
		 String encodedPassword = new BCryptPasswordEncoder().encode(userLogin.getPassword());
		String tokenpass = "";
		Boolean DuplicateRegister = false;
		// String encodedPassword = BCryptPasswordDecoder(userLogin.getPassword());
		try {
			dataUser = loginRepo.findidUser(userLogin.getIduser());
			if(!StringUtils.isEmpty(dataUser))
			{
					dataUser = null;
					 return dataUser;	
			}
			else {
				tokenpass = genjwt.encodeData(userLogin.getPassword());
				usermodal.setIduser(userLogin.getIduser());
				usermodal.setPassword(encodedPassword);
				usermodal.setStatus("I");
				loginRepo.save(usermodal);
			}
			
		
		}catch(Exception e){
			e.printStackTrace();		
		}
		return usermodal;
		
	}
}
