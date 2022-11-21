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
	public ResponseEntity testget(@RequestHeader(value = "Authorization") String token) {
//		LoginReqModel qq = (LoginReqModel) auth.getPrincipal();
		System.out.println("check user =  " + token);
		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(10);
		}

		String user = jwt.getUsernameFromToken(token);
		System.out.println("check user =  " + user);
//		System.out.println("check user =  "+qq.getToken());
		InfoUserRespModal resp = new InfoUserRespModal();
		InfoUserModal infoUser = new InfoUserModal();
		Header header = new Header();
		HttpStatus status = HttpStatus.OK;
//		req.getSession(false);
//		userDetailsService.loadUserByUsername(null)
//		System.out.println("token 1111= " + req.getSession().getAttribute("id"));
//		System.out.println("token 1111= " + req.getSession().getAttribute(ConstantData.USER_DETAILS));
//		System.out.println("token 1111= " + req.getSession().getAttribute("iduser"));
		// System.out.println("token 1111= "+);
		// System.out.println("token font = "+token);
//		 Boolean validateToken = validateToken(req,token);

		if (true) {
			infoUser = dashBoardService.getInfoUser(user);
			if (null != infoUser) {
				status = status.OK;
				header.setStatusCode(ConstantData.STATUS_CODE_200);
				header.setMessage(ConstantData.MESSAGE_SUCCESS);
				resp.setBalance(infoUser.getBalance());
				resp.setCost(infoUser.getCost());
				resp.setHeader(header);
				resp.setTime(infoUser.getTime());
				resp.setTotalLostPrice(infoUser.getTotalLostPrice());
				resp.setTotalPurchase(infoUser.getTotalPurchase());
			} else {
				status = status.OK;
				header.setStatusCode(ConstantData.STATUS_CODE_200);
				header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS);
				resp.setHeader(header);
			}

		} else {
			header.setMessage(ConstantData.ERROR_MESSAGE_UNAUTHORIZED);
			header.setStatusCode(ConstantData.STATUS_CODE_403);
			status = status.FORBIDDEN;
			return new ResponseEntity(header, status);

		}
		return new ResponseEntity(resp, status);
	}

	@GetMapping("/testdata")
	public String test() {
		return "test";
	}

}
