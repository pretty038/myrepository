package com.apcompany.api.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import com.apcompany.api.model.pojo.InvitationTeachDO;

public interface IInvitationTeachDao {
	
	@Select("select * from invitation_teach where student_id=#{studentId} and status=1 limit 1")
	
	public InvitationTeachDO getHandleInvitationByStudent(int studentId);
	
	@Insert("insert into invitation_teach(student_id,teach_course_id,status,created,modified) values(#{studentId},#{teachCourseId},"
			+ "#{status},now(),now())")
	@SelectKey(statement = "SELECT LAST_INSERT_ID() as id", keyProperty = "id", before = false, resultType = Integer.class)
	public void addInvitation(InvitationTeachDO invitationTeachDO);
	
	@Update("update invitation_teach set status=#{status} and modified= now() where id=#{id}")
	public void updateStatus(InvitationTeachDO invitationTeachDO);
	
	@Select("select * from invitation_teach where teach_course_id=#{teachCourseId} and status=0 limit 1")
	public InvitationTeachDO getHandleInvitationByTeachCourse(int teachCourseId);
	
		
	@Select("select * from invitation_teach where id=#{id}")
	public InvitationTeachDO getById(int id);

}
