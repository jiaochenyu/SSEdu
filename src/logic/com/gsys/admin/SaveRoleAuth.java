package com.gsys.admin;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.cmit.cycle.core.CycleLogic;
import com.cmit.cycle.core.Utils;
import com.gsys.common.DbExecutor;
import com.gsys.common.StringHelper;
import com.gsys.model.RoleAuth;


public class SaveRoleAuth implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		String roleid = request.getParameter("roleid");
		String type = request.getParameter("type");
		String[] authidArr = request.getParameterValues("authid");

		boolean result = false;
		if (StringHelper.isEmpty(roleid) || StringHelper.isEmpty(type)) {
			dictionary.put("result", result);
			return CycleLogic.NEXT;
		}

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			Map map = new HashMap();
			map.put("roleid", roleid);
			map.put("type", type);
			mysession.delete("gsys.deleteRoleAuthByMap", map);
			if (StringHelper.isNotEmpty(authidArr)) {
				for (String authid : authidArr) {
					RoleAuth model = new RoleAuth();
					String uuid = Utils.createUUID();
					model.setUuid(uuid);
					model.setRoleid(roleid);
					model.setType(type);
					model.setAuthid(authid);

					mysession.insert("gsys.insertRoleAuth", model);
				}
			}
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