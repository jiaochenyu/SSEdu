package com.gsys.main;

import java.util.Dictionary;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.App;
import com.gsys.model.Users;


public class LoginCheck implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");
		HttpSession session = request.getSession();

		Users user = (Users)session.getAttribute(App.LOGIN_USER);
		if (user == null || user.getUuid() == null) {
			return CycleLogic.ERROR;
		}
		request.setAttribute("username", user.getLoginid());
		return CycleLogic.NEXT;
	}

}