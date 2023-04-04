package BackendLuckyNumber.Backend.COntroller;

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
import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.RequestModel.UserdetailsIml;
import BackendLuckyNumber.Backend.ResponseModel.InfoUserRespModal;
import BackendLuckyNumber.Backend.Service.ReportService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ReportController {
	
	
	@Autowired ReportService reportService;
	
	@GetMapping("/report")
	public ResponseEntity getReport(Authentication auth)
	{
		HttpStatus status = HttpStatus.OK;
		InfoUserRespModal response = new InfoUserRespModal();
		Boolean statusInsert;
		Header header = new Header();
		UserdetailsIml user = (UserdetailsIml) auth.getPrincipal();
		InfoUserModal infoUser = new InfoUserModal();
		if(user != null)
		{
			infoUser = reportService.getReportService(user.getInfoUser().getId(),user.getInfoUser().getNickname());
			if(null !=infoUser)
			{
				header.setMessage(ConstantData.MESSAGE_SUCCESS);
				header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
				response.setHeader(header);
				response.setDatalist(infoUser);
				
			}
		}else {
			status = status.UNAUTHORIZED;
			header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS);
			header.setStatusCode(ConstantData.STATUS_CODE_401);
		}
		return new ResponseEntity(response, status);
	}

}
