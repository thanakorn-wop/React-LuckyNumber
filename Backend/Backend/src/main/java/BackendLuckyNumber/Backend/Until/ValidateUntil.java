package BackendLuckyNumber.Backend.Until;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import BackendLuckyNumber.Backend.Constant.ConstantData;
import BackendLuckyNumber.Backend.GenJwt;
import BackendLuckyNumber.Backend.Modal.UserModal;
import BackendLuckyNumber.Backend.RequestModel.LoginReqModel;
import BackendLuckyNumber.Backend.RequestModel.LuckyNumberReq;
import BackendLuckyNumber.Backend.Constant.ConstantData;
public class ValidateUntil {
	
;
	
	public  Boolean validateRequest(LoginReqModel userLogin)
	{
		Boolean validate = StringUtils.isNotBlank(userLogin.getIduser()) && StringUtils.isNotBlank(userLogin.getPassword()) ? true:false;
		return validate;
	}
	
	public  Boolean validateRequestInsetNumberLucky(LuckyNumberReq req)
	{
		Boolean validate = StringUtils.isNotBlank(req.getThreetop()) && StringUtils.isNotBlank(req.getThreedown())
							&& StringUtils.isNotBlank(req.getTwodown()) && StringUtils.isNotBlank(req.getTwotop())
							&& StringUtils.isNotBlank(req.getLucktime())? true:false;
		return validate;
	}
	
	
	public  Boolean validateToken(HttpServletRequest req, String token)
	{
		 Boolean statusToken = false;
		String tokenDB = (String) req.getSession().getAttribute(ConstantData.KEY_WEB_TOKEN);
//		String token = 	token;
		try {
			if(StringUtils.isNotEmpty(token) || StringUtils.isNotEmpty(tokenDB))
			{
//				 GenJwt genjwt = new GenJwt();
//				 tokenDB = genjwt.deCode(tokenDB);
//				 token = genjwt.deCode(token);
				 if(tokenDB.equals(token))
				 {
					 statusToken = true;
				 }
			}
		}catch(Exception e)
		{
			System.out.println("ERROR VALIDATE TOKEN  = "+e);
		}
		
		
		return statusToken;
	}
	
	
	public void createWebTokenGenerator(HttpServletRequest req, String functionName) throws Exception {
		GenJwt genjwt = new GenJwt();
		UserModal userDetail = getUserDetailFromSession(req);
		String id = null != userDetail && null != userDetail.getId() && null != userDetail.getIduser() ? userDetail.getId() : null;
		String token = genjwt.generateNewToken(id, functionName);;
		req.getSession().setAttribute(ConstantData.KEY_WEB_TOKEN, token);
	}
	
	public UserModal getUserDetailFromSession(HttpServletRequest request){

		if(request.getSession().getAttribute(ConstantData.USER_DETAILS) != null)
			return (UserModal)request.getSession().getAttribute(ConstantData.USER_DETAILS);
		else
			return null;
	}
	
}
