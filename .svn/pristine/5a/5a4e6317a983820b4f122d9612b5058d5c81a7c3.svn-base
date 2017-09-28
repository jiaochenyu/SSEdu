package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;


public class GetArrayItemValue implements CycleLogic{

	private static final String DI_ARRAYNAME = "ArrayName";
	private static final String DI_SEQNO = "SeqNo";
	private static final String DO_ITEMVALUE = "ItemValue";

	public int invoker(Dictionary dictionary){
		Object obj[]=(Object[])dictionary.get(DI_ARRAYNAME);
		String seqNo=(String)dictionary.get(DI_SEQNO);
		dictionary.put(DO_ITEMVALUE, obj[Integer.parseInt(seqNo)]);
		return CycleLogic.NEXT;
	}

}