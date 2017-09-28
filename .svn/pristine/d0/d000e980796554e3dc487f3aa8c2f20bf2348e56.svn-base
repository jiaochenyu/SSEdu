package com.gsys.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.gsys.model.AuthUrl;

public class AccessUtility implements Serializable {
	private static final long serialVersionUID = 1L;
	private static AccessUtility instance = null;
	private Map<String, List<String>> accessMap = null;

	private AccessUtility() {
		SqlSession mysession = null;
		try {
			//mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
			mysession = DbExecutor.open();
			@SuppressWarnings("unchecked")
			List<AuthUrl> authUrlList = mysession.selectList("gsys.listAllAuthUrl");
			if (authUrlList != null) {
				Map<String, List<String>> accessMap = new HashMap<String, List<String>>();
				for (AuthUrl authUrl : authUrlList) {
					String key = StringHelper.lowerCase(authUrl.getAccurl());
					List<String> valueList = accessMap.get(key);
					if (valueList == null) {
						valueList = new ArrayList<String>();
					}
					valueList.add(authUrl.getAuthid());
					accessMap.put(key, valueList);
				}
				setAccessMap(accessMap);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			/*if (mysession != null) {
				mysession.close();
			}*/
			DbExecutor.close(mysession);
		}
	}

	public static AccessUtility getInstance() {
		if (instance == null) {
			synchronized (AccessUtility.class) {
				if (null == instance) {
					instance = new AccessUtility();
				}
			}
		}
		return instance;
	}

	public Map<String, List<String>> getAccessMap() {
		return accessMap;
	}

	private void setAccessMap(Map<String, List<String>> accessMap) {
		this.accessMap = accessMap;
	}

}
