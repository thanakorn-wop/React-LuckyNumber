package BackendLuckyNumber.Backend.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BackendLuckyNumber.Backend.Modal.TransferLottaryModal;
import BackendLuckyNumber.Backend.Repo.TransferLottaryRepo;

@Service
public class ReportService {
	
	@Autowired
	TransferLottaryRepo transferlottaryRepo;
	
	public List<TransferLottaryModal> getAllUserService()
	{
		List<TransferLottaryModal> dataTransfer = new ArrayList<>();
		
	try
	{
		dataTransfer = transferlottaryRepo.getAllUserTransfer();
	}catch(Exception e)
	{
		throw e;
	}
	
		return dataTransfer;
	}

}
