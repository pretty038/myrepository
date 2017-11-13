package com.apcompany.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.apcompany.api.service.ITeachOrderService;

@Controller
@RequestMapping("/order/teacher")
public class TeacherTeachOrderController {
	@Autowired
	private ITeachOrderService teachOrderService;

	// 扫描订单状态。--如果发现有在进行中的订单，返回。
	@RequestMapping(value = "/handle/initeOrder", method = RequestMethod.GET)
	@ResponseBody
	public Object getHandleInviteOrder(
			@SessionAttribute(value = "tokenId", required = true) Integer teacherId,
			@RequestParam(value = "teachCourseId", required = true) int teachCourseId) {
		return teachOrderService.getHandleInviteOrderByTeacher(teacherId);
	}

	// 老师结束订单
	@RequestMapping(value = "/commit.do", method = RequestMethod.GET)
	@ResponseBody
	public Object createOrderByCalling(
			@SessionAttribute(value = "tokenId", required = true) Integer teacherId,
			@RequestParam(value = "orderId", required = true) int orderId) {
		teachOrderService.commitInviteOrderByTeacher(teacherId, orderId);
		return "success";
	}

}
