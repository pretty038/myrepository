package com.apcompany.api.service;

import java.util.List;

import com.apcompany.api.model.form.OnlineTCForm;
import com.apcompany.api.model.pojo.OnlineTCInfoDO;
import com.apcompany.api.model.pojo.StudentSaveDO;

public interface IStudentTCService {
	// ******************student start****************
	List<OnlineTCInfoDO> getOnlineTCListByCourse(OnlineTCForm onlineTCForm);

	boolean saveTeachCourse(int studentId, int teachCoueseId);

	List<StudentSaveDO> getSavaTCList(int studentId, int index,
			int pageSize);

	boolean deleteById(int id);
	// ******************//******************common end****************
	// end****************

}
