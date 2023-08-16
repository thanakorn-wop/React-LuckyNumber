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
import BackendLuckyNumber.Backend.RequestModel.DataSetModal;
import BackendLuckyNumber.Backend.RequestModel.LuckyNumberReq;
import BackendLuckyNumber.Backend.RequestModel.NumberRequestModel;
import BackendLuckyNumber.Backend.RequestModel.UserdetailsIml;
import BackendLuckyNumber.Backend.RequestModel.ValidateLottaryRequestModal;
import BackendLuckyNumber.Backend.RequestModel.listNumberRquestModal;
import BackendLuckyNumber.Backend.ResponseModel.LuckyNumberResponModal;
import BackendLuckyNumber.Backend.ResponseModel.ResponseData;
import BackendLuckyNumber.Backend.Service.LottaryService;
import BackendLuckyNumber.Backend.Until.ValidateUntil;
import BackendLuckyNumber.Backend.Constant.ConstantData;
import BackendLuckyNumber.Backend.Modal.List_number_Modal;
import BackendLuckyNumber.Backend.Modal.LottaryModal;
import BackendLuckyNumber.Backend.Modal.MixTransferListNumberModal;
import BackendLuckyNumber.Backend.Modal.SuccessAndFailModal;
import BackendLuckyNumber.Backend.Modal.UserModal;
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
		ResponseData dataitem = new ResponseData();
		Header header = new Header();
		HttpStatus status = HttpStatus.OK;
		List<List_number_Modal> data = new ArrayList<>();
		System.out.println("date = " + date);
		
		try {
			if (null != user) {
				// set increase date for get list item
				data = listLottaryService.getLottaryService(user.getInfoUser().getId(), date);
				if (null != data && data.size() > 0) {
					header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
					header.setMessage(ConstantData.MESSAGE_SUCCESS);
					header.setStatusProcess(true);
//					 header.setDatalist(data);
					dataitem.setDatalist(data);
					dataitem.setHeader(header);
				}
				else {
					
					header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
					header.setMessage(ConstantData.MESSAGE_EMPTY);
					header.setStatusProcess(true);
//					 header.setDatalist(data);
					dataitem.setDatalist(data);
					dataitem.setHeader(header);
				}
			}
		} catch (NullPointerException e) {
			throw e;
		}
		return new ResponseEntity(dataitem, status);
