package BackendLuckyNumber.Backend.COntroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BackendLuckyNumber.Backend.Header;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ReportController {
	
	@GetMapping("/report")
	public ResponseEntity getReport()
	{
		HttpStatus status = HttpStatus.OK;
		Boolean statusInsert;
		Header header = new Header();
		return new ResponseEntity(header, status);
	}

}
