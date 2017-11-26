package com.apcompany.api.service;

import com.apcompany.api.pojo.VideoAccount;

public interface IInviteVideoService {
	
	boolean inviteVideo(int studentId,int tcId);

	boolean cancleInvitationByStudent(int studentId);
	
	boolean resultInvite(int studentId,int status);
	
	boolean cancleInvitationByTeacher(int studentId);
	
	VideoAccount getVideoAccount(int teacherId);
	
	boolean pushVideoKey(int teacherId,String key);
	
}
