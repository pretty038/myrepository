package com.apcompany.user.pojo;

import java.sql.Timestamp;

public class Labels_Question_Rel {
	
	private long id;
	private int status;
	private int labelid;
	private int questionid;
	private Timestamp ctime;
	private Timestamp utime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	

}
