package com.apcompany.api.service;

import java.util.List;

import com.apcompany.api.pojo.Teacher;
import com.apcompany.api.pojo.TeacherClass;

public interface TeacherService {
	
public boolean register(Teacher teacher);
	
	public Teacher login(Teacher teacher);
	
	//查看当前用户名是否可以使用
	public boolean nameIsUsed(String loginname);
	
	public boolean updateTeacher(Teacher teacher);
	
	public boolean delTeacher(int id);
	
	public Teacher loginByPhone(String phone);
	
	public Teacher loginByWechat(String openid);
	
	public boolean phoneIsUsed(String phone);
	
	public boolean wechatIsUsed(String openid);
	
	public int getIdbyname(String loginname);
	
	public boolean updateClass(List<TeacherClass> list);
	
	public boolean updateClass(Integer tid,List<Integer> types);

}
