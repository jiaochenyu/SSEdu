package com.gsys.model;

import java.io.Serializable;

public class Buttons implements Serializable {
	private static final long serialVersionUID = 1L;
	private String menuid;
	private String pageid;
	private String buttonid;
	private String buttonname;
	private String seq;

	private String checked;
	private String menuname;
	private String pagename;

	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getPageid() {
		return pageid;
	}
	public void setPageid(String pageid) {
		this.pageid = pageid;
	}
	public String getButtonid() {
		return buttonid;
	}
	public void setButtonid(String buttonid) {
		this.buttonid = buttonid;
	}
	public String getButtonname() {
		return buttonname;
	}
	public void setButtonname(String buttonname) {
		this.buttonname = buttonname;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getPagename() {
		return pagename;
	}
	public void setPagename(String pagename) {
		this.pagename = pagename;
	}
}
