package com.gsys.model;

import java.io.Serializable;

public class Menus implements Serializable {
	private static final long serialVersionUID = 1L;
	private String menuid;
	private String menuname;
	private String parentid;
	private String url;
	private int seq;
	private String mtype;
	private String icon;

	private String haschild;
	private String checked;

	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getHaschild() {
		return haschild;
	}
	public void setHaschild(String haschild) {
		this.haschild = haschild;
	}
	public String getChecked() {
		if ("0".equals(parentid)) {
			return "";
		}
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
}
