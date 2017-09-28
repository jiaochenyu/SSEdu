package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;


public class SpliteString implements CycleLogic{

	private static final String DI_SPLITER = "spliter";
	private static final String DI_STRINGPARAMETER = "StringParameter";
	private static final String DO_STRINGARRAY = "StringArray";

	public int invoker(Dictionary dictionary){
		String spliter=(String)dictionary.get(DI_SPLITER);
		String str=(String)dictionary.get(DI_STRINGPARAMETER);
		if(str!=null){
			dictionary.put(DO_STRINGARRAY, str.split(spliter));
		}
		return CycleLogic.NEXT;
	}

}