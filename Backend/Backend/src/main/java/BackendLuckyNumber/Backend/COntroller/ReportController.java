package BackendLuckyNumber.Backend.COntroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BackendLuckyNumber.Backend.Header;
import BackendLuckyNumber.Backend.Constant.ConstantData;
import BackendLuckyNumber.Backend.Modal.TransferLottaryModal;
import BackendLuckyNumber.Backend.RequestModel.UserdetailsIml;
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
		List<TransferLottaryModal> allUser = new ArrayList<>();
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
			
		}
		return new ResponseEntity(res,status);
	}

}
