package com.apcompany.api.model.pojo;

public class OnlineTeacherDO {
	
	private int teacherId;
	private String teacherName;
	private String address;
	private String college;
	private String profession;
	private String photo;
	
	private int courseId;
	private float teachSocre;
	private int moneyPerMinu;
	
	
	
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
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public float getTeachSocre() {
		return teachSocre;
	}
	public void setTeachSocre(float teachSocre) {
		this.teachSocre = teachSocre;
	}
	public int getMoneyPerMinu() {
		return moneyPerMinu;
	}
	public void setMoneyPerMinu(int moneyPerMinu) {
		this.moneyPerMinu = moneyPerMinu;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	

}
