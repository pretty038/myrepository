package com.apcompany.api.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apcompany.api.dao.IInvitationTeachDao;
import com.apcompany.api.model.pojo.InvitationTeachDO;
import com.apcompany.api.service.IBookTeachService;
import com.apcompany.api.service.IInvitationService;
import com.apcompany.api.service.ITeachCourseService;
import com.apcompany.api.service.ITeachOrderService;
import com.apcompany.api.service.IUserOnlineInfoService;

@Service
public class InvitationServiceImp implements IInvitationService {

	@Autowired
	private IInvitationTeachDao invitationTeachDao;
	@Autowired
	private ITeachOrderService teachOrderService;
	@Autowired
	private ITeachCourseService teachCourseService;
	@Autowired
	private IBookTeachService bookService;
	@Autowired
	private IUserOnlineInfoService userOnlineInfoService;

	@Override
	public boolean createInvitation(int studentId, int teachCourseId) {
		// check 当前是否已经有在进行中的邀请。
		InvitationTeachDO invitationTeachDO = getHandleInvitationByStudent(studentId);
		if (invitationTeachDO != null) {
			return false;
		}
		// check teachCourseId is exit;
		if (! teachCourseService.checkTeachCourseIsValid(teachCourseId)) {
			System.out.println(" 该课程不存在，或被关闭");
			return false;
		}
		// 检查该老师是否正在被预约中。例如老师为同意邀请，但是也未处理其他用户的邀请信息。
		invitationTeachDO = invitationTeachDao
				.getHandleInvitationByTeachCourse(teachCourseId);
		if (invitationTeachDO != null) {
			return false;
		}
		// check teacher is offline or busy
		if (!userOnlineInfoService.checkTeacherOnlineAndNotBusy(teachCourseId)) {
			invitationTeachDao.addInvitation(InvitationTeachDO.buildNew(studentId,
					teachCourseId,1));
			return false;
		}
		invitationTeachDao.addInvitation(InvitationTeachDO.buildNew(studentId,
				teachCourseId,0));
		return true;
	}

	@Override
	public boolean cancleInvitationByStudent(int studentId) {
		InvitationTeachDO invitationTeachDO = getHandleInvitationByStudent(studentId);
		if (invitationTeachDO == null) {
			return false;
		}
		invitationTeachDO.setStatus(2);
		invitationTeachDao.updateStatus(invitationTeachDO);
		return false;
	}

	@Override
	public InvitationTeachDO getHandleInvitationByStudent(int studentId) {
		return invitationTeachDao.getHandleInvitationByStudent(studentId);
	}

	@Override
	public InvitationTeachDO getHandleInvitationByTeacher(int teacherId) {
		Integer teachCourseId = teachCourseService
				.getTCIdByTeacherId(teacherId);
		if (teachCourseId == null) {
			return null;
		}
		return invitationTeachDao
				.getHandleInvitationByTeachCourse(teachCourseId);
	}

	@Override
	public boolean dealWithInvitationByTeacher(int teacherId,int status) {
		InvitationTeachDO invitationTeachDO = getHandleInvitationByTeacher(teacherId);
		if(invitationTeachDO==null){
			return false;
		}
		//swich status to deal with
		switch (status) {
		case 3:
			invitationTeachDO.setStatus(2);	
			break;
		case 4:			
		    if(!teachOrderService.createInviteOrder(invitationTeachDO)){
		    	return false;
		    }
		    invitationTeachDO.setStatus(3);
			break;
		default:
			return false;
		}
		invitationTeachDao.updateStatus(invitationTeachDO);
		return true;
	}
	
	
	

}
