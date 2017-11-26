package com.apcompany.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.apcompany.api.service.IInviteVideoService;
import com.apcompany.user.utils.TipUtil;

@Controller
@RequestMapping("/invite/teacher")
public class TeacherInvitationController {

	@Autowired
	private IInviteVideoService invitationService;
	
	
	@RequestMapping(value = "/get/videoaccount.json", method = RequestMethod.GET)
	@ResponseBody
	public Object getCode(
			@RequestAttribute(value = "teacherId", required = true) Integer teacherId) {
		return TipUtil.success(invitationService.getVideoAccount(teacherId));
	}

	@RequestMapping(value = "/push/videokey", method = RequestMethod.GET)
	@ResponseBody
	public Object pushVideoKey(
			@RequestAttribute(value = "teacherId", required = true) Integer teacherId,
			@RequestParam(value="videoKey",required=true) String videoKey
			) {
		return TipUtil.success(invitationService.pushVideoKey(teacherId, videoKey));
	}
	

}
