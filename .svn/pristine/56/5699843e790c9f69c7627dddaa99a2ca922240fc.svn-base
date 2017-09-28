package com.app.appinterface;

import com.app.entity.AppSchool;
import com.app.entity.AppUser;
import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.DbExecutor;

import java.util.Dictionary;
import java.util.List;

import org.apache.ibatis.session.SqlSession;


public class LoadAppTeacher implements CycleLogic{


	public int invoker(Dictionary dictionary){
		SqlSession mysession = null;
		List<AppUser> list = null;
		try{
			mysession = DbExecutor.open();
			
			list = mysession.selectList("gsys.listAppTeacher");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbExecutor.close(mysession);
		}
		dictionary.put("list",list);
		return CycleLogic.NEXT;
	}

}