//		listLottaryService.get
	}

	@PostMapping("/insertnumber")
	public ResponseEntity postInsertNumber(@RequestBody DataSetModal NumRequest, Authentication auth)
			throws Exception {
		HttpStatus status = HttpStatus.OK;
		Header header = new Header();
		UserdetailsIml user = (UserdetailsIml) auth.getPrincipal();
		Boolean statusUpdate = false;
		System.out.println("check user = " + user.getUsername());
		try {
			if (null != user) {
			
				if (validateAPI(NumRequest)) {
					SuccessAndFailModal successAndFaiModal  = listLottaryService.postInsertNumberService(NumRequest, user);
					if (successAndFaiModal.getStatusSuccess()) {
						header.setMessage(successAndFaiModal.getMessage());
						header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
						header.setStatusProcess(true);
					} else {
						header.setMessage(successAndFaiModal.getMessage());
						header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
						header.setStatusProcess(false);
					}
				} else {
					status = status.BAD_REQUEST;
					header.setMessage(ConstantData.MESSAGE_ERROR);
					header.setStatusCode(ConstantData.STATUS_CODE_NOT_SUCCESS_00);
					
				}
			}

		} catch (NullPointerException e) {
			throw e;
		}

		return new ResponseEntity(header, status);
	}

	@PostMapping("/updatestatuspayment")
	public ResponseEntity postUpdateStatusPayment(@RequestBody listNumberRquestModal listRequest, Authentication auth) {
		HttpStatus status = HttpStatus.OK;
		UserdetailsIml user = (UserdetailsIml) auth.getPrincipal();
		UserModal userModal = new UserModal();
		Header header = new Header();
		if (null != listRequest && listRequest.getId() != null
				&& listRequest.getIdlist() != null & listRequest.getStatuspayment() != null) {
			Boolean updateStatus = listLottaryService.postUpdateStatusPaymentService(listRequest, userModal);
			if (updateStatus) {
				header.setMessage(ConstantData.MESSAGE_SUCCESS_TH);
				header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
				header.setStatusProcess(updateStatus);
			}
			else {
				header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS_TH);
				header.setStatusCode(ConstantData.STATUS_CODE_NOT_SUCCESS_00);
				header.setStatusProcess(updateStatus);
			}
		} else {
			status = HttpStatus.BAD_REQUEST;
			header.setMessage(ConstantData.MESSAGE_ERROR);
			header.setStatusCode(ConstantData.STATUS_CODE_400);
		}
		return new ResponseEntity(header, status);
	}

	@PostMapping("/deletedata")
	public ResponseEntity postDeleteData(@RequestBody listNumberRquestModal listRequest, Authentication auth) {
		HttpStatus status = HttpStatus.OK;
		Header header = new Header();
		if (null != listRequest && listRequest.getId() != null && listRequest.getIdlist() != null) {
			Boolean DeleteStatus = listLottaryService.postDeleteDataService(listRequest, auth);
			if (DeleteStatus) {
				header.setMessage(ConstantData.MESSAGE_SUCCESS_TH);
				header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
				header.setStatusProcess(true);

			}
			else {
				header.setMessage(ConstantData.MESSAGE_NOT_SUCCESS_TH);
				header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
				header.setStatusProcess(false);
			}
		} else {
			status = HttpStatus.BAD_REQUEST;
			header.setMessage(ConstantData.MESSAGE_ERROR);
			header.setStatusCode(ConstantData.STATUS_CODE_400);
		}
		return new ResponseEntity(header, status);
	}

	@PostMapping("/sendlottary")
	public ResponseEntity postSendLottary(@RequestBody listNumberRquestModal listRequest, Authentication auth) {
		HttpStatus status = HttpStatus.OK;
		Header header = new Header();
		UserdetailsIml user = (UserdetailsIml) auth.getPrincipal();
		if (null != user) {
			if (null != listRequest && listRequest.getLuckytime() != null) {
				MixTransferListNumberModal mixTransfer = listLottaryService.postSendLottaryService(listRequest,
						user.getInfoUser().getNickname(), user.getUsername(), user.getInfoUser().getId());
				if (mixTransfer.getDuplicateTransfer()) {
					header.setMessage(ConstantData.MESSAGE_DUPLICATE_DATA_TH);
					header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
					header.setStatusMessage(ConstantData.ALERT_MESSAGE_WARNING);
				}
				else if(null == mixTransfer.getLuckytime())
				{
					header.setMessage(ConstantData.MESSAGE_NOT_TIME_TO_WORK_TH);
					header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
					header.setStatusMessage(ConstantData.ALERT_MESSAGE_WARNING);
				}
				else if (mixTransfer.getListNumberModal().size() <= 0 || mixTransfer.getListNumberModal() == null) {
					header.setMessage(ConstantData.MESSAGE_NO_DATA_TH);
					header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
					header.setStatusMessage(ConstantData.ALERT_MESSAGE_WARNING);
				}
				else if (mixTransfer.getStatusValidate()) {
					header.setMessage(ConstantData.MESSAGE_BEFORE_VALIDATE_TH);
					header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
					header.setStatusMessage(ConstantData.ALERT_MESSAGE_WARNING);
				} 
				else {
					header.setMessage(ConstantData.MESSAGE_SUCCESS_TH);
					header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
					header.setStatusProcess(true);
					header.setStatusMessage(ConstantData.ALERT_MESSAGE_SUCCESS);
				}
			} else {
				status = HttpStatus.BAD_REQUEST;
				header.setMessage(ConstantData.MESSAGE_ERROR);
				header.setStatusCode(ConstantData.STATUS_CODE_400);
			}

		} else {
			status = HttpStatus.UNAUTHORIZED;
			header.setMessage(ConstantData.MESSAGE_ERROR);
			header.setStatusCode(ConstantData.STATUS_CODE_401);
		}
		return new ResponseEntity(header, status);
	}

	@GetMapping("/getluckyitem")
	public ResponseEntity getLuckyItem( Authentication auth) {
		HttpStatus status = HttpStatus.OK;
		Header header = new Header();
		ResponseData dataitem = new ResponseData();
		LottaryModal get_Item = listLottaryService.getLuckyItemService();
		if(null != get_Item)
		{
			header.setMessage(ConstantData.MESSAGE_SUCCESS);
			header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
			header.setStatusProcess(true);
			dataitem.setHeader(header);
			dataitem.setDatalist(get_Item);			
		}
		else {
			header.setMessage(ConstantData.MESSAGE_NULL_POINTER);
			header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
			header.setStatusProcess(true);
			dataitem.setHeader(header);
			dataitem.setDatalist(get_Item);
		}

		return new ResponseEntity(dataitem, status);
	}
	
	@PostMapping("/postluckyitem")
	public ResponseEntity postLuckyItem(@RequestBody LottaryModal datelottary, Authentication auth) {
		HttpStatus status = HttpStatus.OK;
		Header header = new Header();
		ResponseData dataitem = new ResponseData();
		String luckyDate = datelottary.getDate();
		LottaryModal get_Item = listLottaryService.postLuckyItemService(luckyDate);
		if(null != get_Item)
		{
			header.setMessage(ConstantData.MESSAGE_SUCCESS);
			header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
			dataitem.setHeader(header);
			dataitem.setDatalist(get_Item);			
		}
		else {
			header.setMessage(ConstantData.MESSAGE_NULL_POINTER);
			header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
			dataitem.setHeader(header);
			dataitem.setDatalist(get_Item);
		}

		return new ResponseEntity(dataitem, status);
	}
	@PostMapping("/validateluckyitem")
	public ResponseEntity postLuckyItem(@RequestBody ValidateLottaryRequestModal datelottary, Authentication auth) {
		HttpStatus status = HttpStatus.OK;
		Header header = new Header();
		ResponseData dataitem = new ResponseData();
		String date = datelottary.getDate();
		UserdetailsIml user = (UserdetailsIml) auth.getPrincipal();
		String id = user.getInfoUser().getId();
		if(null != date)
		{
			SuccessAndFailModal process = listLottaryService.validateLuckyItemService(date,id);
			if(process.getStatusSuccess())
			{
				header.setMessage(process.getMessage());
				header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
				header.setStatusProcess(process.getStatusSuccess());
			}
			else {
				header.setMessage(process.getMessage());
				header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
				header.setStatusProcess(process.getStatusSuccess());
			}
			
		}
		else {
			status = status.BAD_REQUEST;
			header.setMessage(ConstantData.MESSAGE_NULL_POINTER);
			header.setStatusCode(ConstantData.STATUS_CODE_SUCCESS_01);
			header.setStatusProcess(false);
		}
		

		return new ResponseEntity(header, status);
	}
	
	public Boolean validateAPI(DataSetModal NumRequest) {
		Boolean validate = false;

		try {

			for(NumberRequestModel data : NumRequest.getDataSet())
			{
				if (StringUtils.isNotBlank(data.getDate()) || StringUtils.isNotBlank(data.getOption())
						|| StringUtils.isNotBlank(data.getNumber())
						|| StringUtils.isNotBlank(data.getPrice())) {
					validate = true;
				}
			}
		} catch (NullPointerException e) {
			System.out.println("API is EMPTY or NULL ");
		}

		return validate;
	}

}
