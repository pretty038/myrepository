package com.apcompany.api.service.impl;

import com.apcompany.api.service.teachcourse.ITeachCourseService;
import com.apcompany.api.service.teachcourse.ITeacherTeachCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apcompany.api.config.VideoSecureConfig;
import com.apcompany.api.constrant.InviteVideoStatusEnum;
import com.apcompany.api.constrant.MessagePushEnum;
import com.apcompany.api.constrant.TeachCourseStatusEnum;
import com.apcompany.api.dao.IInvitationTeachDao;
import com.apcompany.api.model.schema.InvitationTeachDO;
import com.apcompany.api.model.schema.teachcourse.TeachCourseDO;
import com.apcompany.api.model.schema.TeachOrderDO;
import com.apcompany.api.pojo.VideoAccount;
import com.apcompany.api.service.IBookTeachService;
import com.apcompany.api.service.IInviteVideoService;
import com.apcompany.api.service.IMessagePushService;
import com.apcompany.api.service.IWalletService;
import com.apcompany.api.service.ITeachOrderService;
import com.apcompany.api.service.IUserOnlineInfoService;

@Service
public class InviteVideoServiceImp implements IInviteVideoService {
	
	@Autowired VideoSecureConfig videoSecureConfig;
	@Autowired
	private IMessagePushService messagePushService;
	@Autowired
	private IInvitationTeachDao invitationTeachDao;
	@Autowired
	private ITeachOrderService teachOrderService;
	@Autowired
	private ITeachCourseService tcService;
	@Autowired
	private ITeacherTeachCourseService teacherTCService;
	@Autowired
	private IBookTeachService bookService;
	@Autowired
	private IUserOnlineInfoService userOnlineInfoService;
	
	@Autowired private IWalletService walletService;

	@Override
	public int inviteVideo(int studentId, int teachCourseId) {
		// check 当前是否已经有在进行中的邀请。
		InvitationTeachDO invitationTeachDO = getHandleInvitationByStudent(studentId);
		if (invitationTeachDO != null) {
			return 0;
		}
		//tc is unnormal
		if (!userOnlineInfoService.checkTCNormal(teachCourseId)) {
			return 0;
		}
		TeachCourseDO teachCourseDO = tcService.getTCById(teachCourseId);
        if(teachCourseDO==null){
        	return 0;
        }
		if (getHandleInvitationByTeacher(teachCourseDO.getTeacherId()) != null) {
			return 0;
		}
		invitationTeachDao.add(InvitationTeachDO.buildNew(studentId,teachCourseDO.getTeacherId(),
				teachCourseId));
		messagePushService.pushMessageToTeacher(teachCourseId, MessagePushEnum.OPEN_VIDEO);
		int totalMoney = walletService.getStudentMoney(studentId);
		return totalMoney/teachCourseDO.getMoneyPerMinute();
	}

	@Override
	public TeachOrderDO closeInvitationByStudent(int studentId) {
		TeachOrderDO teachOrderDO = null;
		InvitationTeachDO invitationTeachDO = getHandleInvitationByStudent(studentId);
		if (invitationTeachDO == null) {
			return teachOrderDO;
		}
		if (invitationTeachDO.getStatus()==InviteVideoStatusEnum.WAIT.getKey()){
			invitationTeachDO.onCut();
			messagePushService.pushMessageToTeacher(invitationTeachDO.getTeacherId(), MessagePushEnum.STUDENT_CUT);
		}else{
			invitationTeachDO.onCommit();
			teachOrderDO = teachOrderService.createTeachOrder(invitationTeachDO);
			messagePushService.pushMessageToTeacher(invitationTeachDO.getTeacherId(), MessagePushEnum.VIDEO_FINISH);
		}		
		invitationTeachDao.update(invitationTeachDO);
		return teachOrderDO;
	}
	
	@Override
	public boolean successInvite(int studentId) {
		InvitationTeachDO invitationTeachDO = getHandleInvitationByStudent(studentId);
		if (invitationTeachDO == null || invitationTeachDO.getStatus()!=InviteVideoStatusEnum.CONN.getKey()) {
			return false;
		}	
		invitationTeachDao.update(invitationTeachDO.onConnection());
		messagePushService.pushMessageToTeacher(invitationTeachDO.getTeacherId(), MessagePushEnum.VIDEO_CONN);
		return true;
	}
	

	@Override
	public TeachOrderDO closeInvitationByTeacher(int teacherId) {
		TeachOrderDO teachOrderDO = null;
		InvitationTeachDO invitationTeachDO = getHandleInvitationByTeacher(teacherId);
		if (invitationTeachDO == null) {
			return teachOrderDO;
		}
		if (invitationTeachDO.getStatus()==InviteVideoStatusEnum.WAIT.getKey()){
			invitationTeachDao.update(invitationTeachDO.onCut());
			messagePushService.pushMessageToStudent(invitationTeachDO.getStudentId(),MessagePushEnum.TEACHER_CUT);
		}else{
			messagePushService.pushMessageToStudent(invitationTeachDO.getStudentId(),MessagePushEnum.VIDEO_FINISH);
		}		
		teacherTCService.updateStatus(teacherId, invitationTeachDO.getTeachCourseId(), TeachCourseStatusEnum.NORMAL);		
		videoSecureConfig.returnFree(invitationTeachDO.getId());
		return teachOrderDO;
	}


	@Override
	public VideoAccount getVideoAccount(int teacherId) {
		InvitationTeachDO invitationTeachDO = getHandleInvitationByTeacher(teacherId);
		if (invitationTeachDO == null) {
			return null;
		}
		teacherTCService.updateStatus(teacherId, invitationTeachDO.getTeachCourseId(), TeachCourseStatusEnum.BUSY);
		return videoSecureConfig.getFreeOne(invitationTeachDO.getId());
	}

	@Override
	public boolean pushVideoKey(int teacherId, String key) {
		InvitationTeachDO invitationTeachDO = getHandleInvitationByTeacher(teacherId);
		if (invitationTeachDO == null) {
			return false;
		}
		messagePushService.pushMessageToStudent(invitationTeachDO.getStudentId(), key);
		return true;
	}
	
	
	private InvitationTeachDO getHandleInvitationByStudent(int studentId) {
		return invitationTeachDao.getHandleInvitationByStudent(studentId);
	}

	private InvitationTeachDO getHandleInvitationByTeacher(int teacherId) {
		return invitationTeachDao
				.getHandleInvitationByTeacher(teacherId);
	}

	@Override
	public boolean checkVideoConn(int teacherId) {
		InvitationTeachDO invitationTeachDO = getHandleInvitationByTeacher(teacherId);
		if (invitationTeachDO != null && invitationTeachDO.getStatus()==InviteVideoStatusEnum.CONN.getKey()) {
			return true;
		}
		return false;
	}
	
	
	

	

}
