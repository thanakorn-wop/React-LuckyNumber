package BackendLuckyNumber.Backend.COntroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import BackendLuckyNumber.Backend.Header;
import BackendLuckyNumber.Backend.RequestModel.LuckyNumberReq;
import BackendLuckyNumber.Backend.RequestModel.NumberRequestModel;
import BackendLuckyNumber.Backend.RequestModel.UserdetailsIml;
import BackendLuckyNumber.Backend.RequestModel.listNumberRquestModal;
import BackendLuckyNumber.Backend.ResponseModel.LuckyNumberResponModal;
import BackendLuckyNumber.Backend.ResponseModel.list_number_respModal;
import BackendLuckyNumber.Backend.Service.LottaryService;
import BackendLuckyNumber.Backend.Until.ValidateUntil;
import BackendLuckyNumber.Backend.Constant.ConstantData;
import BackendLuckyNumber.Backend.Modal.List_number_Modal;
import BackendLuckyNumber.Backend.Repo.List_numberRepo;

import org.apache.commons.lang3.StringUtils;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class LottaryController extends ValidateUntil {

	@Autowired
	LottaryService listLottaryService;

	@GetMapping("/getlistlottary/{date}")
	public ResponseEntity getListLottary(@PathVariable String date, Authentication auth) {
		UserdetailsIml user = (UserdetailsIml) auth.getPrincipal();
		list_number_respModal dataitem = new list_number_respModal();
		Header header = new Header();
		HttpStatus status = HttpStatus.OK;
		List<List_number_Modal> data = new ArrayList<>();
		System.out.println("date = "+date);
		try {
			 if(null !=user)
			 {
				 // set increase date for get list item
				 data =  listLottaryService.getLottaryService(user.getInfoUser().getId(),date);
				 if(null != data && data.size() >0)
				 {
					 header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
					 header.setMessage(ConstantData.MESSAGE_SUCCESS);
//					 header.setDatalist(data);
					 dataitem.setDatalist(data);
					 dataitem.setHeader(header);
				 }
			 }
		}catch(NullPointerException e)
		{
			throw e;
		}
		return new ResponseEntity(dataitem,status);
//		listLottaryService.get
	}

	@PostMapping("/insertluckynumber")
	public ResponseEntity postLuckyNumber(@RequestBody LuckyNumberReq luckyNumberReq,Authentication auth) throws Exception {
		HttpStatus status = HttpStatus.OK;
		Boolean statusInsert;
		Header header = new Header();
		LuckyNumberResponModal resp = new LuckyNumberResponModal();
//		UserdetailsIml user = (UserdetailsIml) auth.getPrincipal();
		try {
			if (validateRequestInsetNumberLucky(luckyNumberReq)) {
				statusInsert = listLottaryService.postInsertNumberLuckyService(luckyNumberReq);
				if (statusInsert) {
					Boolean statusUpdate = listLottaryService.postUpdateLuckyNumberService(luckyNumberReq);
					if(statusUpdate)
					{
						status = HttpStatus.OK;
						header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
						header.setMessage(ConstantData.MESSAGE_SUCCESS);
//						resp.setDate(luckyNumberReq.getDate());
//						resp.setThreedown(luckyNumberReq.getThreedown());
//						resp.setThreetop(luckyNumberReq.getThreetop());
//						resp.setTwodown(luckyNumberReq.getTwodown());
//						resp.setTwotop(luckyNumberReq.getTwotop());
//						header.setDatalist(resp);
						
					}
					else {
						status = HttpStatus.OK;
						header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
						header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS);
					}
				} else {
					status = HttpStatus.OK;
					header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
					header.setMessage(ConstantData.DUPLICATE_DATA);
				}
			} else {
				status = HttpStatus.BAD_REQUEST;
				header.setStatusCode(ConstantData.STATUS_CODE_NOT_SUCCESS_00);
				header.setMessage(ConstantData.BAD_REQUEST);
			}
		}catch(Exception e)
		{
			throw e;
		}
		return new ResponseEntity(header, status);
	}

	@PostMapping("/insertnumber")
	public ResponseEntity postInsertNumber(@RequestBody NumberRequestModel NumRequest,Authentication auth) throws Exception
	{
		HttpStatus status = HttpStatus.OK;
		Header header = new Header();
		UserdetailsIml user = (UserdetailsIml) auth.getPrincipal();
		Boolean statusUpdate = false;
		System.out.println("check user = "+user.getUsername());
		try
		{
			if(null != user)
			{
				if(validateAPI(NumRequest))
				{
					statusUpdate = listLottaryService.postInsertNumberService(NumRequest,user);
					if(statusUpdate)
					{
						header.setMessage(ConstantData.MESSAGE_SUCCESS);
						header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
					}
					else {
						header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS);
						header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
					}
				}
				else {
					throw new Exception("BAD Request ");
				}
			}
			
		}catch(NullPointerException e)
		{
			throw e;
		}
		
		
		return new ResponseEntity(header,status);
	}

	@PostMapping("/updatestatuspayment")
	public ResponseEntity postUpdateStatusPayment(@RequestBody listNumberRquestModal listRequest,Authentication auth )
	{
		HttpStatus status = HttpStatus.OK;
		Header header = new Header();
		if(null != listRequest && listRequest.getId() != null && listRequest.getIdlist() != null & listRequest.getStatuspayment() != null)
		{
			 Boolean updateStatus = listLottaryService.postUpdateStatusPaymentService(listRequest,auth);
			 if(updateStatus)
			 {
				 header.setMessage(ConstantData.MESSAGE_SUCCESS);
				 header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
				
			 }
		}
		else {
			status = HttpStatus.BAD_REQUEST;
			header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS);
			header.setStatusCode(ConstantData.STATUS_CODE_400);
		}
		return new ResponseEntity(header,status);
	}
	
	@PostMapping("/deletedata")
	public ResponseEntity postDeleteData(@RequestBody listNumberRquestModal listRequest,Authentication auth )
	{
		HttpStatus status = HttpStatus.OK;
		Header header = new Header();
		if(null != listRequest && listRequest.getId() != null && listRequest.getIdlist() != null)
		{
			 Boolean DeleteStatus = listLottaryService.postDeleteDataService(listRequest,auth);
			 if(DeleteStatus)
			 {
				 header.setMessage(ConstantData.MESSAGE_SUCCESS);
				 header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
				
			 }
		}
		else {
			status = HttpStatus.BAD_REQUEST;
			header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS);
			header.setStatusCode(ConstantData.STATUS_CODE_400);
		}
		return new ResponseEntity(header,status);
	}
	public Boolean validateAPI(NumberRequestModel NumRequest) {
		Boolean validate = false;

		try {

			if (StringUtils.isNotBlank(NumRequest.getDate()) || StringUtils.isNotBlank(NumRequest.getOption())
					|| StringUtils.isNotBlank(NumRequest.getNumber())
					|| StringUtils.isNotBlank(NumRequest.getPrice())) {
				validate = true;
			}
		} catch (NullPointerException e) {
			System.out.println("API is EMPTY or NULL ");
		}

		return validate;
	}

}
