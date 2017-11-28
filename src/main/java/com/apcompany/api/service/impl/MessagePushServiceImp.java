package com.apcompany.api.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.api.constrant.UserTypeEnum;
import com.apcompany.api.service.IMessagePushService;
import com.apcompany.api.service.ITCService;
import com.apcompany.api.service.IUserOnlineInfoService;
import com.apcompany.api.util.CommonUtil;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
@Service
public class MessagePushServiceImp implements IMessagePushService {
	@Autowired private BaiduPushClient baiduPushClient;
	
	@Resource private IUserOnlineInfoService userOnlineInfoService;
	@Resource private ITCService tcService;
	 

	@Override
	public boolean pushMessage(String channelId,Object data) {
		if (channelId == null){
			return false;
		}
		PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().
                addChannelId(channelId).
                addMsgExpires(new Integer(3600)).   //设置消息的有效时间,单位秒,默认3600*5.
                addMessageType(1).              //设置消息类型,0表示透传消息,1表示通知,默认为0.
                addMessage(CommonUtil.toJson(data));
		try {
			baiduPushClient.pushMsgToSingleDevice(request);
			return true;
		} catch (PushClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PushServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;		
	}

	@Override
	public boolean pushMessage(int userId, UserTypeEnum userType, Object data) {
		String channel = userOnlineInfoService.getChannel(userId, userType);
		return pushMessage(channel, data);
	}

	@Override
	public boolean pushMessageToStudent(int studentId, Object data) {
		return pushMessage(studentId, UserTypeEnum.Student, data);
	}

	@Override
	public boolean pushMessageToTeacher(int tId, Object data) {
		return pushMessage(tId, UserTypeEnum.Teacher, data);
	}
	
	

	
	
	

}
