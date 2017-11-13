package com.apcompany.api.model.pojo;

public class InvitationTeachDO {
	
	private int id;
	private int studentId;
	private int teachCourseId;
	
	//0-邀请中 1.老师不在线或忙碌 2. 学生取消 3.老师拒绝了您的邀请 4.老师接受。--接受邀请，会创建innovation订单，并初始化订单为未支付。 
	private int status;
	private String created;
	private String modified;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getTeachCourseId() {
		return teachCourseId;
	}
	public void setTeachCourseId(int teachCourseId) {
		this.teachCourseId = teachCourseId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public static InvitationTeachDO buildNew(int studentId, int teachCourseId,int status) {
		InvitationTeachDO invitationTeachDO= new InvitationTeachDO();
		invitationTeachDO.setStudentId(studentId);
		invitationTeachDO.setTeachCourseId(teachCourseId);
		invitationTeachDO.setStatus(status);
		return invitationTeachDO;
	}
	
	

}
