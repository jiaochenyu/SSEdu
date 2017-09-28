package com.gsys.main;

import java.util.Dictionary;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.DbExecutor;


public class LoadUrlData implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		String menuid = request.getParameter("menuid");

		if (menuid == null) {
			dictionary.put("result", "");
			return CycleLogic.NEXT;
		}

		String url = null;

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			if (menuid != null && menuid.length() > 0) {
				url = (String) mysession.selectOne("gsys.getMenuUrlByMenuid", menuid);
			}
		} catch (Exception e) {
			url = null;
			e.printStackTrace();
		} finally {
			//mysession.close();
			DbExecutor.close(mysession);
		}

		if (url == null) {
			url = "";
		}
		dictionary.put("result", url);
		return CycleLogic.NEXT;
	}

}