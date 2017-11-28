package com.apcompany.api.service;

import com.apcompany.api.model.pojo.TeachOrderDO;
import com.apcompany.api.pojo.VideoAccount;

public interface IInviteVideoService {
	
	int inviteVideo(int studentId,int tcId);

	TeachOrderDO closeInvitationByStudent(int studentId);
	
	boolean successInvite(int studentId);
	
	TeachOrderDO closeInvitationByTeacher(int studentId);
	
	VideoAccount getVideoAccount(int teacherId);
	
	boolean pushVideoKey(int teacherId,String key);
	
	boolean checkVideoConn(int teacherId);
	
}
