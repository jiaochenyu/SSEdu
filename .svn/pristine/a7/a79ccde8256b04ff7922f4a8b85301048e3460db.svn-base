package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;


public class RemoveDictionaryValue implements CycleLogic{

	private static final String DI_DICTIONARYKEYITEM = "DictionaryKeyItem";

	public int invoker(Dictionary dictionary){
		dictionary.remove(dictionary.get(DI_DICTIONARYKEYITEM));
		return CycleLogic.NEXT;
	}

}