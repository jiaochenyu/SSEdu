package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;


public class AddToHashMap implements CycleLogic{

	private static final String DI_KEY = "Key";
	private static final String DI_HASHMAP = "HashMap";
	private static final String DI_ITEM = "Item";

	public int invoker(Dictionary dictionary){
		Map map=(Map)dictionary.get(DI_HASHMAP);
		String key=(String)dictionary.get(DI_KEY);
		map.put(key, dictionary.get(DI_ITEM));
		return CycleLogic.NEXT;
	}

}