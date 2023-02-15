package BackendLuckyNumber.Backend.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BackendLuckyNumber.Backend.Constant.ConstantData;
import BackendLuckyNumber.Backend.Modal.List_number_Modal;
import BackendLuckyNumber.Backend.Modal.LottaryModal;
import BackendLuckyNumber.Backend.Repo.List_numberRepo;
import BackendLuckyNumber.Backend.Repo.LottaryRepo;
import BackendLuckyNumber.Backend.RequestModel.LuckyNumberReq;
import BackendLuckyNumber.Backend.RequestModel.NumberRequestModel;
import BackendLuckyNumber.Backend.RequestModel.UserdetailsIml;
import BackendLuckyNumber.Backend.ResponseModel.list_number_respModal;

import org.apache.commons.lang3.StringUtils;

@Service
public class LottaryService {

	@Autowired
	LottaryRepo lottaryRepo;

	@Autowired
	List_numberRepo listNumberRepo;

	
	public List<List_number_Modal> getLottaryService(String idUser)
	{
//		List<List_numberRepo> dataItem = new ArrayList<>();
		List<list_number_respModal> newData = new ArrayList<>();
		list_number_respModal obj = new list_number_respModal();
		List<List_number_Modal>	dataItem =  listNumberRepo.findItem(idUser);
//		if(null != dataItem)
//		{
//			for(List_number_Modal data: dataItem)
//			{
//				obj.setDatebuy(data.getDatebuy());
//				obj.setId(data.getId());
//				obj.setIdlist(data.getIdlist());
//				obj.setLuckytime(data.getLuckytime());
//				obj.setNumber(data.getNumber());
//				obj.setOptinpurchase(data.getOptinpurchase());
//				obj.setPrice(data.getPrice());
//				obj.setStatus(data.getPrice());
//				obj.setStatuspayment(data.getStatuspayment());
//				obj.setTime(data.getTime());
//				newData.add(obj);
//			}
//		}
	return	 dataItem;
	}
	public Boolean postInsertNumberLuckyService(LuckyNumberReq luckyNumberReq) throws Exception {
		Boolean status = false;
		LottaryModal lottary = new LottaryModal();

		lottary.setDate(luckyNumberReq.getDate());
		lottary.setThreedow(luckyNumberReq.getThreedown());
		lottary.setThreeTop(luckyNumberReq.getThreetop());
		lottary.setTwodown(luckyNumberReq.getTwodown());
		lottary.setTwotop(luckyNumberReq.getTwotop());
		try {
			LottaryModal dataLottary = lottaryRepo.findByDate(luckyNumberReq.getDate());
			if (null == dataLottary) {

				lottaryRepo.save(lottary);
				status = true;
			} else {
				System.out.println("Duplicate date ");
				status = false;
			}

		} catch (Exception e) {
			throw new Exception("ERROR  Lottary is = " + e);

		}
		return status;

	}

	public Boolean postInsertNumberService(NumberRequestModel NumRequest, UserdetailsIml user) throws Exception {
		Boolean status_Update = false;
		LocalTime timenow = LocalTime.now();
		List_number_Modal list_number_modal = new List_number_Modal();

		try {
			if (NumRequest.getNumber().length() == 3 && NumRequest.getOption().equals(ConstantData.MESSAGE_TOP)) {
				list_number_modal.setNumber(NumRequest.getNumber());
				list_number_modal.setPrice(NumRequest.getPrice());
				list_number_modal.setOptinpurchase(NumRequest.getOption());
				list_number_modal.setDatebuy(NumRequest.getDate());
				list_number_modal.setTime(timenow.toString());
				list_number_modal.setStatuspayment(ConstantData.MESSAGE_NO);
				list_number_modal.setId(user.getInfoUser().getId());
				list_number_modal.setStatus("unLucky");
				listNumberRepo.save(list_number_modal);			
				status_Update = true;
				
			} else if (NumRequest.getNumber().length() == 2 && (NumRequest.getOption().equals(ConstantData.MESSAGE_BELOW)
					|| NumRequest.getOption().equals(ConstantData.MESSAGE_TOP))) {
				list_number_modal.setNumber(NumRequest.getNumber());
				list_number_modal.setPrice(NumRequest.getPrice());
				list_number_modal.setOptinpurchase(NumRequest.getOption());
				list_number_modal.setDatebuy(NumRequest.getDate());
				list_number_modal.setTime(timenow.toString());
				list_number_modal.setStatuspayment(ConstantData.MESSAGE_NO);
				list_number_modal.setId(user.getInfoUser().getId());
				list_number_modal.setStatus("unLucky");
				listNumberRepo.save(list_number_modal);			
				status_Update = true;
			}
		} catch (Exception e) {
			throw new Exception("Error Lottary Service = " + e);
		}

		return status_Update;

	}
}
