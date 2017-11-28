package com.apcompany.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.api.constrant.TeachCourseStatusEnum;
import com.apcompany.api.dao.ITeachCourseDao;
import com.apcompany.api.model.pojo.TeachCourseDO;
import com.apcompany.api.service.ITeacherTCService;

@Service
public class TeacherTCServiceImp implements ITeacherTCService {
	
	@Autowired private ITeachCourseDao teachCourseDao;
	
	@Override
	public List<TeachCourseDO> getTCListByTId(int teacherId) {		
		return teachCourseDao.getTCListByTId(teacherId);
	}

	@Override
	public boolean preparedToTeach(int teacherId, int teachCourseId) {
		int result = teachCourseDao.updateTCStatus(teachCourseId, teacherId, TeachCourseStatusEnum.BUSY.getKey());
		return result>0?true:false;
	}

	@Override
	public boolean closeToTeach(int teacherId, int teachCourseId) {
		int result = teachCourseDao.updateTCStatus(teachCourseId, teacherId, TeachCourseStatusEnum.CLOSE.getKey());
		return result>0?true:false;
	}

	@Override
	public boolean updateStatus(int teacherId, int teachCourseId,TeachCourseStatusEnum status) {
		teachCourseDao.updateTCStatus(teachCourseId, teacherId, status.getKey());
		return true;
	}

}
