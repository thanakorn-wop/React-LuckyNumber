package BackendLuckyNumber.Backend.COntroller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import BackendLuckyNumber.Backend.Constant.ConstantData;
import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.TransferLottaryModal;
import BackendLuckyNumber.Backend.RequestModel.LuckyNumberReq;
import BackendLuckyNumber.Backend.RequestModel.UserdetailsIml;
import BackendLuckyNumber.Backend.ResponseModel.LuckyNumberResponModal;
import BackendLuckyNumber.Backend.ResponseModel.ResponseData;
import BackendLuckyNumber.Backend.Service.ReportService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ReportController {
	
	@Autowired
	ReportService reportService;
	
	

	@GetMapping("/gettransferuser")
	public ResponseEntity getAllUserTransfer(Authentication auth) {
		UserdetailsIml user = (UserdetailsIml) auth.getPrincipal();
		Header header = new Header();
		ResponseData res = new ResponseData();
		List<InfoUserModal> allUser = new ArrayList<>();
		HttpStatus status = HttpStatus.OK;
		if (user.getInfoUser().getIduser() != null) {
			allUser =  reportService.getAllUserService();
			header.setMessage(ConstantData.MESSAGE_SUCCESS);
			header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
			res.setHeader(header);
			res.setDatalist(allUser);
			
		}
		else {
			status = status.UNAUTHORIZED;
			header.setMessage(ConstantData.ERROR_MESSAGE_UNAUTHORIZED);
			header.setStatusCode(ConstantData.STATUS_CODE_NOT_SUCCESS_00);
			
		}
		return new ResponseEntity(res,status);
	}

	
	
	
	@PostMapping("/insertluckynumber")
	public ResponseEntity postLuckyNumber(@RequestBody LuckyNumberReq luckyNumberReq, Authentication auth)
			throws Exception {
		HttpStatus status = HttpStatus.OK;
		Boolean statusInsert;
		Header header = new Header();
		UserdetailsIml user = (UserdetailsIml) auth.getPrincipal();
		String userUpdate = user.getUsername();
		LuckyNumberResponModal resp = new LuckyNumberResponModal();
//		UserdetailsIml user = (UserdetailsIml) auth.getPrincipal();
		try {
			if(user.getInfoUser().getRole().equals(ConstantData.ADMIN))
			{
				if (validateRequestInsetNumberLucky(luckyNumberReq)) {
					statusInsert = reportService.postInsertNumberLuckyReportService(luckyNumberReq,userUpdate);
					if (statusInsert) {
//						Boolean statusUpdate = reportService.postUpdateLuckyNumberReportService(luckyNumberReq);
						
							status = HttpStatus.OK;
							header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
							header.setMessage(ConstantData.MESSAGE_SUCCESS_TH);
							header.setStatusProcess(true);
						}
					else {
						status = HttpStatus.OK;
						header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
						header.setMessage(ConstantData.MESSAGE_DUPLICATE_DATA_TH);
						header.setStatusProcess(false);
						
					}
				} else {
					status = HttpStatus.BAD_REQUEST;
					header.setStatusCode(ConstantData.STATUS_CODE_NOT_SUCCESS_00);
					header.setMessage(ConstantData.BAD_REQUEST);
				}
			}
			else {
				status = HttpStatus.UNAUTHORIZED;
				header.setStatusCode(ConstantData.STATUS_CODE_NOT_SUCCESS_00);
				header.setMessage(ConstantData.ERROR_MESSAGE_UNAUTHORIZED);
			}

		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity(header, status);
	}
	
	
	public  Boolean validateRequestInsetNumberLucky(LuckyNumberReq req)
	{
		Boolean statusValidate = StringUtils.isNotBlank(req.getThreetop()) && StringUtils.isNotBlank(req.getThreedown())
							&& StringUtils.isNotBlank(req.getTwodown()) && StringUtils.isNotBlank(req.getTwotop())
							&& StringUtils.isNotBlank(req.getDate()) && StringUtils.isNotBlank(req.getBiglucky())? true:false;
		return statusValidate;
	}
}
