package com.gsys.admin;

import java.util.Dictionary;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.DbExecutor;
import com.gsys.common.Encryptor;
import com.gsys.model.Users;


public class SaveUserPwd implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		String uuid = request.getParameter("uuid");
		String password = request.getParameter("password");
		//String repassword = request.getParameter("repassword");

		boolean result = false;
		/*if (password == null || repassword == null
				|| !password.equals(repassword)) {
			dictionary.put("result", result);
			return CycleLogic.NEXT;
		}*/

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			if (uuid != null && password != null && !"undefined".equals(uuid)) {
				Users model = new Users();
				model.setUuid(uuid);
				model.setPassword(Encryptor.md5(password));

				mysession.update("gsys.updateUsersSetPassword", model);
				mysession.commit();
				result = true;
			}
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