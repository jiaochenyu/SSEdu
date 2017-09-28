package com.gsys.admin;

import java.util.Dictionary;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.cmit.cycle.core.CycleLogic;
import com.cmit.cycle.core.Utils;
import com.gsys.common.DbExecutor;
import com.gsys.common.StringHelper;
import com.gsys.model.Roles;


public class SaveRoles implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		String uuid = request.getParameter("uuid");
		String rolename = request.getParameter("rolename");
		String rolememo = request.getParameter("rolememo");

		Roles model = new Roles();
		model.setUuid(uuid);
		model.setRolename(rolename);
		model.setRolememo(rolememo);

		boolean result = false;

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			if (StringHelper.isEmpty(uuid) || "undefined".equals(uuid)) {
				uuid = Utils.createUUID();
				model.setUuid(uuid);

				mysession.insert("gsys.insertRoles", model);
				mysession.commit();
				result = true;
			} else {
				mysession.update("gsys.updateRoles", model);
				mysession.commit();
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//mysession.close();
			DbExecutor.close(mysession);
		}

		dictionary.put("result", result);
		return CycleLogic.NEXT;
	}

}