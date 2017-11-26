package com.apcompany.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apcompany.api.constrant.TeachCourseStatusEnum;
import com.apcompany.api.dao.ITeachCourseDao;
import com.apcompany.api.model.pojo.CourseDO;
import com.apcompany.api.model.pojo.TeachCourseDO;
import com.apcompany.api.service.IBookTeachService;
import com.apcompany.api.service.ITCService;
import com.apcompany.api.service.IUserOnlineInfoService;


@Service
public class TCServiceImp implements ITCService {
	
	@Autowired private IUserOnlineInfoService userOnlineInfoService;
	
	@Autowired private ITeachCourseDao teachCourseDao;
	@Autowired private IBookTeachService bookingTeachService;
	
	
	@Override
	public boolean createCourse(String courseName) {
		teachCourseDao.addCourse(courseName);
		return true;
	}
	
	@Override
	public List<CourseDO> getAllCourseList() {
		return teachCourseDao.getAllCourseList();
	}

	
	@Override
	public CourseDO getCourseById(Integer id) {
		if(id==null || id<=0){
			return null;
		}
		return teachCourseDao.getCourseById(id);
	}

	
	@Transactional
	@Override
	public boolean createTeachCourse(int teacherId, int courseId,
			int moneyPerMinu) {
		CourseDO courseDO = getCourseById(courseId);
		if(courseDO==null){
			return false;
		}
		TeachCourseDO teachCourseDO = teachCourseDao.checkTeachCourseIsExit(teacherId, courseId);
		if(teachCourseDO!=null){
			return false;
		}
		teachCourseDO = TeachCourseDO.createNew(teacherId, courseId,moneyPerMinu);
		teachCourseDao.addTeachCourse(teachCourseDO);
		//init one year book day data
		return bookingTeachService.initOneYearBookDayList(teachCourseDO.getId());
	}

	@Override
	public void updateScoreOfTeachCourse(int teachCourseID, float teachScore) {
		TeachCourseDO teacherCourseDO = teachCourseDao.getTCById(teachCourseID);
		if( null == teacherCourseDO){
			return;
		}else{
			float oldAvgScore = teacherCourseDO.getTeachScore();
			int oldTeachNum = teacherCourseDO.getTeachStudentNum();
			float avgScore = (oldAvgScore*oldTeachNum + teachScore)/(oldTeachNum+1);
			teacherCourseDO.setTeachScore(avgScore);
			teacherCourseDO.setTeachStudentNum(oldTeachNum+1);
			teachCourseDao.updateScoreOfTeachCourse(teacherCourseDO);
		}
		
	}		
	

	@Override
	public Integer getTIdByTeachCourseId(int teachCourseId) {
		return teachCourseDao.getTeacherByTeachCourseId(teachCourseId);
	}

	@Override
	public TeachCourseDO getTCById(Integer id) {
		if(id==null){
			return null;
		}
		return teachCourseDao.getTCById(id);
	}

	@Override
	public Integer getTCIdByTeacherId(Integer teacherId) {		
		return teachCourseDao.getTCIdByTeacherId(teacherId);
	}

	@Override
	public void updateTCStatus(int id,int teacherId, TeachCourseStatusEnum e) {
		teachCourseDao.updateTCStatus(id, teacherId, e.getKey());
		
	}

	

}