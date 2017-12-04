package com.apcompany.api.service.teachcourse;

import com.apcompany.api.constrant.TeachCourseStatusEnum;
import com.apcompany.api.model.vo.ApiResponse;
import com.apcompany.api.model.vo.teachcourse.TeachCourseVO;

import java.util.List;
import java.util.Map;

public interface ITeacherTeachCourseService {

	ApiResponse applyTeachCourse(int teacherId,int courseId,int moneyPerMinute);

	ApiResponse getMyTeachCourseList(int teacherId);

	ApiResponse updateStatus(int teacherId, int teachCourseId, TeachCourseStatusEnum status);

	ApiResponse deleteTeachCourse(int id,int teacherId);

}
