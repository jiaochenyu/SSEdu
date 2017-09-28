package org.our.cycle.common;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


public class CreateSessionForm implements CycleLogic{

	private static final String DI_MAP = "Map";

	public int invoker(Dictionary dictionary){
		HttpServletRequest request =(HttpServletRequest)dictionary.get("request");
		request.getSession(true).setAttribute("session-form", dictionary.get(DI_MAP));
		return CycleLogic.NEXT;
	}

}