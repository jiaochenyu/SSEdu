package com.gsys.main;

import java.util.Dictionary;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.App;


public class SystemLogout implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		HttpSession session = request.getSession();
		session.setAttribute(App.LOGIN_USER, null);
		session.setAttribute(App.LOGIN_USER_ID, null);

		return CycleLogic.NEXT;
	}

}