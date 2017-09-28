package org.our.cycle.common;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class CreateWebForm implements CycleLogic{

	private static final String DI_FORMID = "FormId";

	public int invoker(Dictionary dictionary){
		String formid=(String)dictionary.get(DI_FORMID);
		HttpServletRequest request =(HttpServletRequest)dictionary.get("request");
		Map map=request.getParameterMap();
		HashMap newMap=new HashMap();
		if(map!=null){
			Iterator keyValuePairs =map.entrySet().iterator();
			while(keyValuePairs.hasNext()){
				Map.Entry entry =(Map.Entry)keyValuePairs.next();
				String name=(String)entry.getKey();
				String[] value=(String[])entry.getValue();
				if(value.length==1){
					newMap.put(new String(name), new String(value[0]));
				}else{
					newMap.put(name, value.clone());
				}
			}
		}
		request.getSession(true).setAttribute(formid, newMap);
		return CycleLogic.NEXT;
	}

}