package com.apcompany.api.service.teachcourse;

import com.apcompany.api.constrant.TeachCourseStatusEnum;
import com.apcompany.api.model.vo.teachcourse.TeachCourseVO;

import java.util.List;

public interface ITeacherTeachCourseService {

	boolean applyTeachCourse(int teacherId,int courseId,int moneyPerMinute);

	List<TeachCourseVO> getMyTeachCourseList(int teacherId);

	boolean updateStatus(int teacherId, int teachCourseId, TeachCourseStatusEnum status);

	boolean deleteTeachCourse(int id,int teacherId);

}
