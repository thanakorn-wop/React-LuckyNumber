package BackendLuckyNumber.Backend.COntroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BackendLuckyNumber.Backend.TokenManager;
import BackendLuckyNumber.Backend.Modal.RefreshToken;
import BackendLuckyNumber.Backend.Modal.TokenModal;
import BackendLuckyNumber.Backend.Modal.UserModal;
import BackendLuckyNumber.Backend.Repo.TokenRepo;
import BackendLuckyNumber.Backend.Repo.UserRepo;
import BackendLuckyNumber.Backend.RequestModel.TokenModalRequest;
import BackendLuckyNumber.Backend.ResponseModel.TokenResponseModal;
import BackendLuckyNumber.Backend.ResponseModel.UserLoginResModal;

@CrossOrigin(origins = "*")
@RestController
//@RequestMapping("/api")
public class AuthenController {
	
	@Autowired TokenManager jwt;
	@Autowired TokenRepo tokenRepo;
	@Autowired UserRepo userRepo;
	
	@PostMapping("/refresh")
	public ResponseEntity refreshToken(@RequestBody TokenModalRequest req)
	{
//		   final String authorizationHeader = request.getHeader("Authorization");
	        String username = null;
	        Boolean isProcess = false;
	    	UserLoginResModal res  = new UserLoginResModal();
	        try {
	        	TokenModal token = tokenRepo.getTokenByRefreshToken(req.getRefreshToken());
	        	if(null !=token)
	        	{
	        		String idUser  = jwt.getUsernameFromToken(token.getToken());
	        		if(null !=idUser)
	        		{
	        			Integer resultDelete = tokenRepo.deleteToken(token.getIdToken());
	        			if(resultDelete>0)
	        			{
	        				List<UserModal> user = userRepo.findidUser(idUser);
		        			RefreshToken jwtToken =	jwt.generateJwtToken(idUser);
//		        			addToken.setToken(jwtToken.getAccessToken());
//		        			addToken.setRefreshToken(jwtToken.getRefreshToken());
		        			res.setAccessToken(jwtToken.getAccessToken());
		        			res.setRefreshToken(jwtToken.getRefreshToken());
		        			res.setRole(user.get(0).getRole());
		        			res.setUsername(user.get(0).getIduser());
		        			Integer result = tokenRepo.insertToken(jwtToken.getRefreshToken(), jwtToken.getAccessToken(),idUser);
		        			if(result>0)
		        			{
		        				 return  ResponseEntity.ok(res);
		        			}
	        			}
	        		
	        		}
	        		else {
	        			throw new NullPointerException();
	        		}
	        		
	        	}
	        	else {
	        		throw new NullPointerException();
	        	}
	        }
	        catch (NullPointerException e) {
	            System.out.println("data is null.");
	        }
	        catch(Exception e)
	        {
	        	throw e;
	        }

	        // Handle token refresh failure
	        return ResponseEntity.badRequest().build();
	    }
	}


