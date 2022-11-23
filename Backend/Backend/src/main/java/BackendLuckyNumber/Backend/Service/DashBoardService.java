package BackendLuckyNumber.Backend.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Repo.InfoUserRepo;
import BackendLuckyNumber.Backend.ResponseModel.InfoUserRespModal;

@Service
public class DashBoardService {
 
	@Autowired InfoUserRepo infoUserRepo;
	
	public 	List<InfoUserModal> getInfoUser(String id)
	{
		
		try {
			List<InfoUserModal> infoUser  = infoUserRepo.findByIdSeller(id);
			if(null != infoUser)
			{
				return infoUser;
			}
			
		}catch(Exception e)
		{
			System.out.println("ERROR happen at DashBoardService = "+e);
		}
		return null;
	}
	
	
}
