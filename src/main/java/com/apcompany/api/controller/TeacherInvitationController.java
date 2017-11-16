package com.apcompany.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.apcompany.api.service.IInvitationService;

@Controller
@RequestMapping("/invitation/teacher")
public class TeacherInvitationController {

	@Autowired
	private IInvitationService invitationService;

	// 老师查询邀请数据 --客户端应该定时去掉此接口，如果发现有未处理的邀请，立即处理。避免积累邀请。否则使用websocket长链接处理。
	@RequestMapping(value = "/invitations.list", method = RequestMethod.GET)
	@ResponseBody
	public Object invite(
			@SessionAttribute(value = "tokenId", required = true) int teacherId,
			@RequestParam("status") int status) {
		invitationService.dealWithInvitationByTeacher(teacherId,status);
		return "success";
	}
	
	
	
	

}
