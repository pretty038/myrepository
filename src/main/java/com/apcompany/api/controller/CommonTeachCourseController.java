package com.apcompany.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.apcompany.api.service.ITCService;
import com.apcompany.user.utils.TipUtil;


@Controller
@RequestMapping("/teachcourse/common")
public class CommonTeachCourseController {
	
	public CommonTeachCourseController(){
		
	}
	
	@Autowired private ITCService tService;;
	
	@RequestMapping(value="/course/create.do",method = RequestMethod.GET)
	@ResponseBody
	public Object createCourses(
			@RequestParam(value="courseName",required=true) String courseName
			){		
		return TipUtil.success(tService.createCourse(courseName));
	}
	
	@RequestMapping(value="/allcourses.json",method = RequestMethod.GET)
	@ResponseBody
	public Object searchAllCourses(
			@RequestAttribute("studentId") int studentId){		
		return TipUtil.success(tService.getAllCourseList());
	}
	
	@RequestMapping(value="/create.do",method = RequestMethod.GET)
	@ResponseBody
	public Object createTeachCourse(
			@RequestAttribute(value= "teacherId",required=true)  int teacherId,
			@RequestParam(value="courseId",required=true) int courseId,
			@RequestParam(value="moneyPerMinu",required=true) int moneyPerMinu
			){
		return TipUtil.success(tService.createTeachCourse(teacherId,courseId,moneyPerMinu));
	}
	
	@RequestMapping(value="/get/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Object getTCById(
			@PathVariable int id
			){
		return TipUtil.success(tService.getTCById(id));
	}	
	
	
	
	
	

}
