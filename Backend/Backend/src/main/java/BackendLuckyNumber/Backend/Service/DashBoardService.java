package BackendLuckyNumber.Backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Repo.InfoUserRepo;
import BackendLuckyNumber.Backend.ResponseModel.InfoUserRespModal;

@Service
public class DashBoardService {
 
	@Autowired InfoUserRepo infoUserRepo;
	
	public InfoUserModal getInfoUser(String id)
	{
		InfoUserModal infoUser = new InfoUserModal();
		try {
			infoUser = infoUserRepo.findByIdSeller(id);
			if(null != infoUser)
			{
				return infoUser;
			}
			
		}catch(Exception e)
		
		{
			System.out.println("ERROR happen at DashBoardService = "+e);
		}
		return infoUser;
	}
	
	
}
