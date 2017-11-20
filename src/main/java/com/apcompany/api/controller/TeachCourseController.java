package com.apcompany.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.apcompany.api.service.IStudentSaveService;
import com.apcompany.api.service.ITeachCourseService;
import com.apcompany.api.service.TeacherService;
import com.apcompany.user.utils.TipUtil;


@Controller
@RequestMapping("/teachcourse")
public class TeachCourseController {
	
	public TeachCourseController(){
		
	}
	
	@Autowired private ITeachCourseService teachCourseService;
	@Autowired private IStudentSaveService studentSaveService;
		
	@Autowired private TeacherService teacherService;
	
	@RequestMapping(value="/admin/create/course.do",method = RequestMethod.GET)
	@ResponseBody
	public Object createCourses(
			@RequestParam(value="courseName",required=true) String courseName
			){		
		return TipUtil.success(teachCourseService.createCourse(courseName));
	}
	
	@RequestMapping(value="/allcourses.json",method = RequestMethod.GET)
	@ResponseBody
	public Object searchAllCourses(){		
		return TipUtil.success(teachCourseService.getAllCourseList());
	}
	
	@RequestMapping(value="/teacher/create.do",method = RequestMethod.GET)
	@ResponseBody
	public Object createTeachCourse(
			@RequestAttribute(value= "teacherId",required=true)  int teacherId,
			@RequestParam(value="courseId",required=true) int courseId,
			@RequestParam(value="moneyPerMinu",required=true) int moneyPerMinu
			){
		return teachCourseService.createTeachCourse(teacherId,courseId,moneyPerMinu);
	}
	
	@RequestMapping(value="/search/onlineTeachCourse/list.json",method = RequestMethod.GET)
	@ResponseBody
	public Object searchOnlineTeachers(
			@RequestParam(value="courseId",required=true) int courseId,
			@RequestParam(value="orderType",required=false,defaultValue="2") int orderType,
			@RequestParam(value="queryIndex",required=false,defaultValue="0") int queryIndex,
			@RequestParam(value="querySize",required=false,defaultValue="10") int querySize){		
		return teachCourseService.getOnlineTeachCourseListByCourse(courseId,orderType, queryIndex, querySize);
	}
		
	
	@RequestMapping(value="/get/teachcourse/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Object getTeachCourseById(
			@PathVariable int id
			){
		return teachCourseService.getTCById(id);
	}	
	
	
	
	@RequestMapping(value="/student/save.do",method = RequestMethod.GET)
	@ResponseBody
	public Object saveTeachCourse(
			@RequestAttribute(value="studentId",required=true)  Integer studentId,
			@RequestParam(value="teachCourseId",required=true) int teachCoueseId){		
		studentSaveService.saveTeachCourse(studentId, teachCoueseId);
		return TipUtil.success("success");
	}
	
	@RequestMapping(value="/student/saveTeachCourse/delete/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Object deleteSaveTeachCourseById(
			@RequestAttribute(value= "studentId",required=true)  int studentId,
			@PathVariable int id
			){
		return TipUtil.success(studentSaveService.deleteById(id));
	}
	
	@RequestMapping(value="/student/savelist.json",method = RequestMethod.GET)
	@ResponseBody
	public Object saveList(
			@RequestAttribute(value= "studentId",required=true)  int studentId,
			@RequestParam(value="queryIndex",required=false,defaultValue="0") int queryIndex,
			@RequestParam(value="querySize",required=false,defaultValue="10") int querySize){	
		return studentSaveService.getStudentSavaTeachCourseList(studentId, queryIndex, querySize);
	}
	
	
	
	

}
