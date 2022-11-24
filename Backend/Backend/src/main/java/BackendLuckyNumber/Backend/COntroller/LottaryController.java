package BackendLuckyNumber.Backend.COntroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BackendLuckyNumber.Backend.Header;
import BackendLuckyNumber.Backend.RequestModel.LuckyNumberReq;
import BackendLuckyNumber.Backend.RequestModel.UserdetailsIml;
import BackendLuckyNumber.Backend.Service.LottaryService;
import BackendLuckyNumber.Backend.Until.ValidateUntil;
import BackendLuckyNumber.Backend.Constant.ConstantData;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class LottaryController extends ValidateUntil {
	
	@Autowired LottaryService listLottaryService;
	
	@GetMapping("/getlistlottary")
	public void getListLottary(Authentication auth)
	{
		UserdetailsIml user = (UserdetailsIml) auth.getPrincipal();
		
//		listLottaryService.get
	}
	
	@PostMapping("/insertluckynumber")
	public ResponseEntity postLuckyNumber(@RequestBody LuckyNumberReq luckyNumberReq) throws Exception
	{
		HttpStatus status = HttpStatus.OK;
		Boolean statusInsert;
		Header header = new Header();
		if(validateRequestInsetNumberLucky(luckyNumberReq))
		{
			statusInsert = listLottaryService.postInsertNumberLuckyService(luckyNumberReq);
			if(statusInsert)
			{
				status = HttpStatus.OK;
				header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
				header.setMessage(ConstantData.MESSAGE_SUCCESS);
			}
			else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				header.setStatusCode(ConstantData.STATUS_CODE_NOT_SUCCESS_00);
				header.setMessage(ConstantData.DUPLICATE_DATA);
			}
		}
		else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity(header,status);
	}

}
