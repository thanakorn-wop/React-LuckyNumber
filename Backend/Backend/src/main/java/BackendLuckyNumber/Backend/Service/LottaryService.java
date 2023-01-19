package BackendLuckyNumber.Backend.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BackendLuckyNumber.Backend.Modal.LottaryModal;
import BackendLuckyNumber.Backend.Repo.LottaryRepo;
import BackendLuckyNumber.Backend.RequestModel.LuckyNumberReq;
import org.apache.commons.lang3.StringUtils;
@Service
public class LottaryService {

	
	@Autowired
	LottaryRepo lottaryRepo;
	public Boolean postInsertNumberLuckyService(LuckyNumberReq luckyNumberReq) throws Exception
	{
		Boolean status = false;
		LottaryModal lottary = new LottaryModal();
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = myDateObj.format(myFormatObj);
		System.out.println("after format = "+luckyNumberReq.getDate());
		System.out.println("after format = "+formattedDate);
		System.out.println(luckyNumberReq.getDate().equals(formattedDate)?true :false);
	//	luckyNumberReq.setDate(formattedDate);
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
			throw new Exception("ERROR  Lottary");
			
		}
		return status;
	
	}
}
