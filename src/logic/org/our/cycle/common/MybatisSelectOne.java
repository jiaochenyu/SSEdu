package org.our.cycle.common;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;

import org.apache.ibatis.session.SqlSession;


public class MybatisSelectOne implements CycleLogic{

	private static final String DI_MAPID = "MapID";
	private static final String DO_RESULTOBJECT = "ResultObject";

	public int invoker(Dictionary dictionary){
		SqlSession session = Utils.getSqlSessionFactory().openSession(); 
		String mapid=(String)dictionary.get(DI_MAPID);
		Object outObj=null;
		try{
			outObj=session.selectOne(mapid);
		}catch(Exception e){   
			
        }finally{   
            session.close();   
        }
		if(outObj!=null){
			dictionary.put(DO_RESULTOBJECT, outObj);
		}else{
			return CycleLogic.ERROR;
		}
		return CycleLogic.NEXT;
	}

}