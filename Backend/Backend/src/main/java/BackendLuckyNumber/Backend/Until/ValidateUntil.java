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
	

	
	public void valdiateToken(String token) throws Exception {

		if (!StringUtils.isEmpty(token)) {
			throw new Exception("Error is token");
		}

	}
	
	
	
	
	
}
