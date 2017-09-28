package com.gsys.admin;

import java.util.Dictionary;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.DbExecutor;
import com.gsys.model.Users;


public class DeleteUsers implements CycleLogic{


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
					Users model = new Users();
					model.setUuid(uuid);
					model.setLocked("1");

					mysession.update("gsys.updateUsersSetLocked", model);
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