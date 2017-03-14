package com.apcompany.user.pojo;

import java.sql.Timestamp;

public class TAnswers {
	private long id;
	private long questionid;
	private int status;
	private String answer;
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
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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
		return "TAnswers [id=" + id + ", questionid=" + questionid + ", status=" + status + ", answer=" + answer
				+ ", ctime=" + ctime + ", utime=" + utime + "]";
	}

}
