package com.apcompany.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.apcompany.api.model.pojo.StudentSaveDO;


public interface IStudentSaveDao {
	
	@Select("select * from student_save_teach_course where student_id=#{studentId} limit #{index},#{pageSize}")
	public List<StudentSaveDO> getStudentSavingList(@Param("studentId") int studentId,@Param("index") int index,@Param("pageSize") int pageSize);
	
	@Insert("insert into student_save_teach_course("
			+ "student_id,teach_course_id,save_time) "
			+ " values(#{studentId},#{teachCourseId},NOW())")
	public void saveTeachCourse(StudentSaveDO studentSaveDO);
	
	@Select("select * from student_save_teach_course where student_id=#{studentId} and "
			+ " teach_course_id=#{teachCourseId} limit 1")
	public StudentSaveDO checkSavaIsExit(@Param("studentId") int studentId,@Param("teachCourseId") int teachCoueseId);

	
	@Delete("delete from student_save_teach_course where id=#{id}")
	public void deleteById(int id);

}
