package com.apcompany.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.apcompany.api.model.pojo.TeachCourseDO;
import com.apcompany.api.service.ITeacherTCService;
import com.apcompany.user.utils.TipUtil;

@Controller
@RequestMapping("/teachcourse/teacher")
public class TeacherTCController {
	
	@Autowired private ITeacherTCService tcService;
	
	@RequestMapping(value="/teacher/all.json",method = RequestMethod.GET)
	@ResponseBody
	public Object getTCListByTeacher(
			@RequestAttribute("teacherId") int teacherId){
		List<TeachCourseDO> list = tcService.getTCListByTId(teacherId);
	    return TipUtil.success(list);
	}

	
	@RequestMapping(value="/teacher/prepared.do",method = RequestMethod.GET)
	@ResponseBody
	public Object preparedToTeach(
			@RequestAttribute("teacherId") int teacherId,
			@RequestAttribute("teachCourseId") int teachCourseId){
		boolean result=  tcService.preparedToTeach(teacherId, teachCourseId);
	    return TipUtil.success(result);
	}
	
	@RequestMapping(value="/teacher/close.do",method = RequestMethod.GET)
	@ResponseBody
	public Object closeToTeach(
			@RequestAttribute("teacherId") int teacherId,
			@RequestAttribute("teachCourseId") int teachCourseId){
		boolean result=  tcService.closeToTeach(teacherId, teachCourseId);
	    return TipUtil.success(result);
	}

}
