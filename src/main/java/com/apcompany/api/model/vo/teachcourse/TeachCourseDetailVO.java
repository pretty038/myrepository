package com.apcompany.api.model.vo.teachcourse;


import com.apcompany.api.model.schema.teacher.TeacherInfoDO;

public class TeachCourseDetailVO extends TeachCourseVO {

	private String teacherName;

	public TeachCourseDetailVO(){}

    public TeachCourseDetailVO of(TeacherInfoDO teacherInfoDO){
		this.setTeacherName(teacherInfoDO.getName());
		return this;
	}



	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
}
