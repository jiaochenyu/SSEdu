package com.gsys.common;

import java.util.Dictionary;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class PageHelper {

	public static void getPageMap(HttpServletRequest request, Map map){
		//获取分页数据
		/*String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		if (pageNo == null) {
			pageNo = request.getParameter("pageNumber");
		}
		if (pageNo == null) {
			pageNo = request.getParameter("page");
		}
		if (pageSize == null) {
			pageSize = request.getParameter("rows");
		}*/
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		//计算分页
		int pagesize = parseIntValue(pageSize, -1);
		if (pagesize <= 0) {
			pagesize = 10;
		}
		int pagestart = parseIntValue(pageNo, -1);
		if (pagestart > 1) {
			pagestart = (pagestart - 1) * pagesize;
		} else {
			pagestart = 0;
		}
		map.put("offset", pagestart);
		map.put("limit", pagesize);
	}

	public static void getPageMap2(Dictionary dictionary, Map map){
		//获取分页数据
		/*String pageNo = (String) dictionary.get("pageNo");
		String pageSize = (String) dictionary.get("pageSize");
		if (pageNo == null) {
			pageNo = (String) dictionary.get("pageNumber");
		}
		if (pageNo == null) {
			pageNo = (String) dictionary.get("page");
		}
		if (pageSize == null) {
			pageSize = (String) dictionary.get("rows");
		}*/
		String pageNo = (String) dictionary.get("page");
		String pageSize = (String) dictionary.get("rows");
		//计算分页
		int pagesize = parseIntValue(pageSize, -1);
		if (pagesize <= 0) {
			pagesize = 10;
		}
		int pagestart = parseIntValue(pageNo, -1);
		if (pagestart > 1) {
			pagestart = (pagestart - 1) * pagesize;
		} else {
			pagestart = 0;
		}
		map.put("offset", pagestart);
		map.put("limit", pagesize);
	}
	
	/**
	 * @param pagesize 显示多少条
	 * @param pagestart 第几页 默认为第一页
	 * @param map 用来存储的map
	 * 
	 * */
	public static void getPageMap(int pagesize,int pagestart, Map map){
		if (pagesize <= 0) {
			pagesize = 10;
		}
		if (pagestart > 1) {
			pagestart = (pagestart - 1) * pagesize;
		} else {
			pagestart = 0;
		}
		map.put("offset", pagestart);
		map.put("limit", pagesize);
	}

	public static int parseIntValue(String value, int defaultValue) {
		if (value == null || value.trim().length() == 0) {
			return defaultValue;
		}
		int result = defaultValue;
		try {
			result = Long.valueOf(value.trim()).intValue();
		} catch (NumberFormatException e) {
			result = defaultValue;
		}
		return result;
	}

}
