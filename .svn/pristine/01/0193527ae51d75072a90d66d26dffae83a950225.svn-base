package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;


public class NullCheck implements CycleLogic{

	private static final String DI_OBJECT = "Object";

	public int invoker(Dictionary dictionary){
		if(dictionary.get(DI_OBJECT)!=null){
			return CycleLogic.NEXT;
		}else{
			return CycleLogic.ERROR;
		}
		
	}

}