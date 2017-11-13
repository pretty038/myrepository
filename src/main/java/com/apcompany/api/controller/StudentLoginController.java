package com.apcompany.api.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apcompany.api.constrant.UserStatusEnum;
import com.apcompany.api.pojo.Student;
import com.apcompany.api.service.IUserOnlineInfoService;
import com.apcompany.api.service.StudentLoginService;
import com.apcompany.user.utils.MD5Util;
import com.apcompany.user.utils.SendMessage;
import com.apcompany.user.utils.StringUtil;
import com.apcompany.user.utils.TipUtil;

@Controller
@RequestMapping("/login/student")
public class StudentLoginController {

	private static final String VALIDATE_PHONE_CODE = "VALIDATE_PHONE_CODE";
	private static final String VALIDATE_PHONE = "VALIDATE_PHONE";
	private static final String SEND_CODE_TIME = "SEND_CODE_TIME";

	@Autowired
	private StudentLoginService studentLoginService;
	
	@Autowired private IUserOnlineInfoService infoService;
	

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Object register(@RequestParam("phone") String phone,@RequestParam("password") String password,
			@RequestParam("code") String code,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession(); 
		
		String validCode = (String) session.getAttribute(VALIDATE_PHONE_CODE);  
        String validphone = (String) session.getAttribute(VALIDATE_PHONE);
		
        if(validCode==null||validphone==null){
        	return TipUtil.failed("手机号或者验证码为空");
        }
        
        if(!"".equals(phone)&&validCode.equals(code)&&validphone.equals(phone)){
        	Student student=new Student();
        	student.setPhone(phone);
        	student.setPassword(MD5Util.getStringMD5String(password));
        	boolean isSucessful = studentLoginService.register(student);
        	if(isSucessful){
        		return TipUtil.success("注册成功",student);
        	}	
        }
		return TipUtil.failed("注册失败");

	}
	
	@RequestMapping(value ="/sendCode",method = RequestMethod.POST, produces = "text/html;charset=UTF-8")  
    @ResponseBody  
    public Object sendCode(@RequestParam("phone") String phone, HttpServletRequest request) throws HttpException, IOException {  
		String code=String .valueOf((int)((Math.random()*9+1)*100000));
		HttpSession session = request.getSession(); 
		session.setAttribute(VALIDATE_PHONE, phone);
	    session.setAttribute(VALIDATE_PHONE_CODE, code.toString());
	    session.setAttribute(SEND_CODE_TIME, new Date().getTime());
		return SendMessage.sentMessage(phone, code);	
    } 

	@RequestMapping(value = "/login/nomal", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Object login(
			@RequestParam(value="loginname",required=false)  String loginname, 
			@RequestParam(value="phone",required=false) String phone,
			@RequestParam("password") String password,
			@RequestParam("password") double lat,
			@RequestParam("password") double lng
			) {

		if (StringUtil.isEmpty(loginname) && StringUtil.isEmpty(phone)) {
			return TipUtil.failed("loginname and phone is empty, at least one is not empty");
		}

		if (StringUtil.isEmpty(password)) {
			return TipUtil.failed("password is empth!!");
		}
		Student student = new Student();
		student.setLoginname(loginname);
		if (!StringUtil.isEmpty(phone)) {
			student.setPhone(phone);
		}
		student.setPassword(MD5Util.getStringMD5String(password));
		student = studentLoginService.login(student);
		if (student != null) {
			return TipUtil.success("sucessful login", createToken(student.getId(), lat, lng));
		} else {
			return TipUtil.failed("password does not match loginname");
		}

	}

	@RequestMapping(value = "/login/phone", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Object loginByPhone(@RequestParam("phone") String phone,
			@RequestParam("code") String code,
			@RequestParam("password") double lat,
			@RequestParam("password") double lng,
			HttpServletRequest request) {
		HttpSession session = request.getSession(); 		
		String validCode = (String) session.getAttribute(VALIDATE_PHONE_CODE);  
        String validphone = (String) session.getAttribute(VALIDATE_PHONE);
        if(!"".equals(phone)&&validCode.equals(code)&&validphone.equals(phone)){
        	Student student = studentLoginService.loginByPhone(phone);
        	return TipUtil.success("sucessful login",createToken(student.getId(), lat, lng));	
        }
		return TipUtil.failed("code do not match phone");
	}

	@RequestMapping(value = "/login/wechat", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Object loginByPhone(
			@RequestParam("openid") String openid,
			@RequestParam("password") double lat,
			@RequestParam("password") double lng
			) {

		if (StringUtil.isEmpty(openid)) {
			return TipUtil.failed("openid is empty");
		}
		if (studentLoginService.wechatIsUsed(openid)) {
			Student student = studentLoginService.loginByWechat(openid);
			return createToken(student.getId(), lat, lng);
		} else {
			Student student = new Student();
			student.setOpendid(openid);
			studentLoginService.register(student);
			return createToken(student.getId(), lat, lng);
		}

	}

	@RequestMapping(value = "/updateMessage", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Object update(Student student) {
		if (studentLoginService.updateStudent(student)) {
			return TipUtil.success("sucessful login", student);
		} else {
			return TipUtil.failed("update failed!!!");
		}
	}

	@RequestMapping(value = "/validname/{name}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Object validName(@PathVariable String name) {
		boolean out = studentLoginService.nameIsUsed(name);
		if (out) {
			return TipUtil.success(true);
		} else {
			return TipUtil.success(false);
		}
	}

	@RequestMapping(value = "/validphone/{phone}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Object validPhone(@PathVariable String phone) {
		boolean out = studentLoginService.phoneIsUsed(phone);
		if (out) {
			return TipUtil.success(true);
		} else {
			return TipUtil.success(false);
		}
	}
	
	
	private String createToken(int studentId,double lat,double lng){
		return infoService.addWithLogin(studentId, 0,UserStatusEnum.ONLINE, lat, lng);
	}

}
