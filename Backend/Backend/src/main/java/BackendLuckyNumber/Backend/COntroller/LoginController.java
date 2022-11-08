package BackendLuckyNumber.Backend.COntroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BackendLuckyNumber.Backend.Modal.UserModal;
import BackendLuckyNumber.Backend.Repo.LoginRepo;
import BackendLuckyNumber.Backend.RequestModel.LoginReqModel;
import BackendLuckyNumber.Backend.Service.LoginService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoginController {
	
	@Autowired LoginService loginService;
	@Autowired LoginRepo loginRepo;
	
	@PostMapping("/validatelogin")
	public List<UserModal> validatelogin(@RequestBody LoginReqModel userLogin )
	{
//		System.out.print(userLogin.getIduser());
//		UserModal user = new UserModal();
//		user.setIduser(userLogin.getIduser());
//		user.setPassword(userLogin.getPassword());
		return  loginService.validateLogin(userLogin);
	}

//	  @GetMapping(value = "/testapi", produces = {MediaType.APPLICATION_JSON_VALUE})
//	    public String getMessageResourceByLanguage() {
//		  return null;
//	    }
}
