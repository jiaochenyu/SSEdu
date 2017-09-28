package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;


public class Array2ArrayList implements CycleLogic{

	private static final String DI_ARRAY = "Array";
	private static final String DO_ARRAYLIST = "ArrayList";

	public int invoker(Dictionary dictionary){
		Object obj=dictionary.get(DI_ARRAY);
		List al=null;
		if(obj!=null){
			if(obj instanceof Object[]){
				Object _obj[]=(Object[])obj;
				al=Arrays.asList(_obj);
			}else{
				al=new ArrayList();
				al.add(obj);
			}
		}else{
			al=new ArrayList();
		}
		dictionary.put(DO_ARRAYLIST, al);
		return CycleLogic.NEXT;
	}

}