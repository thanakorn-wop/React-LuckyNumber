package BackendLuckyNumber.Backend.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BackendLuckyNumber.Backend.Modal.UserModal;
import BackendLuckyNumber.Backend.Repo.UserRepo;
import BackendLuckyNumber.Backend.ResponseModel.UserResModal;

@Service
public class ManagementService {
	
	@Autowired UserRepo userRepo;
	
	
	
	public List<UserResModal> getAllUserService()
	{
		List<UserModal> userModal = new  ArrayList<>();
		
		List<UserResModal> respModal = new  ArrayList<>();
		
		try {
			userModal = userRepo.getUser();
			for(UserModal data : userModal)
			{
				UserResModal resp = new UserResModal();
				resp.setIduser(data.getIduser());
				resp.setNickname(data.getNickname());
				resp.setRole(data.getRole());
				resp.setStatus(data.getStatus());
				respModal.add(resp);
				
			}
		}catch(Exception e)
		{
			throw e;
		}
		
		return respModal;
	}

}
