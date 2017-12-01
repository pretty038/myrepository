package com.apcompany.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.apcompany.api.model.form.OnlineTCForm;
import com.apcompany.api.model.pojo.CourseDO;
import com.apcompany.api.model.pojo.OnlineTCInfoDO;
import com.apcompany.api.model.pojo.TeachCourseDO;

public interface ITeachCourseDao {
	
	@Select("select * from course where id=#{id} ")
	CourseDO getCourseById(@Param("id")int id);
	
	@Select("select * from course")
	List<CourseDO> getAllCourseList();

	@Insert("insert ignore into course(name,created,modified) values(#{name},now(),now() )")
	boolean addCourse(
			@Param("name")String name);
	
	@Select("select t_c.id as teach_course_id ,t_c.teach_score  teach_score,t_c.money_per_minute  money_per_minute,t_c.status as teach_course_status,"
			+ "t.name as teacher_name,t.id as teacher_id, "
			+ "t.imageurl as photo,t_info.college_name as college_name,t_info.grade as grade ,"
			+ " t_info.profession as profession, "
			+ " online.status as teacher_status, x(online.address) as lat,y(online.address) as lng "
			+ "from teach_course as t_c "
			+ "inner join teacher as t  on t.id=t_c.teacher_id "
			+"inner join user_online_info as online "
			+ "on online.type=1 and  t_c.teacher_id=online.user_id "
			+ " inner join teacher_base_info t_info on t_info.teacher_id=t.id "
			+ " where t_c.course_id=#{courseId} and online.status=1 and t_c.status=1 order by #{orderType} "
			+ " limit #{index},#{limit}")
	List<OnlineTCInfoDO> getOnlineListBySubject(OnlineTCForm form);
	
	@Select("select * from teach_course as t_c where t_c.teacher_id=#{teacherId} ")
	List<TeachCourseDO> getTCListByTId(int teacherId);
	
	@Update("update teach_course set status=#{status} where id=#{id} and teacher_id=#{teacherId}")
	int updateTCStatus(
			@Param("id") int id,
			@Param("teacherId") int teacherId,
			@Param("status") int status
			);
	
	@Select("select * from teach_course where teacher_id=#{teacherId} and course_id=#{courseId} limit 1")
	TeachCourseDO checkTeachCourseIsExit(
			@Param("teacherId")int teacherId,
			@Param("courseId")int courseId);
	
	@Insert("insert into teach_course(teacher_id,course_id,start_teach_day,"
			+ "teach_score,teach_student_num,created,modified,status,money_per_minute) "
			+ "values(#{teacherId},#{courseId},now(),"
			+ "0,0,now(),now(),1,#{moneyPerMinute}) ")
	@SelectKey(statement = "SELECT LAST_INSERT_ID() as id", keyProperty = "id", before = false, resultType = Integer.class)
	void addTeachCourse(TeachCourseDO teacherCourseDO);
	
	@Update("update teach_course set teach_score=#{teachScore},teach_student_num=#{teachStudentNum},modified=NOW() "
			+ " where teacher_id=#{teacherId} and course_id=#{courseId} ")
	void updateScoreOfTeachCourse(TeachCourseDO teacherCourseDO);
		
	@Select("select * from teach_course where id=#{id}")
	TeachCourseDO getTCById(@Param("id") int id);
	
	@Select("select teacher_id from teach_course  where id=#{id} ")
	Integer getTeacherIdById(
			@Param("id") int id);

	@Select("select id from teach_course where teacher_id=#{teacherId}")
	List<Integer> getTCIdByTeacherId(
			@Param("teacherId")int teacherId);
	
}
