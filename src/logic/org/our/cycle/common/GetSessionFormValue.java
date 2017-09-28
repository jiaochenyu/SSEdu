package org.our.cycle.common;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class GetSessionFormValue implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request =(HttpServletRequest)dictionary.get("request");
		Map map=(Map)request.getSession(true).getAttribute("session-form");
		if(map!=null){
			Iterator keyValuePairs =map.entrySet().iterator();
			while(keyValuePairs.hasNext()){
				Map.Entry entry =(Map.Entry)keyValuePairs.next();
				String name=(String)entry.getKey();
				String[] value=(String[])entry.getValue();
				if(value.length==1){
					dictionary.put(name, value[0]);
				}else{
					dictionary.put(name, value);
				}
			}
		}
		return CycleLogic.NEXT;
	}

}