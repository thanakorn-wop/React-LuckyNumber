package BackendLuckyNumber.Backend.COntroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BackendLuckyNumber.Backend.Header;
import BackendLuckyNumber.Backend.Constant.ConstantData;
import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.RequestModel.UserdetailsIml;
import BackendLuckyNumber.Backend.ResponseModel.InfoUserRespModal;

import BackendLuckyNumber.Backend.Service.SummaryService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class SummaryController {
	
	
	@Autowired SummaryService reportService;
	
	@GetMapping("/summary/{date}")
	public ResponseEntity getReport(@PathVariable String date,Authentication auth)
	{
		System.out.println("date = "+date);
		HttpStatus status = HttpStatus.OK;
		InfoUserRespModal response = new InfoUserRespModal();
		Boolean statusInsert;
		Header header = new Header();
		UserdetailsIml user = (UserdetailsIml) auth.getPrincipal();
	
		if(user != null)
		{
			Object result = reportService.getReportService(user.getInfoUser().getId(),user.getInfoUser().getNickname(),date, user.getInfoUser().getRole());
			if(null !=result)
			{
				header.setMessage(ConstantData.MESSAGE_SUCCESS_TH);
				header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
//				header.setStatusMessage(ConstantData.ALERT_MESSAGE_SUCCESS);
				response.setHeader(header);
				response.setDatalist(result);
				
			}
			else {
				header.setMessage(ConstantData.MESSAGE_NO_DATA_TH);
				header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
				header.setStatusMessage(ConstantData.ALERT_MESSAGE_ERROR);
				return new ResponseEntity(header, status);
			}
		}else {
			status = status.UNAUTHORIZED;
			header.setMessage(ConstantData.MESSAGE_ERROR);
			header.setStatusCode(ConstantData.STATUS_CODE_401);
		}
		return new ResponseEntity(response, status);
	}

}
