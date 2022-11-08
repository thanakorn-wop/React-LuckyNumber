package BackendLuckyNumber.Backend.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BackendLuckyNumber.Backend.Modal.UserModal;
import BackendLuckyNumber.Backend.Repo.LoginRepo;
import BackendLuckyNumber.Backend.RequestModel.LoginReqModel;

@Service
public class LoginService {
	
	@Autowired LoginRepo loginRepo;
	public List<UserModal> validateLogin(LoginReqModel userLogin)
	{
		 List<UserModal> dataUser = null;
		try {
			 dataUser = loginRepo.validateLogin(userLogin);
//			 if(null != dataUser && dataUser.size()>0)
//			 {
//				  	LocalDateTime myDateObj = LocalDateTime.now();
//				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//				    String formattedDate = myDateObj.format(myFormatObj);
//				    dataUser.get(0).setTimelogin(formattedDate);
//				    dataUser.get(0).setStatus("A");
//				    loginRepo.updateStatusLogin(dataUser.get(0));
//				 //update status to login website 
//				
//				 
//			 }
			
		}catch(Exception e)
		{
			System.out.println("Service LoginUser "+ e);
		}
		
	
		return 	dataUser;
		
	}

}
