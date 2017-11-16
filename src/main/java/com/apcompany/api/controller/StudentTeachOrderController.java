package com.apcompany.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.apcompany.api.model.form.OrderTeacherScoreForm;
import com.apcompany.api.service.ITeachOrderService;

@Controller
@RequestMapping("/order/student")
public class StudentTeachOrderController {

	@Autowired
	private ITeachOrderService teachOrderService;

	// 扫描订单状态。--如果发现有在进行中的订单，返回。
	@RequestMapping(value = "/handle/initeOrder", method = RequestMethod.GET)
	@ResponseBody
	public Object getHandleInviteOrder(
			@SessionAttribute(value = "tokenId", required = true) Integer studentId) {
		return teachOrderService.getHandleInviteOrderByStudent(studentId);
	}

	// commit订单
	@RequestMapping(value = "/commit", method = RequestMethod.GET)
	@ResponseBody
	public Object commitOrder(
			@SessionAttribute(value = "tokenId", required = true) Integer studentId,
			@RequestParam(value = "teachOrderId", required = true) int teachOrderId) {
		return teachOrderService.commitInviteOrderByStudent(studentId, teachOrderId);
	}

	// 为订单评价
	@RequestMapping(value = "/markscore", method = RequestMethod.POST)
	@ResponseBody
	public Object commentTeacher(OrderTeacherScoreForm form) {
		return teachOrderService.markScoreForOrder(form);
	}

}
