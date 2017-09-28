package org.our.cycle.common;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;


public class CaculateTotalPage implements CycleLogic{

	private static final String DI_TOTALCOUNT = "TotalCount";
	private static final String DI_PAGESIZE = "PageSize";
	private static final String DO_TOTALPAGE = "TotalPage";

	public int invoker(Dictionary dictionary){
		int totalcount=0;
		int totalpage=1;
		int pagesize=12;
		totalcount=(Integer)dictionary.get(DI_TOTALCOUNT);
		pagesize=(Integer)dictionary.get(DI_PAGESIZE);
		totalpage=totalcount/pagesize;
		if(totalpage<1){
			totalpage=1;
		}else{
			if((totalcount%pagesize)!=0){
				totalpage=totalcount/pagesize+1;
			}else{
				totalpage=totalcount/pagesize;
			}
		}
		dictionary.put(DO_TOTALPAGE, totalpage);
		return CycleLogic.NEXT;
	}

}