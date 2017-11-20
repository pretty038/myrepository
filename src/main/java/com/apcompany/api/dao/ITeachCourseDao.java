package com.apcompany.api.dao;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import com.apcompany.api.model.pojo.CourseDO;
import com.apcompany.api.model.pojo.OnlineTeachCourseDetailInfoDO;
import com.apcompany.api.model.pojo.TeachCourseDO;

public interface ITeachCourseDao {
	
	@Select("select * from course where id=#{id} ")
	public CourseDO getCourseById(@Param("id")int id);
	
	@Select("select * from course")
	public List<CourseDO> getAllCourseList();

	@Insert("insert ignore into course(name,created,modified) values(#{name},now(),now() )")
	public boolean addCourse(
			@Param("name")String name);
	
	@Select("select t_c.id as teach_course_id ,t_c.teach_score  teach_score,t_c.money_per_minute  money_per_minute,t_c.status as teach_course_status,"
			+ "t.name as teacher_name,t.id as teacher_id, "
			+ "t.imageurl as photo, "
			+ " online.status as teacher_status, x(online.address) as lat,y(online.address) as lng "
			+ "from teach_course as t_c "
			+ "inner join teacher as t  on t.id=t_c.teacher_id "
			+"inner join user_online_info as online "
			+ "on online.type=1 and  t_c.teacher_id=online.user_id "
			+ " where t_c.course_id=#{courseId} and online.status=1 order by #{orderType} "
			+ " limit #{queryIndex},#{querySize}")
	public List<OnlineTeachCourseDetailInfoDO> getOnlineListBySubject(
			@Param("courseId") int courseId,
			@Param("orderType") String orderType,
			@Param("queryIndex") int queryIndex,
			@Param("querySize") int querySize);
	
	@Select("select * from teach_course where teacher_id=#{teacherId} and course_id=#{courseId} limit 1")
	public TeachCourseDO checkTeachCourseIsExit(
			@Param("teacherId")int teacherId,
			@Param("courseId")int courseId);
	
	@Insert("insert into teach_course(teacher_id,course_id,start_teach_day,"
			+ "teach_score,teach_student_num,created,modified,status,money_per_minute) "
			+ "values(#{teacherId},#{courseId},now(),"
			+ "0,0,now(),now(),1,#{moneyPerMinute}) ")
	@SelectKey(statement = "SELECT LAST_INSERT_ID() as id", keyProperty = "id", before = false, resultType = Integer.class)
	public void addTeachCourse(TeachCourseDO teacherCourseDO);
	
	@Update("update teach_course set teach_score=#{teachScore},teach_student_num=#{teachStudentNum},modified=NOW() "
			+ " where teacher_id=#{teacherId} and course_id=#{courseId} ")
	public void updateScoreOfTeachCourse(TeachCourseDO teacherCourseDO);
		
	@Select("select * from teach_course where id=#{id}")
	public TeachCourseDO getTCById(@Param("id") int id);
	
	@Select("select teacher.id from teach_course inner join teacher on teach_course.teacher_id=teacher.id where teach_course.id=#{teachCourseId} ")
	public Integer getTeacherByTeachCourseId(
			@Param("teachCourseId") int teachCourseId);

	@Select("select teach_course.id as id from teach_course inner join teacher on teach_course.teacher_id=teacher.id where teacher.id=#{teacherId}")
	public Integer getTCIdByTeacherId(
			@Param("teacherId")int teacherId);
	
}
