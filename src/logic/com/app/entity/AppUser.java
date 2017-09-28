package com.app.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class AppUser implements Serializable {

	private static final long serialVersionUID = 1L;
	private String uuid;
	private String username;
	private String password;
	private String nickname;
	private String part;
	private String email;
	private String sex;
	private String schoolid;
	private String schoolname;
	private String courseid;
	private String coursename;
	private String state;
	private int integral;
	private Timestamp createtime;
	private int vip;
	private int vipintegral;
	private String vipstate;
	private Timestamp vipendtime;
	private String realname;
	private String imgpath;
	
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSchoolid() {
		return schoolid;
	}
	public void setSchoolid(String schoolid) {
		this.schoolid = schoolid;
	}
	public String getSchoolname() {
		return schoolname;
	}
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	public String getCourseid() {
		return courseid;
	}
	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
	}
	public int getVipintegral() {
		return vipintegral;
	}
	public void setVipintegral(int vipintegral) {
		this.vipintegral = vipintegral;
	}
	public String getVipstate() {
		return vipstate;
	}
	public void setVipstate(String vipstate) {
		this.vipstate = vipstate;
	}
	public Timestamp getVipendtime() {
		return vipendtime;
	}
	public void setVipendtime(Timestamp vipendtime) {
		this.vipendtime = vipendtime;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
}
