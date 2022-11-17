package BackendLuckyNumber.Backend.COntroller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import BackendLuckyNumber.Backend.GenJwt;
import BackendLuckyNumber.Backend.Header;
import BackendLuckyNumber.Backend.Constant.ConstantData;
import BackendLuckyNumber.Backend.Modal.InfoUserModal;
//import BackendLuckyNumber.Backend.TokenManager;
import BackendLuckyNumber.Backend.Modal.UserModal;
import BackendLuckyNumber.Backend.Repo.Base;
import BackendLuckyNumber.Backend.Repo.LoginRepo;
import BackendLuckyNumber.Backend.RequestModel.JwtRequestModel;
import BackendLuckyNumber.Backend.RequestModel.LoginReqModel;
import BackendLuckyNumber.Backend.ResponseModel.JwtResponseModel;
import BackendLuckyNumber.Backend.ResponseModel.LoginResModal;
//import BackendLuckyNumber.Backend.Service.JwtUserDetailsService;
import BackendLuckyNumber.Backend.Service.LoginService;
import BackendLuckyNumber.Backend.Until.ValidateUntil;
import org.apache.commons.lang3.StringUtils;

@RestController
@CrossOrigin(origins = "*")
//@CrossOrigin("Access-Control-Allow-Origin: *")
@RequestMapping("/api")
public class LoginController extends ValidateUntil {

//	  @Autowired
//	   private JwtUserDetailsService userDetailsService;
//	   @Autowired
//	   private AuthenticationManager authenticationManager;
//	   @Autowired
//	   private TokenManager tokenManager;

	@Autowired
	LoginService loginService;
	// @Autowired LoginRepo loginRepo;
	// @Autowired Base base;

//	 @PostMapping("/test")
//	 public ResponseEntity createToken(@RequestBody JwtRequestModel
//			   request) throws Exception {
//			      try {
//			         authenticationManager.authenticate(
//			            new
//			            UsernamePasswordAuthenticationToken(request.getUsername(),
//			            request.getPassword())
//			         );
//			      } catch (DisabledException e) {
//			         throw new Exception("USER_DISABLED", e);
//			      } catch (BadCredentialsException e) {
//			         throw new Exception("INVALID_CREDENTIALS", e);
//			      }
//			      final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
//			      final String jwtToken = tokenManager.generateJwtToken(userDetails);
//			      System.out.println("check jjj");
//			      return ResponseEntity.ok(new JwtResponseModel(jwtToken));
//			   }

	@GetMapping("/hello")
	public String hello(@RequestBody LoginReqModel qq, HttpServletRequest req) {
		GenJwt genjwt = new GenJwt();
		String token = req.getSession().getAttribute("token").toString();
		token = genjwt.deCode(token);
		String data = qq.getToken();
		data = genjwt.deCode(data);
		System.out.println("check token = " + token);
		System.out.println("check data token = " + data);
		if (data.equals(token)) {
			System.out.println("true");

		} else {
			System.out.println("false");
		}
		System.out.println("check hello =  " + req.getSession().getAttribute("token"));
		return "hello";
	}

