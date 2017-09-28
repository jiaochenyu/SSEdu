package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;
import java.util.List;


public class GetListItemValue implements CycleLogic{

	private static final String DI_LISTOBJECT = "ListObject";
	private static final String DI_SEQNO = "SeqNo";
	private static final String DO_VALUEOUTOBJECT = "ValueOutObject";

	public int invoker(Dictionary dictionary){
		List obj=(List)dictionary.get(DI_LISTOBJECT);
		String seqNo=(String)dictionary.get(DI_SEQNO);
		if(seqNo!=null&&obj!=null&&obj.size()>Integer.parseInt(seqNo)){
			dictionary.put(DO_VALUEOUTOBJECT, obj.get(Integer.parseInt(seqNo)));
		}
		return CycleLogic.NEXT;
	}

}