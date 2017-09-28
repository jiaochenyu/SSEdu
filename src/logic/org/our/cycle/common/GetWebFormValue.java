package org.our.cycle.common;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class GetWebFormValue implements CycleLogic{

	private static final String DI_WEBFORMID = "webformid";

	public int invoker(Dictionary dictionary){
		String formid=(String)dictionary.get(DI_WEBFORMID);
		HttpServletRequest request =(HttpServletRequest)dictionary.get("request");
		Map map=(Map)request.getSession(true).getAttribute(formid);
		if(map!=null){
			Iterator keyValuePairs =map.entrySet().iterator();
			while(keyValuePairs.hasNext()){
				Map.Entry entry =(Map.Entry)keyValuePairs.next();
				String name=(String)entry.getKey();
				Object vl=entry.getValue(); 
				if( vl instanceof String[]){
					String[] value=(String[])vl;
					if(value.length==1){
						dictionary.put(name, value[0]);
					}else{
						dictionary.put(name, value);
					}
				}else if( vl instanceof String){
					dictionary.put(name, vl);
				}
			}
		}
		return CycleLogic.NEXT;
	}

}