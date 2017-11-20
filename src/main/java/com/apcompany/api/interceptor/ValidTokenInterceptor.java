package com.apcompany.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.apcompany.api.constrant.UserType;
import com.apcompany.api.pojo.TokenModel;
import com.apcompany.api.service.IUserOnlineInfoService;
import com.apcompany.api.util.CommonUtil;

public class ValidTokenInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger= Logger.getLogger(ValidTokenInterceptor.class);
	
	@Autowired private IUserOnlineInfoService userinfoInfoService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		try {			
			TokenModel tokenModel = CommonUtil.validToken(request.getParameter("token"));
		    if(tokenModel==null){
		    	logger.info("token is not valid");
		    	CommonUtil.setResponseData(response, "token is not valid");
		    	return false;
		    }
			if(! userinfoInfoService.checkAccessToken(tokenModel.getToken())){
		    	logger.info("user token is not exits");
		    	CommonUtil.setResponseData(response, "user token is not exits");
		    	return false;
		    }
			logger.info("check ok and refresh success");
			setDataToRequest(request, tokenModel);
			logger.info("set data ok");			
		    return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private void setDataToRequest(HttpServletRequest request,TokenModel tokenModel){
		if(tokenModel== null||tokenModel.getUserType()==null||tokenModel.getUserId()==null){
			return;
		}
		if(tokenModel.getUserType()==UserType.Student){
			request.setAttribute(UserType.Student.getValue(), tokenModel.getUserId());
		}else{
			request.setAttribute(UserType.Teacher.getValue(), tokenModel.getUserId());
		}
	}
	
	

	
}
