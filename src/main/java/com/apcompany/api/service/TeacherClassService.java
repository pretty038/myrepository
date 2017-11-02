package com.apcompany.api.service;

import java.util.List;

import com.apcompany.api.pojo.TeacherClass;

public interface TeacherClassService {
	
	public List<Integer> getTypeByTid(Integer tid);
	
	
	public int insert(TeacherClass teacherClass);
	
	public int update(TeacherClass teacherClass);
	
	public List<TeacherClass> getByTid(Integer tid);
	
	public int delByTid(Integer id);

}
