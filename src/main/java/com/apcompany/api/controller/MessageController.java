package com.apcompany.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apcompany.api.service.IMessagePushService;
import com.apcompany.api.service.IUserOnlineInfoService;
import com.apcompany.user.utils.TipUtil;


@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Autowired IUserOnlineInfoService userOnlineInfoService;
	
	@Autowired private IMessagePushService messagePushService;
	
	@RequestMapping(value="/push/channel",method = RequestMethod.GET)
	@ResponseBody
	public Object pushChannel(
			@RequestAttribute(value="studentId",required=false) int studentId,
			@RequestAttribute(value="teacherId",required=false) int teacherId,
			@RequestParam(value="channelId",required=true) String channelId
			){		
		return TipUtil.success(userOnlineInfoService.addChannelInfo(studentId, teacherId, channelId));
	}
	
	
	
	

}
