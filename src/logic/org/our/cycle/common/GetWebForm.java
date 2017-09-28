package org.our.cycle.common;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class GetWebForm implements CycleLogic{

	private static final String DI_WEBFORMID = "WebFormId";
	private static final String DO_FORMOBJECT = "FormObject";

	public int invoker(Dictionary dictionary){
		String obj=(String)dictionary.get(DI_WEBFORMID);
		HttpServletRequest request =(HttpServletRequest)dictionary.get("request");
		Map map=(Map)request.getSession(true).getAttribute(obj);
		if(map!=null){
			dictionary.put(DO_FORMOBJECT, map);
		}
		return CycleLogic.NEXT;
	}

}