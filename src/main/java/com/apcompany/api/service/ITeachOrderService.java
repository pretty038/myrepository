package com.apcompany.api.service;

import com.apcompany.api.model.form.OrderTeacherScoreForm;
import com.apcompany.api.model.pojo.BookTimeTeachDO;
import com.apcompany.api.model.pojo.InvitationTeachDO;
import com.apcompany.api.model.pojo.TeachOrderDO;

public interface ITeachOrderService {
	
	TeachOrderDO createTeachOrder(InvitationTeachDO invitationTeachDO);
	
	boolean markScoreForOrder(int studentId,OrderTeacherScoreForm form);
	
	TeachOrderDO getById(int id);
	
	//book order
	TeachOrderDO createBookOrder(BookTimeTeachDO bookTimeTeachDO);
	
	TeachOrderDO cancelBookOrder(int bookId);

}
