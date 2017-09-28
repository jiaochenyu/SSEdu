package com.gsys.admin;

import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.cmit.cycle.core.CycleLogic;
import com.cmit.cycle.core.Utils;
import com.gsys.common.DbExecutor;
import com.gsys.common.Encryptor;
import com.gsys.common.StringHelper;
import com.gsys.model.Users;


public class SaveUsers implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		String uuid = request.getParameter("uuid");
		String loginid = request.getParameter("loginid");
		String displayname = request.getParameter("displayname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		//String type = request.getParameter("type");

		Users model = new Users();
		model.setUuid(uuid);
		//model.setLoginid(loginid);
		model.setDisplayname(displayname);
		model.setEmail(email);
		//model.setType(type);
		model.setType("1");

		boolean result = false;
		String error = null;

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			if (StringHelper.isEmpty(uuid) || "undefined".equals(uuid)) {
				Map map = new HashMap();
				if (StringHelper.isNotEmpty(loginid)) {
					map.put("loginid", loginid.trim());
				}
				Integer total = (Integer) mysession.selectOne("gsys.countUsers", map);
				if (total != null && total == 0) {
					uuid = Utils.createUUID();
					model.setUuid(uuid);
					model.setLoginid(loginid.trim());
					if (password != null) {
						model.setPassword(Encryptor.md5(password));
					}
					model.setCreated(new Date());
					model.setLocked("0");

					mysession.insert("gsys.insertUsers", model);
					mysession.commit();
					result = true;
				} else if (total != null) {
					error = "loginid-exist";
				}
			} else {
				mysession.update("gsys.updateUsers", model);
				mysession.commit();
				result = true;
			}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		} finally {
			//mysession.close();
			DbExecutor.close(mysession);
		}

		if (error != null) {
			dictionary.put("result", error);
		} else {
			dictionary.put("result", result);
		}
		return CycleLogic.NEXT;
	}

}