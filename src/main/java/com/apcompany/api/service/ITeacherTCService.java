package com.apcompany.api.service;

import java.util.List;

import com.apcompany.api.constrant.TeachCourseStatusEnum;
import com.apcompany.api.model.pojo.TeachCourseDO;

public interface ITeacherTCService {

	// ************************TEACHER INTERFACE start************************
	
	List<TeachCourseDO> getTCListByTId(int teacherId);

	boolean preparedToTeach(int teacherId, int teachCourseId);

	boolean closeToTeach(int teacherId, int teachCourseId);
	
	boolean updateStatus(int teacherId,int teachCourseId,TeachCourseStatusEnum status);
	
	// **********************TEACHER INTERFACE end************************

}
