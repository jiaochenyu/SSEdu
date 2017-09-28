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
import com.gsys.model.Roles;


public class ListRoles implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		String rolename = request.getParameter("rolename");

		Integer total = 0;
		List<Roles> rolesList = null;

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			Map map = new HashMap();
			if (StringHelper.isNotEmpty(rolename)) {
				map.put("rolename", rolename);
			}
			PageHelper.getPageMap(request, map);

			total = (Integer) mysession.selectOne("gsys.countRoles", map);
			if (total > 0) {
				rolesList = mysession.selectList("gsys.listPagedRoles", map);
			}
			if (rolesList != null && rolesList.size() > 0) {
				Map vmap = new HashMap();
				vmap.put("locked", "0");
				vmap.put("adminid", "admin");
				for (Roles roles : rolesList) {
					String roleid = roles.getUuid();
					vmap.put("roleid", roleid);
					Integer usernum = (Integer) mysession.selectOne("gsys.countUsersByRoleMap", vmap);
					if (usernum != null) {
						roles.setUsernum(usernum);
					}
				}
			}
		} catch (Exception e) {
			rolesList = null;
			e.printStackTrace();
		} finally {
			//mysession.close();
			DbExecutor.close(mysession);
		}

		if (rolesList == null) {
			rolesList = new ArrayList<Roles>();
		}

		dictionary.put("total", total);
		dictionary.put("list", rolesList);
		return CycleLogic.NEXT;
	}

}