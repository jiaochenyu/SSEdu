package org.our.cycle.common;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;

import javax.servlet.http.HttpServletRequest;


public class RemoveSession implements CycleLogic{

	private static final String DI_KEY = "Key";

	public int invoker(Dictionary dictionary){
		String key=(String)dictionary.get(DI_KEY);
		HttpServletRequest request =(HttpServletRequest)dictionary.get("request");
		request.getSession(true).removeAttribute(key);
		return CycleLogic.NEXT;
	}

}