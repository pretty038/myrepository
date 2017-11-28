package com.apcompany.api.service;

import com.apcompany.api.constrant.UserTypeEnum;

public interface IMessagePushService {
	
		
	boolean pushMessage(String channelId,Object data);
	
	boolean pushMessage(int userId,UserTypeEnum userType,Object data);
	
	boolean pushMessageToStudent(int studentId,Object data);
	
	boolean pushMessageToTeacher(int tId,Object data);

}
