package org.our.cycle.common;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class CreateSessionFormUseWebInput implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request =(HttpServletRequest)dictionary.get("request");
		Map map=request.getParameterMap();
		request.getSession(true).setAttribute("session-form", map);
		return CycleLogic.NEXT;
	}

}