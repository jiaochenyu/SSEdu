package org.our.cycle.utils;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ReadCookie implements CycleLogic {

	private static final String DI_COOKIENAME = "CookieName";
	private static final String DO_COOKIEVALUE = "CookieValue";

	public int invoker(Dictionary dictionary) {
		String cname=(String)dictionary.get(DI_COOKIENAME);
		HttpServletRequest request =(HttpServletRequest)dictionary.get("request");
		Cookie cookies[] = request.getCookies(); // 将适用目录下所有Cookie读入并存入cookies数组中
		Cookie sCookie = null;
		String sname = null;
		String name = null;
		if (cookies == null) {
			//out.print("none any cookie");
		} else {
			//out.print(cookies.length + "<br>");
			for (int i = 0; i < cookies.length; i++) // 循环列出所有可用的Cookie
			{
				sCookie = cookies[i];
				sname = sCookie.getName();
				name = sCookie.getValue();
				if(cname.equals(sname)){
					dictionary.put(DO_COOKIEVALUE, name);
					break;
				}
				//out.println(sname + "->" + name + "<br>");
			}
		}
		return CycleLogic.NEXT;
	}

}