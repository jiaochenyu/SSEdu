package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;


public class ConcatString implements CycleLogic{

	private static final String DI_STRA = "StrA";
	private static final String DI_STRB = "StrB";
	private static final String DO_STRC = "StrC";

	public int invoker(Dictionary dictionary){
		String stra=(String)dictionary.get(DI_STRA);
		String strb=(String)dictionary.get(DI_STRB);
		dictionary.put(DO_STRC, stra+strb);
		return CycleLogic.NEXT;
	}

}