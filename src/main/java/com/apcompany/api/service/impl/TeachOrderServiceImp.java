package com.apcompany.api.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.api.dao.ITeachOrderDao;
import com.apcompany.api.dao.TeacherDao;
import com.apcompany.api.model.form.OrderTeacherScoreForm;
import com.apcompany.api.model.pojo.BookDayTeachDO;
import com.apcompany.api.model.pojo.BookTimeTeachDO;
import com.apcompany.api.model.pojo.InvitationTeachDO;
import com.apcompany.api.model.pojo.TeachCourseDO;
import com.apcompany.api.model.pojo.TeachOrderDO;
import com.apcompany.api.service.IBookTeachService;
import com.apcompany.api.service.ITeachOrderService;
import com.apcompany.api.service.ITeachCourseService;
import com.apcompany.api.service.IUserOnlineInfoService;
import com.apcompany.api.util.DateUtil;

@Service
public class TeachOrderServiceImp implements ITeachOrderService{
	
	@Autowired private ITeachOrderDao teachOrderDao;
	@Autowired private TeacherDao teacherDao;
	
	@Autowired private ITeachCourseService teachCourseService;
	
	@Autowired private IBookTeachService bookService;
	@Autowired private IUserOnlineInfoService userOnlineInfoService;

	
	@Override
	public boolean createInviteOrder(InvitationTeachDO invitationTeachDO){		
		if(invitationTeachDO==null){
			return false;
		}
		if(checkTeacherHasHandleInviteOrder(invitationTeachDO.getTeachCourseId())|| 
				!userOnlineInfoService.checkTeacherOnlineAndNotBusy(invitationTeachDO.getTeachCourseId())){
			return false;
		}
		TeachOrderDO teachOrderDO=new TeachOrderDO();
		teachOrderDO.setTeachCourseId(invitationTeachDO.getTeachCourseId());
		teachOrderDO.setStudentId(invitationTeachDO.getStudentId());
		teachOrderDO.setType(0);
		teachOrderDO.setSrcId(invitationTeachDO.getId());
		teachOrderDO.setStatus(0);
		teachOrderDO.setBeginTime(DateUtil.formateDateToYMDHMS(new Date()));
		teachOrderDao.addTeachOrder(teachOrderDO);
		int tid= teachCourseService.getTIdByTeachCourseId(invitationTeachDO.getTeachCourseId());
		userOnlineInfoService.updateTeacherStatus(tid, 3);
		return true;
	}

	@Override
	public TeachOrderDO commitInviteOrderByStudent(int studentId, int orderId) {
		TeachOrderDO teachOrderDO = getById(orderId);
		if(teachOrderDO == null || teachOrderDO.getStudentId()!=studentId||teachOrderDO.getStatus()!=0){
			return null;
		}
		TeachCourseDO teachCourseDO = teachCourseService.getTCById(teachOrderDO.getTeachCourseId());
		Date startTime = DateUtil.parseDateFromYMDHM(teachOrderDO.getBeginTime());
		Date endTime = new Date();
		int useMinute = DateUtil.compareMinuteBetweenDate(endTime,startTime );
		teachOrderDO.setEndTime(DateUtil.formateDateToYMDHM(endTime));
		teachOrderDO.setUseMinute(useMinute);
		teachOrderDO.setMoney(useMinute*teachCourseDO.getMoneyPerMinute());
		teachOrderDO.setStatus(1);
		teachOrderDao.commitOrder(teachOrderDO);
		int tid= teachCourseService.getTIdByTeachCourseId(teachCourseDO.getId());
		userOnlineInfoService.updateTeacherStatus(tid, 1);
		return teachOrderDO;
	}
	
	@Override
	public boolean markScoreForOrder(OrderTeacherScoreForm form) {
		if ( form ==null){
			return false;
		}
		TeachOrderDO order = teachOrderDao.getOrderById(form.getOrderId());
		//如果已经评论过 或者无此订单 ，返回false
		if( order == null || !form.getStudentId().equals(form.getStudentId())
				|| order.getStatus() !=4){
			return false;
		}
		order.setTeacherCustomerScore(form.getTeacherCustomerScore());
		order.setTeacherMannerScore(form.getTeacherMannerScore());
		order.setTeacherSkillScore(form.getTeacherSkillScore());
		teachOrderDao.markOrder(order);
		//更新该教师授课的平均评分
		float avgScore = (form.getTeacherCustomerScore()+form.getTeacherMannerScore()
				+form.getTeacherSkillScore())/3;
		teachCourseService.updateScoreOfTeachCourse(order.getTeachCourseId(), avgScore);
		return true;
	}

