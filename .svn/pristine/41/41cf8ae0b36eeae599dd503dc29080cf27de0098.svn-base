package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;
import java.util.HashMap;


public class CreateHashMap implements CycleLogic{

	private static final String DO_HASHMAP = "HashMap";

	public int invoker(Dictionary dictionary){
		HashMap map=new HashMap();
		dictionary.put(DO_HASHMAP, map);
		return CycleLogic.NEXT;
	}

}