package BackendLuckyNumber.Backend.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.List_number_Modal;
import BackendLuckyNumber.Backend.Repo.InfoUserRepo;
import BackendLuckyNumber.Backend.Repo.List_numberRepo;
import BackendLuckyNumber.Backend.Repo.LottaryRepo;


@Service
public class ReportService {
	
	@Autowired
	LottaryRepo lottaryRepo;	
	@Autowired List_numberRepo listnumberRepo;
	
	@Autowired
	InfoUserRepo infouserRepo;
	public InfoUserModal getReportService(String id,String nickname,String searchDate)
	{
	
		Boolean Status = false;
		String people_win ="";
		String people_lost = "";
		Integer total_purchase = 0;
		Integer balance = 0;
		Integer payNow = 0;
		Integer notPay  = 0;
		Integer money_win = 0;	
		List<List_number_Modal> listItem = new ArrayList<>();
		InfoUserModal infoUser = new InfoUserModal();
		try {
			if(!"last".equals(searchDate))
			{
				infoUser = infouserRepo.finInfoUserReportPage(id,searchDate,nickname);
			}
			else {
				infoUser = infouserRepo.findLastInfoUserReportPage(id,nickname);
			}
		
//			LottaryModal dateLottary = lottaryRepo.findDate();
//			if(null != dateLottary)
//			{
//				listItem = listnumberRepo.getlistitem(id, dateLottary.getDate());
//				if(null != listItem && listItem.size()>0)
//				{
//					
//				}
//			}
		}catch(Exception e)
		{
			throw e;
		}
		return infoUser;
	}
}
