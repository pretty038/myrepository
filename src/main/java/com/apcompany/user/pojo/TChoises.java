package com.apcompany.user.pojo;

import java.sql.Timestamp;

public class TChoises {
	private long id;
	private long questionid;
	private String choise;
	private int status;
	private Timestamp ctime;
	private Timestamp utime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getChoise() {
		return choise;
	}
	public void setChoise(String choise) {
		this.choise = choise;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public long getQuestionid() {
		return questionid;
	}
	public void setQuestionid(long questionid) {
		this.questionid = questionid;
	}
	@Override
	public String toString() {
		return "TChoises [id=" + id + ", questionid=" + questionid + ", choise=" + choise + ", status=" + status
				+ ", ctime=" + ctime + ", utime=" + utime + "]";
	}

}
