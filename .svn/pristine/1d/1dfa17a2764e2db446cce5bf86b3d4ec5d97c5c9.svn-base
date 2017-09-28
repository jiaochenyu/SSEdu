package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;


public class IntegerAddition implements CycleLogic{

	private static final String DI_INTEGER1 = "Integer1";
	private static final String DI_INTEGER2 = "Integer2";
	private static final String DO_INTEGER3 = "Integer3";

	public int invoker(Dictionary dictionary){
		Integer result=(Integer)dictionary.get(DI_INTEGER1)+(Integer)dictionary.get(DI_INTEGER2);
		dictionary.put(DO_INTEGER3, result);
		return CycleLogic.NEXT;
	}

}