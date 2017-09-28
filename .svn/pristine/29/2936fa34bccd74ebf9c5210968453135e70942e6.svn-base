package com.gsys.admin;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.DbExecutor;
import com.gsys.common.StringHelper;
import com.gsys.model.Pages;


public class LoadPages implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		String roleid = request.getParameter("roleid");

		List<Pages> pageList = null;

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			Map map = new HashMap();
			map.put("type", "p");
			if (StringHelper.isNotEmpty(roleid)) {
				map.put("roleid", roleid);
			}
			//pageList = mysession.selectList("gsys.listPowerPages", map);
			pageList = mysession.selectList("gsys.listPowerPages2", map);
		} catch (Exception e) {
			pageList = null;
			e.printStackTrace();
		} finally {
			//mysession.close();
			DbExecutor.close(mysession);
		}

		if (pageList == null) {
			pageList = new ArrayList<Pages>();
		}

		request.setAttribute("pageList", pageList);
		//dictionary.put("list", pageList);
		return CycleLogic.NEXT;
	}

}