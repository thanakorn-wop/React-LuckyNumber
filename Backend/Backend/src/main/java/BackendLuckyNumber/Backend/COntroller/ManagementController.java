package BackendLuckyNumber.Backend.COntroller;

import java.util.ArrayList;
import java.util.List;

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
import BackendLuckyNumber.Backend.Modal.UserModal;
import BackendLuckyNumber.Backend.RequestModel.UserRequestModal;
import BackendLuckyNumber.Backend.RequestModel.UserdetailsIml;
import BackendLuckyNumber.Backend.ResponseModel.ResponseData;
import BackendLuckyNumber.Backend.ResponseModel.UserResModal;
import BackendLuckyNumber.Backend.Service.ManagementService;
import BackendLuckyNumber.Backend.Until.ValidateUntil;
import antlr.StringUtils;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ManagementController extends ValidateUntil {
	
	
	@Autowired ManagementService managementService;
	
	@GetMapping("/getuser")
	public ResponseEntity getAllUser(Authentication auth)
	{
		UserdetailsIml user = (UserdetailsIml) auth.getPrincipal();
		String role = user.getInfoUser().getRole();
		List<UserResModal> respModal = new  ArrayList<>();
		ResponseData respon = new ResponseData();
		Header header = new Header();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if(role.equals(ConstantData.ADMIN))
		{
			respModal = managementService.getAllUserService();
			status = status.OK;
			header.setMessage(ConstantData.MESSAGE_SUCCESS);
			header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
			respon.setHeader(header);
			respon.setDatalist(respModal);
			
		}
		else {
			header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS);
			header.setStatusCode(ConstantData.STATUS_CODE_NOT_SUCCESS_00);
			respon.setHeader(header);
		
			
		}
		return new ResponseEntity(respon, status);
	}
	
	@PostMapping("/lockuser")
	public ResponseEntity postLockUser(Authentication auth,@RequestBody UserRequestModal userModal)
	{
		UserdetailsIml user = (UserdetailsIml) auth.getPrincipal();
		String role = user.getInfoUser().getRole();
		List<UserResModal> respModal = new  ArrayList<>();
		ResponseData respon = new ResponseData();
		Header header = new Header();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if(role.equals(ConstantData.ADMIN))
		{
			if(null != userModal.getIduser())
			{
				Boolean statusLockUser = managementService.postLockUser(userModal);
				if(statusLockUser)
				{
					status = status.OK;
					header.setMessage(ConstantData.MESSAGE_SUCCESS);
					header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
					respon.setHeader(header);
					
				}
				else {
					status = status.INTERNAL_SERVER_ERROR;
					header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS);
					header.setStatusCode(ConstantData.STATUS_CODE_NOT_SUCCESS_00);
					respon.setHeader(header);
				}
			}
			else {
				status = status.BAD_REQUEST;
				header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS);
				header.setStatusCode(ConstantData.STATUS_CODE_NOT_SUCCESS_00);
				respon.setHeader(header);
			}
			
		}
		else {
			header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS);
			header.setStatusCode(ConstantData.STATUS_CODE_NOT_SUCCESS_00);
			respon.setHeader(header);
		
			
		}
		return new ResponseEntity(respon, status);
	}

}
