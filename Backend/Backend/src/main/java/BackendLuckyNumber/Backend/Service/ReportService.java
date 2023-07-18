package BackendLuckyNumber.Backend.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.TransferLottaryModal;
import BackendLuckyNumber.Backend.Repo.InfoUserRepo;
import BackendLuckyNumber.Backend.Repo.TransferLottaryRepo;

@Service
public class ReportService {
	
	@Autowired
	TransferLottaryRepo transferlottaryRepo;
	
	@Autowired
	InfoUserRepo infouserRepo;
	
	public List<InfoUserModal> getAllUserService()
	{
		List<InfoUserModal> dataTransfer = null;
		
	try
	{
		dataTransfer = infouserRepo.getAllUser();
	}catch(Exception e)
	{
		throw e;
	}
	
		return dataTransfer;
	}

}
