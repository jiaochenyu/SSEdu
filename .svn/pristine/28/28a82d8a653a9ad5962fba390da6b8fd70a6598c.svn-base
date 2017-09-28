package org.our.cycle.common;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;

import org.apache.ibatis.session.SqlSession;


public class MybatisDeleteWithParameters implements CycleLogic{

	private static final String DI_MAPID = "MapID";
	private static final String DI_PARAMETER = "Parameter";

	public int invoker(Dictionary dictionary){
		SqlSession session = Utils.getSqlSessionFactory().openSession(); 
		String mapid=(String)dictionary.get(DI_MAPID);
		Object args=dictionary.get(DI_PARAMETER);
		int result=-9;
		try{
			result=session.delete(mapid,args);
			session.commit();
		}catch(Exception e){   
			
        }finally{   
            session.close();   
        }
		if(result!=-9){
			return CycleLogic.NEXT;
		}else{
			return CycleLogic.ERROR;
		}
	}

}