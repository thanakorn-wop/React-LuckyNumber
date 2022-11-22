package BackendLuckyNumber.Backend.COntroller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BackendLuckyNumber.Backend.Header;
import BackendLuckyNumber.Backend.TokenManager;
//import BackendLuckyNumber.Backend.TokenManager;
import BackendLuckyNumber.Backend.Constant.ConstantData;
//import BackendLuckyNumber.Backend.JWT.JWT;
import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.UserModal;
import BackendLuckyNumber.Backend.RequestModel.LoginReqModel;
import BackendLuckyNumber.Backend.ResponseModel.InfoUserRespModal;
import BackendLuckyNumber.Backend.Service.DashBoardService;
import BackendLuckyNumber.Backend.Until.ValidateUntil;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DashboardControoler extends ValidateUntil {

	@Autowired
	private DashBoardService dashBoardService;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private TokenManager jwt;

	@GetMapping("/getdashboard")
	public ResponseEntity testget( Authentication auth) {
//		LoginReqModel qq = (LoginReqModel) auth.getPrincipal();
//		System.out.println("check user =  " + token);
//		if (token != null && token.startsWith("Bearer ")) {
//			token = token.substring(10);
//		}
		// Object user = auth.getPrincipal().toString();
		UserDetails user = (UserDetails) auth.getPrincipal();
		InfoUserRespModal resp = new InfoUserRespModal();
		InfoUserModal infoUser = new InfoUserModal();
		Header header = new Header();
		HttpStatus status = HttpStatus.OK;
	//	Object qq =jwt.getUsernameFromToken(token);
	//	System.out.println(qq);
//		userDetailsService.loadUserByUsername(null)
//	
//		 Boolean validateToken = validateToken(req,token);
	
		try {
			infoUser = dashBoardService.getInfoUser(user.getUsername());
			if (null != infoUser) {
				status = status.OK;
				header.setStatusCode(ConstantData.STATUS_CODE_200);
				header.setMessage(ConstantData.MESSAGE_SUCCESS);
				resp.setHeader(header);
				resp.setBalance(infoUser.getBalance());
				resp.setCost(infoUser.getCost());
				resp.setTime(infoUser.getTime());
				resp.setTotalLostPrice(infoUser.getTotalLostPrice());
				resp.setTotalPurchase(infoUser.getTotalPurchase());
			} else {
				status = status.OK;
				header.setStatusCode(ConstantData.STATUS_CODE_401);
				header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS);
				return new ResponseEntity(header, status);
			}

		} catch (Exception e) {
			System.out.println("ERROR DASHBOARD = " + e);
		}

		return new ResponseEntity(resp, status);
	}

	@GetMapping("/testdata")
	public String test() {
		return "test";
	}

}
