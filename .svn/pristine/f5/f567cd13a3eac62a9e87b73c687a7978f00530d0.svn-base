package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;
import java.util.Map;


public class GetMapValue implements CycleLogic{

	private static final String DI_MAPOBJECT = "MapObject";
	private static final String DI_MAPKEY = "MapKey";
	private static final String DO_MAPVALUE = "MapValue";

	public int invoker(Dictionary dictionary){
		Map map=(Map)dictionary.get(DI_MAPOBJECT);
		String key=(String)dictionary.get(DI_MAPKEY);
		dictionary.put(DO_MAPVALUE, map.get(key));
		return CycleLogic.NEXT;
	}

}