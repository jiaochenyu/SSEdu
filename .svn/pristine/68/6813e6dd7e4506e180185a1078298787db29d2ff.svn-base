package com.gsys.main;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.App;
import com.gsys.common.DbExecutor;
import com.gsys.model.Menus;
import com.gsys.model.Users;


public class ListMenus implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute(App.LOGIN_USER);

		String parentid = request.getParameter("parentid");

		List<Menus> menuList = null;
		if (user == null || user.getUuid() == null || parentid == null) {
			menuList = new ArrayList<Menus>();
			dictionary.put("list", menuList);
			return CycleLogic.NEXT;
		}

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			if (user != null && parentid != null && parentid.length() > 0) {
				if ("admin".equals(user.getLoginid())) {
					menuList = mysession.selectList("gsys.listMenusByParentid", parentid);
				} else {
					Map map = new HashMap();
					map.put("parentid", parentid);
					map.put("type", "m");
					map.put("userid", user.getUuid());
					menuList = mysession.selectList("gsys.listLoginUserMenus", map);
				}
			}
			if (menuList != null) {
				for (Menus menus : menuList) {
					Integer count = (Integer) mysession.selectOne("gsys.countMenusByParentid", menus.getMenuid());
					if (count != null && count > 0) {
						menus.setHaschild("1");
					}
				}
			}
		} catch (Exception e) {
			menuList = null;
			e.printStackTrace();
		} finally {
			//mysession.close();
			DbExecutor.close(mysession);
		}

		if (menuList == null) {
			menuList = new ArrayList<Menus>();
		}

		dictionary.put("list", menuList);
		return CycleLogic.NEXT;
	}

}