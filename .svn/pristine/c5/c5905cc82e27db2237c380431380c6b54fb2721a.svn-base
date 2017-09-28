package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;
import java.util.Map;


public class RemoveMapValue implements CycleLogic{

	private static final String DI_MAPKEYNAME = "MapKeyName";
	private static final String DI_MAPOBJECT = "MapObject";

	public int invoker(Dictionary dictionary){
		Map m=(Map)dictionary.get(DI_MAPOBJECT);
		Object key=dictionary.get(DI_MAPKEYNAME);
		m.remove(key);
		return CycleLogic.NEXT;
	}

}