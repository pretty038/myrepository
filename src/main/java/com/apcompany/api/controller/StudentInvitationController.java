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
@RequestMapping("/invitation/student")
public class StudentInvitationController {
	
	@Autowired private IInvitationService invitationService;

	// 学生主动发起邀请
	@RequestMapping(value = "/invite.do", method = RequestMethod.GET)
	@ResponseBody
	public Object invite(
			@SessionAttribute(value = "tokenId", required = true) Integer studentId,
			@RequestParam(value = "teachCourseId", required = true) int teachCourseId) {
		invitationService.createInvitation(studentId, teachCourseId);
		return "success";
	}
	
	@RequestMapping(value = "/cancle.do", method = RequestMethod.GET)
	@ResponseBody
	public Object cancleInvitation(
			@SessionAttribute(value = "tokenId", required = true) Integer studentId) {
		invitationService.cancleInvitationByStudent(studentId);
		return "success";
	}
	
	@RequestMapping(value = "/handle.json", method = RequestMethod.GET)
	@ResponseBody
	public Object getHandleInvitation(
			@SessionAttribute(value = "tokenId", required = true) Integer studentId) {
		return invitationService.getHandleInvitationByStudent(studentId);
	}

}
