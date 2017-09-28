package com.gsys.main;

import java.util.Dictionary;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.App;
import com.gsys.common.DbExecutor;
import com.gsys.common.Encryptor;
import com.gsys.model.Users;


public class SaveLoginPwd implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		String oldpassword = request.getParameter("oldpassword");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");

		boolean result = false;
		if (oldpassword == null || password == null || repassword == null
				|| !password.equals(repassword)) {
			dictionary.put("result", result);
			return CycleLogic.NEXT;
		}

		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute(App.LOGIN_USER);

		String error = null;

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			Users model = (Users)mysession.selectOne("gsys.getUsers", user.getUuid());
			if (model != null) {
				String pwd = user.getPassword();
				if (pwd != null && pwd.equals(Encryptor.md5(oldpassword))) {
					model.setPassword(Encryptor.md5(password));

					mysession.update("gsys.updateUsersSetPassword", model);
					mysession.commit();
					result = true;
				} else {
					error = "password-not-equals";
				}
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