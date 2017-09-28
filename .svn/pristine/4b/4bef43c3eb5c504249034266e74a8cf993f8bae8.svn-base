package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;


public class GetRequestValue implements CycleLogic{

	private static final String DI_ARGNAME = "ArgName";
	private static final String DO_ARGVALUE = "ArgValue";

	public int invoker(Dictionary dictionary){
		Object key=dictionary.get(DI_ARGNAME);
		dictionary.put(DO_ARGVALUE, dictionary.get(key));
		return CycleLogic.NEXT;
	}

}