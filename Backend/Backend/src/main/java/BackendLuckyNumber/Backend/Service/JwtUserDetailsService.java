package BackendLuckyNumber.Backend.Service;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User; 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import BackendLuckyNumber.Backend.Modal.UserModal;

@Service
public class JwtUserDetailsService implements UserDetailsService { 
	
	 @Autowired LoginService lognService;
	 
	   @Override 
	   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
		   UserModal userDetails = lognService.getUser(username);
		   try {
			   if(null != userDetails)
			   {
				   if (userDetails.getIduser().equals(username)) { 
				         return new User(userDetails.getIduser(), 
				            userDetails.getPassword(),
				            new ArrayList<>()); 
				      } 
				
			   }
			   
		   }catch(Exception e)
		   {
			   System.out.println("ERROR loadUserByUsername");
		   }
		   return null;
		  
		
	     
	   } 
	}