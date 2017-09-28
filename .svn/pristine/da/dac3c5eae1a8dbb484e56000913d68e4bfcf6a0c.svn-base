package com.gsys.main;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.AccessUtility;
import com.gsys.common.App;
import com.gsys.model.Users;


public class URLCheck implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		String uri = request.getRequestURI();
		boolean isok = false;
		String action = null;
		if (uri != null) {
			int beginIndex = uri.lastIndexOf("/");
			if (beginIndex >= 0) {
				action = uri.substring(beginIndex + 1);
			} else {
				action = uri;
			}
			int endIndex = action.lastIndexOf("?");
			if (endIndex >= 0) {
				action = action.substring(0, endIndex);
			}
			action = action.toLowerCase();
		}
		if (action != null && action.length() > 0) {
			HttpSession session = request.getSession();
			Users user = (Users)session.getAttribute(App.LOGIN_USER);
			if (user != null && "admin".equals(user.getLoginid())) {
				isok = true;
			} else {
				List<String> valueList = null;
				Map<String, List<String>> accessMap = AccessUtility.getInstance().getAccessMap();
				if (accessMap != null) {
					valueList = accessMap.get(action);
				}
				if (valueList != null) {
					@SuppressWarnings("unchecked")
					Map<String, String> authMap = (Map<String, String>)session.getAttribute(App.LOGIN_AUTH_MAP);
					if (authMap != null) {
						for (String value : valueList) {
							if (authMap.containsKey(value)) {
								isok = true;
								break;
							}
						}
					}
				}
			}
			//System.out.println("------------------------------");
			//System.out.println(">>> " + action + " --> " + isok);
			//System.out.println("------------------------------");
		}
		if (!isok) {
			dictionary.put("result", "URL Denied");
			return CycleLogic.ERROR;
		}
		return CycleLogic.NEXT;
	}

}