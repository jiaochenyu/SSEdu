package com.gsys.common;

import java.util.Map;

import javax.servlet.http.HttpSession;

public class BaseAuthUtils extends AuthUtils {
	private HttpSession session = null;
	private boolean admin = false;

	public BaseAuthUtils(HttpSession session) {
		this.session = session;
		if ("admin".equals(session.getAttribute(App.LOGIN_USER_ID))) {
			this.admin = true;
		}
	}

	public BaseAuthUtils(boolean admin) {
		this.admin = admin;
	}

	@Override
	public boolean display(String viewid) {
		if (admin) {
			return true;
		}
		if (viewid == null || session == null) {
			return false;
		}
		@SuppressWarnings("unchecked")
		Map<String, String> authMap = (Map<String, String>)session.getAttribute(App.LOGIN_AUTH_MAP);
		if (authMap != null) {
			return authMap.containsKey(viewid);
		}
		return false;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