	@PostMapping("/validatelogin")
	public ResponseEntity validatelogin(@RequestBody LoginReqModel userLogin, HttpServletRequest req) throws NoSuchAlgorithmException {
		//InfoUserModal infoUser = new InfoUserModal();
		GenJwt genjwt = new GenJwt();
	//	String token = genjwt.generateNewToken(userLogin.getIduser(),"validatelogin");
	//	token = genjwt.encodeData(token);
		Header header = new Header();
		HttpStatus status = HttpStatus.OK;
		LoginResModal resp = new LoginResModal();
		try {
			List<UserModal> user = loginService.validateLogin(userLogin);
			req.getSession().setAttribute(ConstantData.USER_DETAILS, user.get(0));
			if (null == user.get(0)) {
				header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS);
				header.setStatusCode(ConstantData.STATUS_CODE_NOT_SUCCESS_00);
				resp.setHeader(header);
				status = status.OK;
			} else {
				if (user.get(0).getPassword().equals("invalid")) {
					header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS);
					header.setStatusCode(ConstantData.STATUS_CODE_NOT_SUCCESS_00);
					resp.setIduser(userLogin.getIduser());
					resp.setHeader(header);
					System.out.println("ok");
					status = status.OK;

				} else {
					createWebTokenGenerator(req,"validatelogin");
//					infoUser = loginService.getInfoUser(user.get(0).getId());
					header.setMessage(ConstantData.MESSAGE_SUCCESS);
					header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
					resp.setHeader(header);
					resp.setIduser(user.get(0).getIduser());
					resp.setToken(req.getSession().getAttribute(ConstantData.KEY_WEB_TOKEN).toString());
					resp.setStatus(user.get(0).getStatus());
					resp.setTimelogin(user.get(0).getTimelogin());
					resp.setTimelogout(user.get(0).getTimelogout());
					req.getSession().setAttribute(ConstantData.USER_DETAILS, resp);	
					req.getSession().setAttribute("id", user.get(0).getId());
					System.out.println("req tokenDB = " + req.getSession().getAttribute(ConstantData.KEY_WEB_TOKEN));


					System.out.println("req tokenDB = " + req.getSession().getAttribute("tokenDB"));
					System.out.println("req tokenUser = " + req.getSession().getAttribute("tokenUser"));
					
				}

			}
		}catch(Exception e)
		{
			System.out.print("ERROR Login controller is = "+e);
		}
		
		return new ResponseEntity(resp, status);
	}

	@PostMapping("/logout")
	public ResponseEntity validatelogout(@RequestHeader("token") String token,@RequestBody LoginReqModel userLogin, HttpServletRequest req) {

		Boolean validateToken = validateToken(req,token);
		HttpStatus status = HttpStatus.OK;
		LoginResModal resp = new LoginResModal();
		Header header = new Header();
		Boolean updateStatus = false;
		if (validateToken) {
			updateStatus = loginService.validateLogout(userLogin);
			if (updateStatus) {
				header.setStatusCode(ConstantData.STATUS_CODE_200);
				header.setMessage(ConstantData.MESSAGE_SUCCESS);
				resp.setIduser(userLogin.getIduser());
				resp.setHeader(header);
				status = status.OK;

			} else {
				status = status.INTERNAL_SERVER_ERROR;
			}
		} else {
			status = status.UNAUTHORIZED;
		}

//		System.out.print(userLogin.getIduser());
//		UserModal user = new UserModal();
//		user.setIduser(userLogin.getIduser());
//		user.setPassword(userLogin.getPassword());
		// base.getAllEmployeesRowMapper();
		return new ResponseEntity(resp, status);
	}

	@GetMapping(value = "/testapi", produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getMessageResourceByLanguage() {
		return null;
	}

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody LoginReqModel userLogin) throws NoSuchAlgorithmException {
		LoginResModal resp = new LoginResModal();
		HttpStatus status = HttpStatus.OK;
		GenJwt genjwt = new GenJwt();
		//String token = genjwt.generateNewToken(userLogin.getIduser());
		Boolean DuplicateRegister = false;
		Header header = new Header();
		Boolean validateRequest = validateRequest(userLogin);
		if (validateRequest) {
			//token = genjwt.encodeData(token);
			UserModal resRepo = loginService.register(userLogin);
			if (null == resRepo) {
				status = status.BAD_REQUEST;
				header.setStatusCode(ConstantData.STATUS_CODE_400);
				header.setMessage(ConstantData.ERROR_MESSAGE_DUPLICATE_REGISTER);
				resp.setHeader(header);
			} else {
				status = status.OK;
				header.setMessage(ConstantData.MESSAGE_SUCCESS);
				header.setStatusCode(ConstantData.STATUS_CODE_200);
				resp.setIduser(resRepo.getId());
				resp.setHeader(header);
			}
		} else {
			status = status.BAD_REQUEST;
			header.setStatusCode(ConstantData.STATUS_CODE_400);
			header.setMessage(ConstantData.ERROR_MESSAGE_BAD_REQUEST);
			resp.setHeader(header);

		}

		return new ResponseEntity(resp, status);
	}

//	public Boolean validateRequest(LoginReqModel userLogin)
//	{
//		Boolean validate = StringUtils.isNotBlank(userLogin.getIduser()) && StringUtils.isNotBlank(userLogin.getPassword()) ? true:false;
//		return validate;
//	}
//	public Boolean validateToken(String tokenDB,String token)
//	{
//		
//		 GenJwt genjwt = new GenJwt();
//		 tokenDB = genjwt.deCode(tokenDB);
//		 token = genjwt.deCode(token);
//		 Boolean statusToken = false;
//		 if(tokenDB.equals(token))
//		 {
//			 statusToken = true;
//		 }
//		
//		return statusToken;
//	}
}
