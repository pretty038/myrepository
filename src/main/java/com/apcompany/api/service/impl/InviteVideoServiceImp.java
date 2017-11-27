package com.apcompany.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.api.config.VideoSecureConfig;
import com.apcompany.api.constrant.InviteVideoStatusEnum;
import com.apcompany.api.constrant.MessagePushEnum;
import com.apcompany.api.constrant.TeachCourseStatusEnum;
import com.apcompany.api.dao.IInvitationTeachDao;
import com.apcompany.api.model.pojo.InvitationTeachDO;
import com.apcompany.api.pojo.VideoAccount;
import com.apcompany.api.service.IBookTeachService;
import com.apcompany.api.service.IInviteVideoService;
import com.apcompany.api.service.IMessagePushService;
import com.apcompany.api.service.ITCService;
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
	private ITCService teachCourseService;
	@Autowired
	private IBookTeachService bookService;
	@Autowired
	private IUserOnlineInfoService userOnlineInfoService;

	@Override
	public boolean inviteVideo(int studentId, int teachCourseId) {
		// check 当前是否已经有在进行中的邀请。
		InvitationTeachDO invitationTeachDO = getHandleInvitationByStudent(studentId);
		if (invitationTeachDO != null) {
			return true;
		}
		// 检查该老师是否正在被预约中。例如老师未同意邀请，但是也未处理其他用户的邀请信息。
		invitationTeachDO = invitationTeachDao
				.getHandleInvitationByTeachCourse(teachCourseId);
		if (invitationTeachDO != null) {
			return false;
		}
		//tc is unnormal
		if (!userOnlineInfoService.checkTCNormal(teachCourseId)) {
			return false;
		}
		invitationTeachDao.addInvitation(InvitationTeachDO.buildNew(studentId,
				teachCourseId, InviteVideoStatusEnum.WAIT.getKey()));
		String channel = userOnlineInfoService.getChannelByTCID(teachCourseId);
		if(channel==null){
			return false;
		}
		messagePushService.pushMessage(channel, MessagePushEnum.OPEN_VIDEO.getValue());
		return true;
	}

	@Override
	public boolean cancleInvitationByStudent(int studentId) {
		InvitationTeachDO invitationTeachDO = getHandleInvitationByStudent(studentId);
		if (invitationTeachDO == null) {
			return false;
		}
		invitationTeachDO.setStatus(InviteVideoStatusEnum.CUT.getKey());
		invitationTeachDao.updateStatus(invitationTeachDO);
		return true;
	}
	
	@Override
	public boolean resultInvite(int studentId,int status) {
		InvitationTeachDO invitationTeachDO = getHandleInvitationByStudent(studentId);
		if (invitationTeachDO == null) {
			return false;
		}
		//faiu
		if (status==0){
			invitationTeachDO.setStatus(InviteVideoStatusEnum.CUT.getKey());
		}else{
			teachOrderService.createInviteOrder(invitationTeachDO);
			invitationTeachDO.setStatus(InviteVideoStatusEnum.CONN.getKey());
		}		
		invitationTeachDao.updateStatus(invitationTeachDO);
		return true;
	}
	

	@Override
	public boolean cancleInvitationByTeacher(int teacherId) {
		InvitationTeachDO invitationTeachDO = getHandleInvitationByTeacher(teacherId);
		if (invitationTeachDO == null) {
			return false;
		}
		invitationTeachDO.setStatus(InviteVideoStatusEnum.CUT.getKey());
		teachCourseService.updateTCStatus(invitationTeachDO.getTeachCourseId(), teacherId, TeachCourseStatusEnum.NORMAL);
		invitationTeachDao.updateStatus(invitationTeachDO);
		return true;
	}


	@Override
	public VideoAccount getVideoAccount(int teacherId) {
		InvitationTeachDO invitationTeachDO = getHandleInvitationByTeacher(teacherId);
		if (invitationTeachDO == null) {
			return null;
		}
		teachCourseService.updateTCStatus(invitationTeachDO.getTeachCourseId(),teacherId, TeachCourseStatusEnum.BUSY);
		return videoSecureConfig.getFreeOne();
	}

	@Override
	public boolean pushVideoKey(int teacherId, String key) {
		InvitationTeachDO invitationTeachDO = getHandleInvitationByTeacher(teacherId);
		if (invitationTeachDO == null) {
			return false;
		}
		String channelId = userOnlineInfoService.getChannelByTCID(invitationTeachDO.getTeachCourseId());
		messagePushService.pushMessage(channelId, key);
		return true;
	}
	
	
	private InvitationTeachDO getHandleInvitationByStudent(int studentId) {
		return invitationTeachDao.getHandleInvitationByStudent(studentId);
	}

	private InvitationTeachDO getHandleInvitationByTeacher(int teacherId) {
		Integer teachCourseId = teachCourseService
				.getTCIdByTeacherId(teacherId);
		if (teachCourseId == null) {
			return null;
		}
		return invitationTeachDao
				.getHandleInvitationByTeachCourse(teachCourseId);
	}

	

}
