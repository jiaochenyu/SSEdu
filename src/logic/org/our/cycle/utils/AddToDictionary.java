package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;


public class AddToDictionary implements CycleLogic{

	private static final String DI_KEY = "Key";
	private static final String DI_VALUE = "Value";

	public int invoker(Dictionary dictionary){
		return CycleLogic.NEXT;
	}

}