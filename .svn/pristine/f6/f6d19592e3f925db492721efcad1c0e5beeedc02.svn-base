package com.gsys.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.cmit.cycle.core.CycleLogic;
import com.cmit.cycle.core.Utils;
import com.gsys.common.App;
import com.gsys.common.AuthUtils;
import com.gsys.common.BaseAuthUtils;
import com.gsys.common.DbExecutor;
import com.gsys.common.Encryptor;
import com.gsys.common.StringHelper;
import com.gsys.model.Logs;
import com.gsys.model.RoleAuth;
import com.gsys.model.Users;


public class SystemLogin implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//String remember = request.getParameter("remember");

		if (StringHelper.isBlank(username)) {
			if (username != null) {
				request.setAttribute("username", username);
				request.setAttribute("error", "username-invalid");
			}
			return CycleLogic.ERROR;
		}
		if (StringHelper.isEmpty(password)) {
			if (username != null) {
				request.setAttribute("username", username);
			}
			if (password != null) {
				request.setAttribute("error", "password-invalid");
			}
			return CycleLogic.ERROR;
		}

		boolean result = false;
		String error = null;

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			if (StringHelper.isNotBlank(username)) {
				Map dmap = new HashMap();
				dmap.put("loginid", username);
				Users user = (Users)mysession.selectOne("gsys.getLoginUsersByMap", dmap);
				if (user != null && "1".equals(user.getLocked())) {
					error = "user-is-locked";
				} else if (user != null && "0".equals(user.getLocked())) {
					String pwd = user.getPassword();
					if (pwd != null && pwd.equals(Encryptor.md5(password))) {
						result = true;

						HttpSession session = request.getSession();
						session.setAttribute(App.LOGIN_USER, user);
						session.setAttribute(App.LOGIN_USER_ID, user.getLoginid());

						session.setAttribute(App.LOGIN_AUTH_MAP, null);

						if ("admin".equals(user.getLoginid())) {
							AuthUtils authUtils = new BaseAuthUtils(true);
							session.setAttribute(AuthUtils.AUTH_UTILS, authUtils);
						} else {
							Map vmap = new HashMap();
							vmap.put("userid", user.getUuid());
							List<RoleAuth> roleAuthList = mysession.selectList("gsys.listLoginUserRoleAuth", vmap);
							//List<AuthUrl> authUrlList = mysession.selectList("gsys.listLoginUserAuthUrl", vmap);
							if (roleAuthList != null) {
								Map<String, String> authMap = new HashMap<String, String>();
								for (RoleAuth auth : roleAuthList) {
									//authMap.put(auth.getAuthid(), auth.getUuid());
									authMap.put(auth.getAuthid(), auth.getType());
								}
								session.setAttribute(App.LOGIN_AUTH_MAP, authMap);
							}

							AuthUtils authUtils = new BaseAuthUtils(session);
							session.setAttribute(AuthUtils.AUTH_UTILS, authUtils);
						}

						/*if (remember != null && "1".equals(remember)) {
							HttpServletResponse response = (HttpServletResponse)dictionary.get("response");
							Cookie cookie = new Cookie(App.USERNAME_COOKIE, username);
							response.addCookie(cookie);
						}*/

						try {
							Logs logs = new Logs();
							logs.setUuid(Utils.createUUID());
							logs.setIp(request.getRemoteAddr());
							logs.setUserid(user.getLoginid());
							logs.setOtime(formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
							//logs.setAurl("sys-login.cyc");
							//logs.setPagename("系统登录");
							logs.setAurl("");
							logs.setPagename("");
							logs.setType("0"); // 0-登录
							mysession.insert("gsys.insertLogs", logs);
							mysession.commit();
						} catch (Exception e) {
							e.printStackTrace();
						}

					} else {
						error = "password-not-equals";
					}
				} else {
					error = "user-not-found";
				}
			} else {
				/*Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if (App.USERNAME_COOKIE.equals(cookie.getName())) {
							username = cookie.getValue();
							break;
						}
					}
				}*/
			}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		} finally {
			//mysession.close();
			DbExecutor.close(mysession);
		}

		if (!result) {
			request.setAttribute("error", error);
			request.setAttribute("username", username);
			return CycleLogic.ERROR;
		}
		request.setAttribute("username", username);
		return CycleLogic.NEXT;
	}

	public String formatDate(Date date, String pattern) {
		String result = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat(pattern);
			result = dateFormat.format(date);
		} catch (Exception e) {
			result = "";
			//e.printStackTrace();
		}
		return result;
	}

}