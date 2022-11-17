package BackendLuckyNumber.Backend.ResponseModel;

import java.io.Serializable;

public class JwtResponseModel implements Serializable {
	   /**
	   *
	   */
	   private static final long serialVersionUID = 1L;
	   private final String access_token;
	   public JwtResponseModel(String token) {
	      this.access_token = token;
	   }
	   public String getToken() {
	      return access_token;
	   }
	   
	}