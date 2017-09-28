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
import com.gsys.model.Menus;


public class ListMenus implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		String roleid = request.getParameter("roleid");

		List<Menus> menuList = null;

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			Map map = new HashMap();
			map.put("type", "m");
			if (StringHelper.isNotEmpty(roleid)) {
				map.put("roleid", roleid);
			}
			menuList = mysession.selectList("gsys.listPowerMenus", map);
			/*if (menuList != null) {
				for (Menus menus : menuList) {
					if ("0".equals(menus.getParentid())) {
						menus.setChecked("");
					}
				}
			}*/
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