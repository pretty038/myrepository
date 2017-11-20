package com.apcompany.api.model.pojo;

import java.io.Serializable;

public class OnlineTeachCourseDetailInfoDO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5730347846885813508L;
	private int teacherId;
	private String teacherName;
	private String photo;
	
	
	private float teachScore;
	private int moneyPerMinute;
	private int teachCourseStatus;
	
	private double lat;
	private double lng;
	private int teacherStatus;
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public float getTeachScore() {
		return teachScore;
	}
	public void setTeachScore(float teachScore) {
		this.teachScore = teachScore;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getMoneyPerMinute() {
		return moneyPerMinute;
	}
	public void setMoneyPerMinute(int moneyPerMinute) {
		this.moneyPerMinute = moneyPerMinute;
	}
	public int getTeachCourseStatus() {
		return teachCourseStatus;
	}
	public void setTeachCourseStatus(int teachCourseStatus) {
		this.teachCourseStatus = teachCourseStatus;
	}
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public int getTeacherStatus() {
		return teacherStatus;
	}
	public void setTeacherStatus(int teacherStatus) {
		this.teacherStatus = teacherStatus;
	}
	
	
	

}