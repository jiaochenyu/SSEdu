package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;
import com.cmit.cycle.core.Utils;

import java.util.Date;
import java.util.Dictionary;


public class ShowToday implements CycleLogic{

	private static final String DO_TODAYSTR = "todaystr";

	public int invoker(Dictionary dictionary){
		String day=Utils.formatDate(false, new Date());
		dictionary.put(DO_TODAYSTR, day);
		return CycleLogic.NEXT;
	}

}