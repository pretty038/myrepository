package com.apcompany.api.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.apcompany.api.model.pojo.TeacherBaseInfoDO;

public interface ITeacherBaseInfoDAO {
	
	@Select("SELECT * FROM teacher_base_info "
			+ " WHERE teacher_id=#{teacherId}")
    TeacherBaseInfoDO getByTeacherId(
    		@Param("teacherId")int teacherId);

}
