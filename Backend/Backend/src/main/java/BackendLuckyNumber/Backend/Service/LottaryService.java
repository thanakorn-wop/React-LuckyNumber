package BackendLuckyNumber.Backend.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BackendLuckyNumber.Backend.Modal.LottaryModal;
import BackendLuckyNumber.Backend.Repo.LottaryRepo;
import BackendLuckyNumber.Backend.RequestModel.LuckyNumberReq;

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
		luckyNumberReq.setDate(formattedDate);
		lottary.setDate(formattedDate);
		lottary.setThreedow(luckyNumberReq.getThreedown());
		lottary.setThreeTop(luckyNumberReq.getThreetop());
		lottary.setTwodown(luckyNumberReq.getTwodown());
		lottary.setTwotop(luckyNumberReq.getTwotop());
		try {
			LottaryModal dataLottary = lottaryRepo.findByDate(formattedDate);
			if(dataLottary.getDate().equals(formattedDate))
			{
				System.out.println("Duplicate date ");
				status = false;
				
			}
			else {
				lottaryRepo.save(lottary);
				 status = true;
			}
			
		}
		catch(Exception e)
		{
			throw new Exception("ERROR  Lottary");
			
		}
		return status;
	
	}
}
