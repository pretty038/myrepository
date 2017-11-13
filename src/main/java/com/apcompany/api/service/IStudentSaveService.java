package com.apcompany.api.service;

import java.util.List;

import com.apcompany.api.model.pojo.StudentSaveDO;


public interface IStudentSaveService {

	boolean saveTeachCourse(int studentId,int teachCoueseId);
	
	List<StudentSaveDO> getStudentSavaTeachCourseList(int studentId,int index,int pageSize);

	boolean deleteById(int id); 
}
