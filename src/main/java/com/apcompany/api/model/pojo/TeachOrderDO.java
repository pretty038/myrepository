package com.apcompany.api.model.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TeachOrderDO {
	
	private Integer id;
	private Integer teachCourseId;	
	private Integer studentId;
	private String beginTime;
	private String endTime;
	//secound type
	private int useMinute;
	private int money;
	//0 invite 1. book 
	private int type;
	private int srcId;
	//状态 0.inite进行中， 1.支付中 2.支付成功 3.已经评价 4.已退款。5. 已取消（book类型，未支付）
	private Integer status;
	private Float teacherCustomerScore;
	private Float teacherMannerScore;
	private Float teacherSkillScore;
	private String created;
	private String modified;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	public int getUseMinute() {
		return this.useMinute;
	}
	public void setUseMinute(int useMinute) {
		this.useMinute = useMinute;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public Float getTeacherCustomerScore() {
		return teacherCustomerScore;
	}
	public void setTeacherCustomerScore(Float teacherCustomerScore) {
		this.teacherCustomerScore = teacherCustomerScore;
	}
	public Float getTeacherMannerScore() {
		return teacherMannerScore;
	}
	public void setTeacherMannerScore(Float teacherMannerScore) {
		this.teacherMannerScore = teacherMannerScore;
	}
	public Float getTeacherSkillScore() {
		return teacherSkillScore;
	}
	public void setTeacherSkillScore(Float teacherSkillScore) {
		this.teacherSkillScore = teacherSkillScore;
	}
	
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getTeachCourseId() {
		return teachCourseId;
	}
	public void setTeachCourseId(Integer teachCourseId) {
		this.teachCourseId = teachCourseId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSrcId() {
		return srcId;
	}
	public void setSrcId(int srcId) {
		this.srcId = srcId;
	}
	
	public static void main(String[] args) {
		ObjectMapper mapper= new ObjectMapper();
		try {
			String str = mapper.writeValueAsString(new TeachOrderDO());
			System.out.println(str);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}	
