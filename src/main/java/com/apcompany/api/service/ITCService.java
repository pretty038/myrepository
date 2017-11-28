package com.apcompany.api.service;

import java.util.List;
import com.apcompany.api.model.pojo.CourseDO;
import com.apcompany.api.model.pojo.TeachCourseDO;

public interface ITCService {
	
	//******************admin start****************
    boolean createCourse(String courseName);	
	boolean createTeachCourse(int teacherId, int courseId, int moneyPerMinu);
	//******************admin end**************** 
	
		
	
	//******************common start**************** 
	List<CourseDO> getAllCourseList();
	CourseDO getCourseById(Integer id);
	TeachCourseDO getTCById(Integer id);
	List<Integer> getTCIdByTeacherId(Integer teacherId);
	void updateScoreOfTeachCourse(int teachCourseID, float teachScore);
	Integer getTeacherIdById(int teachCourseId);
	
	//******************common end****************   

	

}
