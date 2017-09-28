package org.our.cycle.common;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class PutItemIntoSession implements CycleLogic{

	private static final String DI_KEY = "Key";
	private static final String DI_VALUE = "Value";

	public int invoker(Dictionary dictionary){
		Object value=dictionary.get(DI_VALUE);
		String key=(String)dictionary.get(DI_KEY);
		HttpServletRequest request =(HttpServletRequest)dictionary.get("request");
		request.getSession(true).setAttribute(key, value);
		return CycleLogic.NEXT;
	}

}