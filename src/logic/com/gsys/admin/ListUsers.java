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
import com.gsys.common.PageHelper;
import com.gsys.common.StringHelper;
import com.gsys.model.Users;


public class ListUsers implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		//String loginid = request.getParameter("loginid");
		String text = request.getParameter("text");

		Integer total = 0;
		List<Users> usersList = null;

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			Map map = new HashMap();
			map.put("locked", "0");
			/*if (StringHelper.isNotEmpty(loginid)) {
				map.put("loginid", loginid);
			}*/
			if (StringHelper.isNotEmpty(text)) {
				map.put("text", text);
			}
			PageHelper.getPageMap(request, map);

			total = (Integer) mysession.selectOne("gsys.countUsers", map);
			if (total > 0) {
				usersList = mysession.selectList("gsys.listPagedUsers", map);
			}
		} catch (Exception e) {
			usersList = null;
			e.printStackTrace();
		} finally {
			//mysession.close();
			DbExecutor.close(mysession);
		}

		if (usersList == null) {
			usersList = new ArrayList<Users>();
		}

		dictionary.put("total", total);
		dictionary.put("list", usersList);
		return CycleLogic.NEXT;
	}

}