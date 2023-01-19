package BackendLuckyNumber.Backend.COntroller;

import java.util.ArrayList;
import java.util.List;

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
import BackendLuckyNumber.Backend.Modal.MonthModal;
import BackendLuckyNumber.Backend.Modal.UserModal;
import BackendLuckyNumber.Backend.RequestModel.LoginReqModel;
import BackendLuckyNumber.Backend.RequestModel.UserdetailsIml;
import BackendLuckyNumber.Backend.ResponseModel.InfoUserRespModal;
import BackendLuckyNumber.Backend.ResponseModel.MonthReponse;
import BackendLuckyNumber.Backend.Service.DashBoardService;
import BackendLuckyNumber.Backend.Until.ValidateUntil;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DashboardController extends ValidateUntil {

	@Autowired
	private DashBoardService dashBoardService;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private TokenManager jwt;

	@GetMapping("/getdashboard")
	public ResponseEntity testget(Authentication auth,HttpServletRequest req) {

		UserdetailsIml user = (UserdetailsIml) auth.getPrincipal();
		//InfoUserRespModal resp = new InfoUserRespModal();
		MonthModal Month_Each_Month = null;
		MonthReponse monthReponse = new MonthReponse();
		List<InfoUserModal> infoUser = new ArrayList<>();
		System.out.println(user.getInfoUser().getIduser());
		Header header = new Header();
		HttpStatus status = HttpStatus.OK;
		//System.out.println("check token = "+req.getSession().getAttribute("testToken"));
		try {
			 Month_Each_Month = dashBoardService.getInfoUser(user.getInfoUser().getId());
			//System.out.println("check length = "+Month_Each_Month.length);
			if (null != Month_Each_Month) {
				status = status.OK;
				header.setStatusCode(ConstantData.STATUS_CODE_200);
				header.setMessage(ConstantData.MESSAGE_SUCCESS);
//				resp.setHeader(header);
//				resp.setDataList(Month_Each_Month);
				
				monthReponse.setDataList(Month_Each_Month);
				monthReponse.setHeader(header);
			
			} else {
				status = status.OK;
				header.setStatusCode(ConstantData.STATUS_CODE_401);
				header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS);
				return new ResponseEntity(header, status);
			}
 
		} catch (Exception e) {
			System.out.println("ERROR DASHBOARD = " + e);
		}

		return new ResponseEntity(monthReponse, status);
	}

	@GetMapping("/testdata")
	public String test() {
		return "test";
	}

}
