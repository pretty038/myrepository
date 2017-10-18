package com.apcompany.api.service;

import com.apcompany.api.pojo.Student;

public interface StudentLoginService {

	public boolean register(Student student);
	
	public Student login(Student student);
	
	//查看当前用户名是否可以使用
	public boolean nameIsUsed(String loginname);
	
	public boolean updateStudent(Student student);
	
	public boolean delStudent(int id);
	
	public Student loginByPhone(String phone);
	
	public Student loginByWechat(String openid);
	
	public boolean phoneIsUsed(String phone);
	
	public boolean wechatIsUsed(String openid);
	
}
