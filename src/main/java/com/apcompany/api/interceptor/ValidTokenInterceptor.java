package com.apcompany.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.apcompany.api.constrant.UserType;
import com.apcompany.api.service.IUserOnlineInfoService;
import com.apcompany.api.util.CommonUtil;

public class ValidTokenInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger= Logger.getLogger(ValidTokenInterceptor.class);
	
	@Autowired private IUserOnlineInfoService userinfoInfoService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		try {
			
			String token = request.getParameter("token");
			Integer userId = CommonUtil.parseToInteger(request.getParameter("userId"));
			UserType userType = UserType.valueOf(CommonUtil.parseToInteger(request.getParameter("userType")));			
			if(userId==null||userType==null){
				logger.info("userId or userType is null....");
				return true;
			}
			if(userType==UserType.Student){
				request.setAttribute(UserType.Student.getValue(), userId);
			}else{
				request.setAttribute(UserType.Teacher.getValue(), userId);
			}
			System.out.println("check accessToken status...");
			boolean checkResult = userinfoInfoService.checkAccessStatus(Integer.valueOf(userId), userType, token);
		    if(checkResult ==false){
		    	logger.info("user token is offline");
		    }
		    return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	

	
}
