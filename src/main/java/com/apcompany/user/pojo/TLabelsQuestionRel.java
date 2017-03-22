package com.apcompany.user.pojo;

import java.sql.Timestamp;

public class TLabelsQuestionRel {
	
	private int id;
	private int status;
	private int labelid;
	private int questionid;
	private Timestamp ctime;
	private Timestamp utime;
	private int labelsrelid;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getLabelid() {
		return labelid;
	}
	public void setLabelid(int labelid) {
		this.labelid = labelid;
	}
	public int getQuestionid() {
		return questionid;
	}
	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}
	public Timestamp getCtime() {
		return ctime;
	}
	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
	public Timestamp getUtime() {
		return utime;
	}
	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLabelsrelid() {
		return labelsrelid;
	}
	public void setLabelsrelid(int labelsrelid) {
		this.labelsrelid = labelsrelid;
	}
	

}
