package com.app.appinterface;

import com.app.entity.AppSchool;
import com.app.entity.AppSection;
import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.DbExecutor;

import java.util.Dictionary;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;


public class LoadAppSection implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		String courseid = request.getParameter("courseid");
		SqlSession mysession = null;
		List<AppSection> list = null;
		try{
			mysession = DbExecutor.open();
			
			list = mysession.selectList("gsys.listAppSection",courseid);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbExecutor.close(mysession);
		}
		dictionary.put("list",list);
		return CycleLogic.NEXT;
	}
}