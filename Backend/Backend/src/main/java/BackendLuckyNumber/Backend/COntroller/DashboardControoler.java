package BackendLuckyNumber.Backend.COntroller;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BackendLuckyNumber.Backend.Header;
import BackendLuckyNumber.Backend.Constant.ConstantData;
import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.ResponseModel.InfoUserRespModal;
import BackendLuckyNumber.Backend.Service.DashBoardService;
import BackendLuckyNumber.Backend.Until.ValidateUntil;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DashboardControoler  extends ValidateUntil{
	
	@Autowired
	private DashBoardService dashBoardService;
	
	@GetMapping("/getdashboard")
	public ResponseEntity testget(@RequestHeader(value = "token") String token, HttpServletRequest req)
	{ 
		InfoUserRespModal resp = new  InfoUserRespModal();
		InfoUserModal infoUser = new  InfoUserModal();
		Header header = new Header();
		HttpStatus status = HttpStatus.OK;
		req.getSession(false);
		System.out.println("token = "+req.getSession().getAttribute(ConstantData.KEY_WEB_TOKEN));
		System.out.println("token font = "+token);
		Boolean validateToken = validateToken(req,token);
		
		if(validateToken)
		{
			infoUser = dashBoardService.getInfoUser(req.getSession().getAttribute("id").toString());
			if(null != infoUser)
			{
				status = status.OK;
				header.setStatusCode(ConstantData.STATUS_CODE_200);
				header.setMessage(ConstantData.MESSAGE_SUCCESS);
				resp.setBalance(infoUser.getBalance());
				resp.setCost(infoUser.getCost());
				resp.setHeader(header);
				resp.setTime(infoUser.getTime());
				resp.setTotalLostPrice(infoUser.getTotalLostPrice());
				resp.setTotalPurchase(infoUser.getTotalPurchase());
			}
			else {
				status = status.OK;
				header.setStatusCode(ConstantData.STATUS_CODE_200);
				header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS);
				resp.setHeader(header);
			}
		
		}
		else {
			header.setMessage(ConstantData.ERROR_MESSAGE_UNAUTHORIZED);
			header.setStatusCode(ConstantData.STATUS_CODE_403);
			status = status.FORBIDDEN;
			return new ResponseEntity(header,status);
			
		}
		return new ResponseEntity(resp,status);
	}
	@GetMapping("/testdata")
	public String test()
	{
		return "test";
	}

}
