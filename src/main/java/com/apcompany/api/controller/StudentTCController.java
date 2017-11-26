package com.apcompany.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.apcompany.api.model.form.OnlineTCForm;
import com.apcompany.api.model.pojo.OnlineTCInfoDO;
import com.apcompany.api.service.IStudentTCService;
import com.apcompany.user.utils.TipUtil;

@Controller
@RequestMapping("/teachcourse/student")
public class StudentTCController {
	
	@Autowired private IStudentTCService sTcService;
	
	@RequestMapping(value="/search/list.json",method = RequestMethod.GET)
	@ResponseBody
	public Object searchOnlineTeachers(
			@RequestAttribute("studentId") int studentId,
			@RequestParam(value="courseId",required=true) int courseId,
			@RequestParam(value="orderType",required=false,defaultValue="2") int orderType,
			@RequestParam(value="index",required=false,defaultValue="0") int index,
			@RequestParam(value="limit",required=false,defaultValue="10") int limit){
		List<OnlineTCInfoDO> list = sTcService.getOnlineTCListByCourse(
				new OnlineTCForm(studentId,courseId,orderType,index,limit));
	    return TipUtil.success(list);
	}
	
	@RequestMapping(value="/save/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Object saveTeachCourse(
			@RequestAttribute(value="studentId",required=true)  Integer studentId,
			@PathVariable(value="id",required=true) int id){		
		sTcService.saveTeachCourse(studentId, id);
		return TipUtil.success("success");
	}
	
	@RequestMapping(value="/save/delete/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Object deleteSaveTeachCourseById(
			@RequestAttribute(value= "studentId",required=true)  int studentId,
			@PathVariable int id
			){
		return TipUtil.success(sTcService.deleteById(id));
	}
	
	@RequestMapping(value="/save/list.json",method = RequestMethod.GET)
	@ResponseBody
	public Object saveList(
			@RequestAttribute(value= "studentId",required=true)  int studentId,
			@RequestParam(value="queryIndex",required=false,defaultValue="0") int queryIndex,
			@RequestParam(value="querySize",required=false,defaultValue="10") int querySize){	
		return sTcService.getSavaTCList(studentId, queryIndex, querySize);
	}
	
	

}
