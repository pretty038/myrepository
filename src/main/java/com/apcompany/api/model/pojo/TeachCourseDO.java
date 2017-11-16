package com.apcompany.api.model.pojo;

import java.io.Serializable;
import com.apcompany.api.constrant.TeachCourseStatusEnum;

public class TeachCourseDO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 891545544059941592L;
	private int id;
	private int teacherId;
	private int courseId;
	private String created;	
	private String modified;
	/*
	 * 正常：1 ，取消：0 
	 */
	private int status;	
	private int startTeachDay;
	private int teachStudentNum;
	private float teachScore;
	private int moneyPerMinute;
	
	public TeachCourseDO(){
		
	}
	
	
	public static TeachCourseDO createNew(int teacherId, int courseId,int moneyPerMinute){
		TeachCourseDO teachCourseDO= new TeachCourseDO();
		teachCourseDO.setTeacherId(teacherId);
		teachCourseDO.setCourseId(courseId);
		teachCourseDO.setMoneyPerMinute(moneyPerMinute);
		teachCourseDO.setStatus(TeachCourseStatusEnum.NORMAL.getKey());
		return teachCourseDO;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}


	public int getCourseId() {
		return courseId;
	}


	public void setCourseId(int courseId) {
		this.courseId = courseId;
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


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getStartTeachDay() {
		return startTeachDay;
	}


	public void setStartTeachDay(int startTeachDay) {
		this.startTeachDay = startTeachDay;
	}


	public int getTeachStudentNum() {
		return teachStudentNum;
	}


	public void setTeachStudentNum(int teachStudentNum) {
		this.teachStudentNum = teachStudentNum;
	}


	public float getTeachScore() {
		return teachScore;
	}


	public void setTeachScore(float teachScore) {
		this.teachScore = teachScore;
	}


	public int getMoneyPerMinute() {
		return moneyPerMinute;
	}


	public void setMoneyPerMinute(int moneyPerMinute) {
		this.moneyPerMinute = moneyPerMinute;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
