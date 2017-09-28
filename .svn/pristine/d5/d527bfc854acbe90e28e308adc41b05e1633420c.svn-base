package com.gsys.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Users implements Serializable {
	private static final long serialVersionUID = 1L;
	private String uuid;
	private String loginid;
	private String displayname;
	private String email;
	private String password;
	private String type;
	private String locked;
	private Date created;

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	public Date getCreated() {
		if (created != null) {
			return new Timestamp(created.getTime());
		}
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
}
