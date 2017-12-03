package com.apcompany.api.controller.teachcourse;

import com.apcompany.api.constrant.TeachCourseStatusEnum;
import com.apcompany.api.service.teachcourse.ITeacherTeachCourseService;
import com.apcompany.user.utils.TipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachcourse/teacher")
public class TeacherTeachCourseController {
	
	@Autowired private ITeacherTeachCourseService teacherTeachCourseService;

	@RequestMapping(value="/apply.do",method = RequestMethod.POST)
	@ResponseBody
	public Object closeToTeach(
			@RequestAttribute("teacherId") int teacherId,
			@RequestParam("courseId") int teachCourseId,
			@RequestParam("moneyPerMinute") int moneyPerMinute){
		return TipUtil.success(teacherTeachCourseService.applyTeachCourse(teacherId, teachCourseId,moneyPerMinute));
	}
	
	@RequestMapping(value="/all.json",method = RequestMethod.GET)
	@ResponseBody
	public Object getTCListByTeacher(
			@RequestAttribute("teacherId") int teacherId){
	    return TipUtil.success(teacherTeachCourseService.getMyTeachCourseList(teacherId));
	}


	
	@RequestMapping(value="/prepared.do",method = RequestMethod.GET)
	@ResponseBody
	public Object preparedToTeach(
			@RequestAttribute("teacherId") int teacherId,
			@RequestParam("teachCourseId") int teachCourseId){
		boolean result=  teacherTeachCourseService.updateStatus(teacherId, teachCourseId, TeachCourseStatusEnum.NORMAL);
	    return TipUtil.success(result);
	}
	
	@RequestMapping(value="/close.do",method = RequestMethod.GET)
	@ResponseBody
	public Object closeToTeach(
			@RequestAttribute("teacherId") int teacherId,
			@RequestParam("teachCourseId") int teachCourseId){
		boolean result=  teacherTeachCourseService.updateStatus(teacherId, teachCourseId,TeachCourseStatusEnum.CLOSE);
	    return TipUtil.success(result);
	}

	@RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Object deleteTeachCourse(
			@RequestAttribute("teacherId") int teacherId,
			@PathVariable("id") int id){
		boolean result=  teacherTeachCourseService.deleteTeachCourse(id, teacherId);
		return TipUtil.success(result);
	}

}
