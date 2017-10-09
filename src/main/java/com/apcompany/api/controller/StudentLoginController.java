package com.apcompany.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apcompany.api.pojo.Student;
import com.apcompany.api.service.StudentLoginService;
import com.apcompany.user.utils.StringUtil;
import com.apcompany.user.utils.TipUtil;

@Controller
@RequestMapping("/student")
public class StudentLoginController {

	@Autowired
	private StudentLoginService studentLoginService;

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Object register(Student student) {
		
		boolean isSucessful = studentLoginService.register(student);
		
		return isSucessful;

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Object login(String loginname,String phone,String password) {
		
		if(StringUtil.isEmpty(loginname)&&StringUtil.isEmpty(phone)){
			return TipUtil.failed("loginname and phone is empty, at least one is not empty");
		}
		
		if(StringUtil.isEmpty(password)){
			return TipUtil.failed("password is empth!!");
		}
		Student student=new Student();	
		student.setLoginname(loginname);
		if(!StringUtil.isEmpty(phone)){
			student.setPhone(Integer.valueOf(phone));
		}
		student.setPassword(password);
		student=studentLoginService.login(student);
		if(student!=null){
			return TipUtil.success("sucessful login", student);
		}else{
			return TipUtil.failed("password does not match loginname");
		}	

	}
	
	@RequestMapping(value = "/updateMessage", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Object update(Student student){
		if(studentLoginService.updateStudent(student)){
			return TipUtil.success("sucessful login", student);
		}else{
			return TipUtil.failed("update failed!!!");
		}
	}

}
