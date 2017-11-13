package com.apcompany.api.service;

import com.apcompany.api.model.form.OrderTeacherScoreForm;
import com.apcompany.api.model.pojo.BookTimeTeachDO;
import com.apcompany.api.model.pojo.InvitationTeachDO;
import com.apcompany.api.model.pojo.TeachOrderDO;

public interface ITeachOrderService {
	
	TeachOrderDO getHandleInviteOrderByTeacher(int teacherId);
	
	TeachOrderDO getHandleInviteOrderByStudent(int studentId);
	
	boolean createInviteOrder(InvitationTeachDO invitationTeachDO);
	
	TeachOrderDO commitInviteOrderByStudent(int studentId,int orderId);
	
	boolean markScoreForOrder(OrderTeacherScoreForm form);
	
	TeachOrderDO commitInviteOrderByTeacher(int teacherId,int orderId);
	
	TeachOrderDO getById(int id);
	
	//book order
	TeachOrderDO createBookOrder(BookTimeTeachDO bookTimeTeachDO);
	
	TeachOrderDO cancelBookOrder(int bookId);

}
