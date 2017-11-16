package com.apcompany.api.service;

import java.util.List;

import com.apcompany.api.model.pojo.CourseDO;
import com.apcompany.api.model.pojo.OnlineTeachCourseDetailInfoDO;
import com.apcompany.api.model.pojo.TeachCourseDO;

public interface ITeachCourseService {

	List<CourseDO> getAllCourseList();

	CourseDO getCourseById(Integer id);
	
	boolean createCourse(String courseName);
	
	boolean createTeachCourse(int teacherId, int courseId, int moneyPerMinu);

	/*
	 * 获取指定科目在线的教师，需要分页获取
	 * 
	 * @Param subject 查询的科目， queryindex 开始查询的角标，querySize：每页显示数量，默认：10
	 */
	List<OnlineTeachCourseDetailInfoDO> getOnlineTeachCourseListByCourse(int courseId,
			int orderType, int queryIndex, int querySize);
	
	

	void updateScoreOfTeachCourse(int teachCourseID, float teachScore);

	

	Integer getTIdByTeachCourseId(int teachCourseId);
	

	TeachCourseDO getTCById(Integer id);
	
	Integer getTCIdByTeacherId(Integer teacherId);
	
	boolean checkTeachCourseIsValid(int teachCourseId);

}
