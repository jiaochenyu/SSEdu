package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;


public class StringToInteger implements CycleLogic{

	private static final String DI_STRING = "String";
	private static final String DO_INTEGER = "Integer";

	public int invoker(Dictionary dictionary){
		String str=(String)dictionary.get(DI_STRING);
		try{
			dictionary.put(DO_INTEGER, Integer.parseInt(str));
		}catch(Exception e){
			return CycleLogic.ERROR;
		}
		return CycleLogic.NEXT;
	}

}