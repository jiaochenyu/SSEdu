package com.gsys.admin;

import java.util.Dictionary;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.cmit.cycle.core.CycleLogic;
import com.cmit.cycle.core.Utils;
import com.gsys.common.DbExecutor;
import com.gsys.common.StringHelper;
import com.gsys.model.RoleUsers;


public class SaveRoleUsers implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		String roleid = request.getParameter("roleid");
		String userid = request.getParameter("userid");

		boolean result = false;
		if (StringHelper.isEmpty(roleid) || StringHelper.isEmpty(userid)) {
			dictionary.put("result", result);
			return CycleLogic.NEXT;
		}

		RoleUsers model = new RoleUsers();
		model.setRoleid(roleid);
		model.setUserid(userid);

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			String uuid = Utils.createUUID();
			model.setUuid(uuid);

			mysession.delete("gsys.deleteRoleUsersBySelf", model);
			mysession.insert("gsys.insertRoleUsers", model);
			mysession.commit();
			result = true;
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