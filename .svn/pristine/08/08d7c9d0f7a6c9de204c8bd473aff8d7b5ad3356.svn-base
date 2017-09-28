package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;


public class CheckStringEmpty implements CycleLogic{

	private static final String DI_STRING = "String";

	public int invoker(Dictionary dictionary){
		String str=(String)dictionary.get(DI_STRING);
		if(str!=null&&str.trim().length()>0){
			return CycleLogic.NEXT;
		}else{
			return CycleLogic.ERROR;
		}
	}

}