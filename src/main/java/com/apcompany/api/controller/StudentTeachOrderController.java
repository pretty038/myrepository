package com.apcompany.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.apcompany.api.model.form.OrderTeacherScoreForm;
import com.apcompany.api.service.ITeachOrderService;
import com.apcompany.user.utils.TipUtil;

@Controller
@RequestMapping("/order/student")
public class StudentTeachOrderController {

	@Autowired
	private ITeachOrderService teachOrderService;

	// 扫描订单状态。--如果发现有在进行中的订单，返回。
	@RequestMapping(value = "/handle/initeOrder", method = RequestMethod.GET)
	@ResponseBody
	public Object getHandleInviteOrder(
			@RequestAttribute(value = "studentId", required = true) Integer studentId) {
		return teachOrderService.getHandleInviteOrderByStudent(studentId);
	}

	// commit订单
	@RequestMapping(value = "/commit", method = RequestMethod.GET)
	@ResponseBody
	public Object commitOrder(
			@RequestAttribute(value = "studentId", required = true) Integer studentId,
			@RequestParam(value = "teachOrderId", required = true) int teachOrderId) {
		return teachOrderService.commitInviteOrderByStudent(studentId, teachOrderId);
	}

	// 为订单评价
	@RequestMapping(value = "/markscore", method = RequestMethod.POST)
	@ResponseBody
	public Object commentTeacher(
			@RequestAttribute(value = "studentId", required = true) Integer studentId,
			OrderTeacherScoreForm form) {
		return TipUtil.success(teachOrderService.markScoreForOrder(studentId,form));
	}

}
