package com.apcompany.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.api.dao.StudentDao;
import com.apcompany.api.pojo.Student;
import com.apcompany.api.service.StudentLoginService;

@Service
public class StudentLoginServiceImpl implements StudentLoginService {

	@Autowired
	private StudentDao studentDao;

	@Override
	public boolean register(Student student) {

		if (student == null) {
			return false;
		} else {
			return studentDao.insert(student) > 0 ? true : false;
		}
	}

	@Override
	public Student login(Student student) {
		if (student == null) {
			return null;
		} else if (student.getPhone() > 0) {
			return studentDao.loginin(student.getPhone(), student.getPassword());
		} else {
			return studentDao.loginin(student.getLoginname(), student.getPassword());
		}

	}

	@Override
	public boolean nameIsUsed(String loginname) {

		return studentDao.isNameUsed(loginname) > 0 ? true : false;
	}

	@Override
	public boolean updateStudent(Student student) {
		
		return studentDao.update(student) > 0 ? true : false;
		
	}

	@Override
	public boolean delStudent(int id) {
		
		return studentDao.delete(id) > 0 ? true : false;
	}

}
