package com.gsys.admin;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.DbExecutor;
import com.gsys.common.StringHelper;
import com.gsys.model.AuthUrl;


public class SaveAuthUrl implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		String type = request.getParameter("type");
		String[] authidArr = request.getParameterValues("authid");
		String[] accurlArr = request.getParameterValues("accurl");

		boolean result = false;
		if (StringHelper.isEmpty(type)) {
			dictionary.put("result", result);
			return CycleLogic.NEXT;
		}

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			Map map = new HashMap();
			map.put("type", type);
			mysession.delete("gsys.deleteAuthUrlByMap", map);
			if (StringHelper.equalsLength(authidArr, accurlArr)) {
				int length = authidArr.length;
				for (int i = 0; i < length; i++) {
					String authid = authidArr[i];
					String accurl = accurlArr[i];

					AuthUrl model = new AuthUrl();
					model.setAuthid(authid);
					model.setAccurl(accurl);
					model.setType(type);

					mysession.insert("gsys.insertAuthUrl", model);
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