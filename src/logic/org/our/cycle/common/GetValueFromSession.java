package org.our.cycle.common;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;

import javax.servlet.http.HttpServletRequest;


public class GetValueFromSession implements CycleLogic{

	private static final String DI_KEY = "Key";
	private static final String DO_VALUE = "Value";

	public int invoker(Dictionary dictionary){
		String key=(String)dictionary.get(DI_KEY);
		HttpServletRequest request =(HttpServletRequest)dictionary.get("request");
		Object value=request.getSession(true).getAttribute(key);
		if(value!=null){
			dictionary.put(DO_VALUE, value);
		}
		return CycleLogic.NEXT;
	}

}