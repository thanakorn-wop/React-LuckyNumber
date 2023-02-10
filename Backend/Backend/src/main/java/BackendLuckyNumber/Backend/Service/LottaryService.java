package BackendLuckyNumber.Backend.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BackendLuckyNumber.Backend.Modal.LottaryModal;
import BackendLuckyNumber.Backend.Repo.LottaryRepo;
import BackendLuckyNumber.Backend.RequestModel.LuckyNumberReq;
import BackendLuckyNumber.Backend.RequestModel.NumberRequestModel;

import org.apache.commons.lang3.StringUtils;
@Service
public class LottaryService {

	
	@Autowired
	LottaryRepo lottaryRepo;
	
	public Boolean postInsertNumberLuckyService(LuckyNumberReq luckyNumberReq) throws Exception
	{
		Boolean status = false;
		LottaryModal lottary = new LottaryModal();

		lottary.setDate(luckyNumberReq.getDate());
		lottary.setThreedow(luckyNumberReq.getThreedown());
		lottary.setThreeTop(luckyNumberReq.getThreetop());
		lottary.setTwodown(luckyNumberReq.getTwodown());
		lottary.setTwotop(luckyNumberReq.getTwotop());
		try {
			LottaryModal dataLottary = lottaryRepo.findByDate(luckyNumberReq.getDate());
			if(null == dataLottary)
			{
				
				lottaryRepo.save(lottary);
				 status = true;
			}
			else {
				System.out.println("Duplicate date ");
				status = false;
			}
			
		}
		catch(Exception e)
		{
			throw new Exception("ERROR  Lottary is = "+e);
			
		}
		return status;
	
	}
	
	public Boolean postInsertNumberService(NumberRequestModel NumRequest)
	{
		
		return false;
		
	}
}
