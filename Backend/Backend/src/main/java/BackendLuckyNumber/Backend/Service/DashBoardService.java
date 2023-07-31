package BackendLuckyNumber.Backend.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.MonthModal;
import BackendLuckyNumber.Backend.Repo.MonthRepo;
import BackendLuckyNumber.Backend.ResponseModel.InfoUserRespModal;

@Service
public class DashBoardService {

//	@Autowired InfoUserRepo infoUserRepo;

	@Autowired MonthRepo monthRepo;

	public MonthModal getInfoUser(String id) {

		String[] Month = null;
		Integer Sum = 0;
		Integer[] Money_Each_Month = null;
		MonthModal infoMonth_User = null;
		try {
			infoMonth_User = monthRepo.findMonthOfUser(id,"2023");
		} catch (Exception e) {
			System.out.println("ERROR happen at DashBoardService = " + e);
		}
		return infoMonth_User;
	}

}
