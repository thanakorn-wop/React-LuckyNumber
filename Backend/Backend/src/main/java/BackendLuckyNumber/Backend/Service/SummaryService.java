package BackendLuckyNumber.Backend.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import BackendLuckyNumber.Backend.Constant.ConstantData;
import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.List_number_Modal;
import BackendLuckyNumber.Backend.Repo.InfoAdminRepo;
import BackendLuckyNumber.Backend.Repo.InfoUserRepo;
import BackendLuckyNumber.Backend.Repo.List_numberRepo;
import BackendLuckyNumber.Backend.Repo.LottaryRepo;


@Service
public class SummaryService {
	
	@Autowired
	LottaryRepo lottaryRepo;	
	@Autowired List_numberRepo listnumberRepo;
	
	@Autowired
	InfoUserRepo infouserRepo;
	@Autowired
	InfoAdminRepo infoAdminRepo;
	public Object getReportService(String id,String idUser,String nickname,String searchDate,String role)
	{
		Object result = null;
		
		try {
			if(role.equals(ConstantData.ADMIN))
			{
				if(!"last".equals(searchDate))
				{
					result = infoAdminRepo.findInfoAdmin(idUser,searchDate,nickname);
				}
				else {
					result = infoAdminRepo.findLastDateInfoAdmin(idUser,nickname);
				}
			}
			else {
				if(!"last".equals(searchDate))
				{
					result = infouserRepo.finInfoUserReportPage(id,searchDate,nickname);
				}
				else {
					result = infouserRepo.findLastInfoUserReportPage(id,nickname);
				}
			}
		

		}catch(Exception e)
		{
			throw e;
		}
		return result;
	}
}
