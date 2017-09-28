package com.gsys.admin;

import java.util.Dictionary;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.DbExecutor;


public class DeleteRoles implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		boolean result = false;
		String[] uuidArr = request.getParameterValues("uuid");
		if (uuidArr == null || uuidArr.length == 0) {
			dictionary.put("result", result);
			return CycleLogic.NEXT;
		}

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			for (String uuid : uuidArr) {
				if (uuid != null) {
					mysession.delete("gsys.deleteRoleAuthByRoleid", uuid);
					mysession.delete("gsys.deleteRoleUsersByRoleid", uuid);
					mysession.delete("gsys.deleteRoles", uuid);
					//mysession.commit();
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