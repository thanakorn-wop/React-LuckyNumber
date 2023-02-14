package BackendLuckyNumber.Backend.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

import org.apache.commons.lang3.StringUtils;

@Service
public class LottaryService {

	@Autowired
	LottaryRepo lottaryRepo;

	@Autowired
	List_numberRepo listNumberRepo;

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
//				listNumberRepo.postInsertNumber(NumRequest.getNumber(),NumRequest.getPrice(),NumRequest.getOption(),NumRequest.getDate(),timenow.toString(),ConstantData.MESSAGE_NO,user.getInfoUser().getId());
//				status_Update = true;
				list_number_modal.setNumber(NumRequest.getNumber());
				list_number_modal.setPrice(NumRequest.getPrice());
				list_number_modal.setOptionpurchase(NumRequest.getOption());
				list_number_modal.setDatebuy(NumRequest.getDate());
				list_number_modal.setTime(timenow.toString());
				list_number_modal.setStatuspayment(ConstantData.MESSAGE_NO);
				list_number_modal.setId(user.getInfoUser().getId());
				listNumberRepo.save(list_number_modal);
				
			} else if (NumRequest.getNumber().length() == 2 && NumRequest.getOption().equals(ConstantData.MESSAGE_BELOW)
					|| NumRequest.getOption().equals(ConstantData.MESSAGE_TOP)) {
//				listNumberRepo.postInsertNumber(NumRequest.getNumber(),NumRequest.getPrice(),NumRequest.getOption(),NumRequest.getDate(),timenow.toString(),ConstantData.MESSAGE_NO,user.getInfoUser().getId());
//				status_Update = true;
			}
		} catch (Exception e) {
			throw new Exception("Error Lottary Service = " + e);
		}

		return status_Update;

	}
}
