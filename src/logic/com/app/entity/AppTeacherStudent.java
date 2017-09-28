package com.app.entity;

import java.io.Serializable;

public class AppTeacherStudent implements Serializable {

	private static final long serialVersionUID = 1L;
	private String uuid;
	private String tid;
	private String sid;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
}
