package com.app.appinterface;

import com.app.entity.AppSchool;
import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.DbExecutor;

import java.util.Dictionary;
import java.util.List;

import org.apache.ibatis.session.SqlSession;


public class LoadAppSchool implements CycleLogic{


	public int invoker(Dictionary dictionary){
		SqlSession mysession = null;
		List<AppSchool> list = null;
		try{
			mysession = DbExecutor.open();
			
			list = mysession.selectList("gsys.listAppSchool");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbExecutor.close(mysession);
		}
		dictionary.put("list",list);
		return CycleLogic.NEXT;
	}

}