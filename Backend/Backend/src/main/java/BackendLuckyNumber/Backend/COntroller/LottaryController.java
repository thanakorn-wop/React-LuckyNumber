package BackendLuckyNumber.Backend.COntroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import BackendLuckyNumber.Backend.Service.ListLottaryService;

@RestController
@CrossOrigin(origins = "*")
public class ListLottaryController {
	
	@Autowired ListLottaryService listLottaryService;
	
	@GetMapping("/getlistlottary")
	public void getListLottary()
	{
		
	}

}
