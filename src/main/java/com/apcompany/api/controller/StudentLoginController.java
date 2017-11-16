package com.apcompany.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.apcompany.api.pojo.Student;
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
<<<<<<< HEAD
	public Object loginByWechat(@RequestParam("code") String code) {
=======
	public Object loginByPhone(
			@RequestParam("openid") String openid,
			@RequestParam("password") double lat,
			@RequestParam("password") double lng
			) {
>>>>>>> 7c5aec36ffed6ed6da5df3bddef3d730179b8daa

		if (StringUtil.isEmpty(code)) {
			return TipUtil.failed("openid is empty");
		}
<<<<<<< HEAD
		
		JSONObject jsonObject=getAccessToken(code);
		
		String openID = jsonObject.getString("openid");
		
		if(openID==null||"".equals(openID)){
			return TipUtil.failed("wechat failed!!!");
		}
		
		if (studentLoginService.wechatIsUsed(openID)) {
			Student student = studentLoginService.loginByWechat(openID);
			return TipUtil.success(student);
		} else {
	
			return  TipUtil.success("new user!!",jsonObject);
=======
		if (studentLoginService.wechatIsUsed(openid)) {
			Student student = studentLoginService.loginByWechat(openid);
			return createToken(student.getId(), lat, lng);
		} else {
			Student student = new Student();
			student.setOpendid(openid);
			studentLoginService.register(student);
			return createToken(student.getId(), lat, lng);
>>>>>>> 7c5aec36ffed6ed6da5df3bddef3d730179b8daa
		}
	}
	
	@RequestMapping(value = "/register/wechat/", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Object registerByWechat(@RequestParam("phone") String phone,@RequestParam("code") String code,@RequestParam("openid") String openid,HttpServletRequest request) {
		HttpSession session = request.getSession(); 		
		String validCode = (String) session.getAttribute(VALIDATE_PHONE_CODE);  
        String validphone = (String) session.getAttribute(VALIDATE_PHONE);
        if(!"".equals(phone)&&validCode.equals(code)&&validphone.equals(phone)&&!"".equals(openid)){
        		Student student=new Student();
        		student.setPhone(phone);
        		student.setOpenid(openid);
        		boolean isSucessful = studentLoginService.register(student);
        		if(isSucessful){
        			return TipUtil.success("register successful",student);
        		}else{
        			return TipUtil.failed("register failed!");
        		}
        		
        }
		return TipUtil.failed("code do not match phone");

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

	@RequestMapping(value = "/validphone/{phone}", method = RequestMethod.GET)
	@ResponseBody
	public Object validPhone(@PathVariable String phone) {
		boolean out = studentLoginService.phoneIsUsed(phone);
		if (out) {
			return TipUtil.success(true);
		} else {
			return TipUtil.success(false);
		}
	}
	
<<<<<<< HEAD
	private  JSONObject getAccessToken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx1cc921938ad2e075"
        		+ "&secret=6940113a75b6b15bd596960e0c6a5dd9&code="+code+"&grant_type=authorization_code";
        URI uri = URI.create(url);
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(uri);

        HttpResponse response;
        try {
            response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();

                BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
                StringBuilder sb = new StringBuilder();

                for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
                    sb.append(temp);
                }

//                JSONObject object = new JSONObject(sb.toString().trim());
//                accessToken = object.getString("access_token");
//                openID = object.getString("openid");
//                refreshToken = object.getString("refresh_token");
//                expires_in = object.getLong("expires_in");
                JSONObject object=JSONObject.parseObject(sb.toString().trim());         
                return object;
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block 
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
	
=======
	
	private String createToken(int studentId,double lat,double lng){
		return infoService.addWithLogin(studentId, 0,UserStatusEnum.ONLINE, lat, lng);
	}
>>>>>>> 7c5aec36ffed6ed6da5df3bddef3d730179b8daa

}