	@Override
	public TeachOrderDO getById(int id) {
		return teachOrderDao.getOrderById(id);
	}

	
	private boolean checkTeacherHasHandleInviteOrder(int teachCourseId) {
		return teachOrderDao.getHandleInviteOrderByTeachCourse(teachCourseId)==null?false:true;
	}

	@Override
	public TeachOrderDO commitInviteOrderByTeacher(int teacherId, int orderId) {
		TeachCourseDO teachCourseDO= teachCourseService.getTCById(teachCourseService.getTCIdByTeacherId(teacherId));
		if(teachCourseDO==null){
			return null;
		}
		TeachOrderDO teachOrderDO = getById(orderId);
		if(teachOrderDO == null || teachOrderDO.getType()!=0 || teachOrderDO.getStatus()!=0||
				teachOrderDO.getTeachCourseId()!=teachCourseDO.getId()){
			return null;
		}
		Date startTime = DateUtil.parseDateFromYMDHM(teachOrderDO.getBeginTime());
		Date endTime = new Date();
		int useMinute = DateUtil.compareMinuteBetweenDate(endTime,startTime );
		teachOrderDO.setEndTime(DateUtil.formateDateToYMDHM(endTime));
		teachOrderDO.setUseMinute(useMinute);
		teachOrderDO.setMoney(useMinute*teachCourseDO.getMoneyPerMinute());
		teachOrderDO.setStatus(1);
		teachOrderDao.commitOrder(teachOrderDO);
		userOnlineInfoService.updateTeacherStatus(teacherId, 1);
		return teachOrderDO;
	}

	@Override
	public TeachOrderDO getHandleInviteOrderByTeacher(int teacherId) {
		Integer teachCourseId = teachCourseService.getTCIdByTeacherId(teacherId);
		if(teachCourseId==null||teachCourseId<=0){
			return null;
		}
		return teachOrderDao.getHandleInviteOrderByTeachCourse(teachCourseId);		
	}

	@Override
	public TeachOrderDO getHandleInviteOrderByStudent(int studentId) {
		return teachOrderDao.getHandleInviteOrderByStudent(studentId);
	}

	@Override
	public TeachOrderDO createBookOrder(BookTimeTeachDO bookTimeTeachDO) {		
		if(bookTimeTeachDO == null){
			return null;
		}
		BookDayTeachDO bookDayTeachDO= bookService.getBookDayById(bookTimeTeachDO.getBookDayId());
		if(bookDayTeachDO==null){
			return null;
		}
		TeachCourseDO teachCourseDO= teachCourseService.getTCById(bookDayTeachDO.getTeachCourseId());
		if(teachCourseDO== null || teachCourseDO.getStatus()==0){
			return null;
		}
		TeachOrderDO teachOrderDO = new TeachOrderDO();
		teachOrderDO.setStudentId(bookTimeTeachDO.getStudentId());
		teachOrderDO.setTeachCourseId(teachCourseDO.getId());
		teachOrderDO.setType(1);
		teachOrderDO.setSrcId(bookTimeTeachDO.getId());
		teachOrderDO.setStatus(1);
		Date beginTime = DateUtil.createDate(bookDayTeachDO.getBookDay(), bookTimeTeachDO.getStartHour());
		Date endTime= DateUtil.createDate(bookDayTeachDO.getBookDay(), bookTimeTeachDO.getEndHour());
		teachOrderDO.setBeginTime(DateUtil.formateDateToYMDHMS(beginTime));
		teachOrderDO.setEndTime(DateUtil.formateDateToYMDHMS(endTime));
		teachOrderDO.setUseMinute((bookTimeTeachDO.getEndHour()-bookTimeTeachDO.getStartHour())*60);
		teachOrderDO.setMoney(teachOrderDO.getUseMinute()*teachCourseDO.getMoneyPerMinute());
		teachOrderDao.addTeachOrder(teachOrderDO);
		return teachOrderDO;
	}

	@Override
	public TeachOrderDO cancelBookOrder(int bookId) {
		TeachOrderDO teachOrderDO = teachOrderDao.getByBookId(bookId);
		if(teachOrderDO== null){
			return null;
		}
		if(teachOrderDO.getStatus()==2){
			// 计算退款费用
		   teachOrderDO.setStatus(4);
		}
		else if(teachOrderDO.getStatus()==1){
			teachOrderDO.setStatus(5);
		}
		teachOrderDao.updateOrderStatus(teachOrderDO);
		return teachOrderDO;
	}
	

}
