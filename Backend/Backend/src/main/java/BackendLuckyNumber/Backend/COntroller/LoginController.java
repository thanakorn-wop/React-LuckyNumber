package BackendLuckyNumber.Backend.COntroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import BackendLuckyNumber.Backend.GenJwt;
import BackendLuckyNumber.Backend.Header;
import BackendLuckyNumber.Backend.Modal.UserModal;
import BackendLuckyNumber.Backend.Repo.Base;
import BackendLuckyNumber.Backend.Repo.LoginRepo;
import BackendLuckyNumber.Backend.RequestModel.LoginReqModel;
import BackendLuckyNumber.Backend.ResponseModel.LoginResModal;
import BackendLuckyNumber.Backend.Service.LoginService;
import BackendLuckyNumber.Backend.Until.ConstantData;

import org.apache.commons.lang3.StringUtils;
@RestController
@CrossOrigin(origins = "*")
//@CrossOrigin("Access-Control-Allow-Origin: *")
@RequestMapping("/api")
public class LoginController {
		
	
//	public  WebMvcConfigurer configure()
//	{
//		return new WebMvcConfigurer()
//				{
//				 @Override
//				 public void addCorsMappings(CorsRegistry registry)
//				 {
//					 registry.addMapping("/*").allowedOrigins("http://localhost:3000/");
//				 }
//				};
//	}

	@Autowired LoginService loginService;
	//@Autowired LoginRepo loginRepo;
	//@Autowired Base base;
	
	@PostMapping("/validatelogin")
	public ResponseEntity validatelogin(@RequestBody LoginReqModel userLogin )
	{
		GenJwt genjwt = new GenJwt();
		String token = genjwt.generateNewToken();
		Header header = new Header();
		 HttpStatus status = HttpStatus.OK;
		 LoginResModal resp = new LoginResModal();
		List<UserModal> user = loginService.validateLogin(userLogin,token);
		if(null == user.get(0))
		{
			header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS);
			header.setStatusCode(ConstantData.STATUS_CODE_NOT_SUCCESS_00);
			resp.setHeader(header);
			status = status.OK;
		}
		else {
			if(user.get(0).getPassword().equals("invalid"))
			{
				header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS);
				header.setStatusCode(ConstantData.STATUS_CODE_NOT_SUCCESS_00);
				resp.setIduser(userLogin.getIduser());
				resp.setHeader(header);
				System.out.println("ok");
				status = status.OK;
			}
			else {
				header.setMessage(ConstantData.MESSAGE_SUCCESS);
				header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
				resp.setHeader(header);
				resp.setIduser(user.get(0).getIduser());
				resp.setToken(user.get(0).getToken());
				resp.setStatus(user.get(0).getStatus());
				resp.setTimelogin(user.get(0).getTimelogin());
				resp.setTimelogout(user.get(0).getTimelogout());
				
			}
			
		}
//		System.out.print(userLogin.getIduser());
//		UserModal user = new UserModal();
//		user.setIduser(userLogin.getIduser());
//		user.setPassword(userLogin.getPassword());
		//base.getAllEmployeesRowMapper();
		return new ResponseEntity(resp,status);
	}
	
	@PostMapping("/logout")
	public ResponseEntity validatelogout(@RequestBody LoginReqModel userLogin )
	{
		HttpStatus status = HttpStatus.OK;
		GenJwt genjwt = new GenJwt();
		String token = genjwt.generateNewToken();
		LoginResModal resp = new LoginResModal();
		System.out.println("token = "+userLogin.getToken());
		Boolean updateStatus = loginService.validateLogout(userLogin);
		Header header = new Header();
		if(updateStatus)
		{
			header.setStatusCode(ConstantData.STATUS_CODE_200);
			header.setMessage(ConstantData.MESSAGE_SUCCESS);
			resp.setIduser(userLogin.getIduser());
			resp.setHeader(header);
			status = status.OK;
			
		}
		else {
			status = status.INTERNAL_SERVER_ERROR;
		}
//		System.out.print(userLogin.getIduser());
//		UserModal user = new UserModal();
//		user.setIduser(userLogin.getIduser());
//		user.setPassword(userLogin.getPassword());
		//base.getAllEmployeesRowMapper();
		return new ResponseEntity(resp,status);
	}

	@GetMapping(value = "/testapi", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String getMessageResourceByLanguage() {
		  return null;
	    }
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody LoginReqModel userLogin )
	{
		LoginResModal resp = new LoginResModal();
		HttpStatus status = HttpStatus.OK;
		GenJwt genjwt = new GenJwt();
		String token = genjwt.generateNewToken();
		Boolean DuplicateRegister = false;
		Header header = new Header();
		Boolean validateRequest = validateRequest(userLogin);
		if(validateRequest)
		{
			token = genjwt.encodeData(token);
			UserModal resRepo = loginService.register(userLogin,token);
			if(null == resRepo)
			{
				status = status.BAD_REQUEST;
				header.setStatusCode(ConstantData.STATUS_CODE_400);
				header.setMessage(ConstantData.ERROR_MESSAGE_DUPLICATE_REGISTER);
				resp.setHeader(header);
			}
			else {
				status = status.OK;
				header.setMessage(ConstantData.MESSAGE_SUCCESS);
				header.setStatusCode(ConstantData.STATUS_CODE_200);
				resp.setIduser(resRepo.getId());
				resp.setHeader(header);
			}
		}
		else {
			status = status.BAD_REQUEST;
			header.setStatusCode(ConstantData.STATUS_CODE_400);
			header.setMessage(ConstantData.ERROR_MESSAGE_BAD_REQUEST);
			resp.setHeader(header);
		
		}
		
		return new ResponseEntity(resp,status);
	}
	
	public Boolean validateRequest(LoginReqModel userLogin)
	{
		Boolean validate = StringUtils.isNotBlank(userLogin.getIduser()) && StringUtils.isNotBlank(userLogin.getPassword()) ? true:false;
		return validate;
	}
}
