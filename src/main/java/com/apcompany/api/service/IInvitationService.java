package com.apcompany.api.service;

import com.apcompany.api.model.pojo.InvitationTeachDO;

public interface IInvitationService {
	
	boolean createInvitation(int studentId, int teachCourseId);

	boolean cancleInvitationByStudent(int studentId);
	
	InvitationTeachDO getHandleInvitationByStudent(int studentId);
	
	//teacher service
	InvitationTeachDO getHandleInvitationByTeacher(int teacherId);
	
	boolean dealWithInvitationByTeacher(int teacherId,int status);
	
	
}
