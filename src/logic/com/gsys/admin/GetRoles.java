package com.gsys.admin;

import java.util.Dictionary;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.DbExecutor;
import com.gsys.common.StringHelper;
import com.gsys.model.Roles;


public class GetRoles implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		// HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		String uuid = request.getParameter("uuid");
		if (StringHelper.isEmpty(uuid) || "undefined".equals(uuid)) {
			String roleid = request.getParameter("roleid");
			if (StringHelper.isEmpty(roleid) || "undefined".equals(roleid)) {
				return CycleLogic.NEXT;
			} else {
				uuid = roleid;
			}
		}

		Roles roles = new Roles();

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			roles = (Roles)mysession.selectOne("gsys.getRoles", uuid);
		} catch (Exception e){
        	e.printStackTrace();
        } finally{
        	//mysession.close();
        	DbExecutor.close(mysession);
        }

        request.setAttribute("bean", roles);
		return CycleLogic.NEXT;
	}

}