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
import com.gsys.model.RoleUsers;


public class ListRoleUsers implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		String roleid = request.getParameter("roleid");
		String userid = request.getParameter("userid");
		String loginid = request.getParameter("loginid");

		Integer total = 0;
		List<RoleUsers> roleUsersList = null;

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			Map map = new HashMap();
			map.put("locked", "0");
			map.put("adminid", "admin");
			if (StringHelper.isNotEmpty(roleid)) {
				map.put("roleid", roleid);
			}
			if (StringHelper.isNotEmpty(userid)) {
				map.put("userid", userid);
			}
			if (StringHelper.isNotEmpty(loginid)) {
				map.put("loginid", loginid);
			}
			PageHelper.getPageMap(request, map);

			total = (Integer) mysession.selectOne("gsys.countRoleUsers", map);
			if (total > 0) {
				roleUsersList = mysession.selectList("gsys.listPagedRoleUsers", map);
			}
		} catch (Exception e) {
			roleUsersList = null;
			e.printStackTrace();
		} finally {
			//mysession.close();
			DbExecutor.close(mysession);
		}

		if (roleUsersList == null) {
			roleUsersList = new ArrayList<RoleUsers>();
		}

		dictionary.put("total", total);
		dictionary.put("list", roleUsersList);
		return CycleLogic.NEXT;
	}

}