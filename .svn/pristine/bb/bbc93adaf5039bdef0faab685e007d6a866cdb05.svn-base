package org.our.cycle.common;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;


public class CaculatePageStart implements CycleLogic{

	private static final String DI_PAGENO = "PageNo";
	private static final String DI_PAGESIZE = "PageSize";
	private static final String DI_TOTALPAGE = "TotalPage";
	private static final String DO_PAGESTART = "PageStart";

	public int invoker(Dictionary dictionary){
		int pageNo=(Integer)dictionary.get(DI_PAGENO);
		int pagesize=(Integer)dictionary.get(DI_PAGESIZE);
		int pagestart=0;
		int totalpage=(Integer)dictionary.get(DI_TOTALPAGE);
		if(pageNo>totalpage){
			pageNo=totalpage;
		}else if(pageNo<0){
			pageNo=1;
		}
		if(pageNo>1){
			pagestart=(pageNo-1)*pagesize;
		}else{
			pagestart=0;
		}
		dictionary.put(DO_PAGESTART, pagestart);
		return CycleLogic.NEXT;
	}

}