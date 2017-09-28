package com.gsys.admin;

import java.util.Dictionary;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.DbExecutor;
import com.gsys.common.StringHelper;
import com.gsys.model.Users;


public class GetUsers implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		// HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		String uuid = request.getParameter("uuid");
		if (StringHelper.isEmpty(uuid) || "undefined".equals(uuid)) {
			return CycleLogic.NEXT;
		}

		Users users = new Users();

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			users = (Users)mysession.selectOne("gsys.getUsers", uuid);
		} catch (Exception e){
        	e.printStackTrace();
        } finally{
        	//mysession.close();
        	DbExecutor.close(mysession);
        }

        request.setAttribute("bean", users);
		return CycleLogic.NEXT;
	}

}