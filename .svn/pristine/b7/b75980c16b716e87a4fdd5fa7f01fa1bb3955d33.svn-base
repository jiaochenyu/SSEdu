package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;


public class TrimString implements CycleLogic{

	private static final String DI_STRING = "String";
	private static final String DO_TRIMEDSTRING = "TrimedString";

	public int invoker(Dictionary dictionary){
		String str=(String)dictionary.get(DI_STRING);
		dictionary.put(DO_TRIMEDSTRING, str.trim());
		return CycleLogic.NEXT;
	}

}