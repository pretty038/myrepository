package com.apcompany.api.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import com.apcompany.api.model.schema.InvitationTeachDO;

public interface IInvitationTeachDao {
	
	@Select("select * from invitation_teach where student_id=#{studentId} and status in (1,3) limit 1")
	InvitationTeachDO getHandleInvitationByStudent(int studentId);
	
	@Insert("insert ignore into "
			+ " invitation_teach( "
			+ "   student_id,"
			+ "   teacher_id,"
			+ "   teach_course_id,"
			+ "   status,"
			+ "   begin_time,"
			+ "   end_time,"
			+ "   created,"
			+ "   modified"
			+ " ) "
			+ " values( "
			+ "   #{studentId},"
			+ "   #{teacherId},"
			+ "   #{teachCourseId},"
			+ "   #{status},"
			+ "   #{beginTime},"
			+ "   #{endTime},"
			+ "   now(),"
			+ "   now()"
			+ " )")
	@SelectKey(statement = "SELECT LAST_INSERT_ID() as id", keyProperty = "id", before = false, resultType = Integer.class)
	void add(InvitationTeachDO invitationTeachDO);
	
	@Update("update invitation_teach "
			+ " set begin_time=#{beginTime} and end_time=#{endTime} "
			+ " and status=#{status} and modified= now() where id=#{id}")
	void update(InvitationTeachDO invitationTeachDO);
	
	@Select("<script>"
			+ " SELECT * FROM invitation_teach "
			+ " WHERE teacher_id=#{teacherId} "
			+ " and status in (1,3) limit 1"
			+ "</script>")
	InvitationTeachDO getHandleInvitationByTeacher(
			@Param("teacherId") Integer teacherId);
	

}
