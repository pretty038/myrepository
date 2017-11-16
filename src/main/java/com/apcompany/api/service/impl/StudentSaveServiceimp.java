package com.apcompany.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.api.dao.IStudentSaveDao;
import com.apcompany.api.model.pojo.StudentSaveDO;
import com.apcompany.api.service.IStudentSaveService;
@Service
public class StudentSaveServiceimp implements IStudentSaveService {

	@Autowired private IStudentSaveDao studentSaveDao;

	@Override
	public boolean saveTeachCourse(int studentId,int teachCoueseId) {		
		StudentSaveDO studentSaveDO = studentSaveDao.checkSavaIsExit(studentId, teachCoueseId);
		if(studentSaveDO !=null ){
			System.out.println("您已经收藏过了");
			return false;			
		}
		studentSaveDO = new StudentSaveDO(studentId,teachCoueseId);
		studentSaveDao.saveTeachCourse(studentSaveDO);
		return true;
	}

	@Override
	public List<StudentSaveDO> getStudentSavaTeachCourseList(
			int studentId,int index,int pageSize) {
		return studentSaveDao.getStudentSavingList(studentId, index, pageSize);
	}

	@Override
	public boolean deleteById(int id) {
		try {
			studentSaveDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
