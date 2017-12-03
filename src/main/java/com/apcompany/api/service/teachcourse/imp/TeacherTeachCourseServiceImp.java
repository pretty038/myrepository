package com.apcompany.api.service.teachcourse.imp;

import com.apcompany.api.constrant.TeachCourseStatusEnum;
import com.apcompany.api.dao.ITeachCourseDao;
import com.apcompany.api.model.schema.teachcourse.TeachCourseDO;
import com.apcompany.api.model.schema.teachcourse.CourseDO;
import com.apcompany.api.model.vo.teachcourse.TeachCourseVO;
import com.apcompany.api.service.teachcourse.ICourseService;
import com.apcompany.api.service.teachcourse.ITeacherTeachCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherTeachCourseServiceImp implements ITeacherTeachCourseService {
	
	@Autowired private ITeachCourseDao teachCourseDao;
	@Autowired private ICourseService courseService;


	@Override
	public boolean applyTeachCourse(int teacherId, int courseId,int moneyPerMinute) {
		CourseDO courseDO = courseService.getById(courseId);
		if(courseDO == null){
			return false;
		}
		return teachCourseDao.addTeachCourse(new TeachCourseDO().apply(teacherId,courseId,moneyPerMinute));
	}

	@Override
	public List<TeachCourseVO> getMyTeachCourseList(int teacherId) {

		List<TeachCourseDO> teachCourseDOList =  teachCourseDao.getTCListByTId(teacherId);
		return teachCourseDOList.stream().map(teachCourseDO -> new TeachCourseVO()
				.of(teachCourseDO)
				.of(courseService.getById(teachCourseDO.getCourseId()))).collect(Collectors.toList());
	}


	@Override
	public boolean updateStatus(int teacherId, int teachCourseId,TeachCourseStatusEnum status) {
		int id = teachCourseDao.chekcTCIsValid(teachCourseId,teacherId);
		if (id == 0 ) return false;
		teachCourseDao.updateTCStatus(teachCourseId, teacherId, status.getKey());
		return true;
	}

	@Override
	public boolean deleteTeachCourse(int id, int teacherId) {
		return teachCourseDao.deleteTCByTeacher(id,teacherId);
	}

}
