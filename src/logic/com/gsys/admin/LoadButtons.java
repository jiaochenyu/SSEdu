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
import com.gsys.model.Buttons;
import com.gsys.model.Pages;


public class LoadButtons implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		//HttpServletResponse response = (HttpServletResponse)dictionary.get("response");

		String roleid = request.getParameter("roleid");

		List<Pages> pageList = null;
		Map<String, List<Buttons>> buttonsMap = null;

		//List<Buttons> buttonList = null;

		//SqlSession mysession = org.our.cycle.common.Utils.getSqlSessionFactory().openSession();
		SqlSession mysession = DbExecutor.open();

		try {
			/*Map map = new HashMap();
			map.put("type", "b");
			if (StringHelper.isNotEmpty(roleid)) {
				map.put("roleid", roleid);
			}
			buttonList = mysession.selectList("gsys.listPowerButtons", map);*/
			Map map = new HashMap();
			map.put("type", "p");
			if (StringHelper.isNotEmpty(roleid)) {
				map.put("roleid", roleid);
			}
			//pageList = mysession.selectList("gsys.listPowerPages", map);
			pageList = mysession.selectList("gsys.listPowerPages2", map);
			if (pageList != null) {
				buttonsMap = new HashMap<String, List<Buttons>>();
				Map vmap = new HashMap();
				vmap.put("type", "b");
				if (StringHelper.isNotEmpty(roleid)) {
					vmap.put("roleid", roleid);
				}
				//List<Buttons> buttonList = mysession.selectList("gsys.listPowerButtons", vmap);
				List<Buttons> buttonList = mysession.selectList("gsys.listPowerButtons2", vmap);
				if (buttonList != null) {
					for (Buttons btn : buttonList) {
						String key = btn.getPageid();
						List<Buttons> btnList = buttonsMap.get(key);
						if (btnList == null) {
							btnList = new ArrayList<Buttons>();
						}
						btnList.add(btn);
						buttonsMap.put(key, btnList);
					}
				}
				/*for (Pages p : pageList) {
					vmap.put("pageid", p.getPageid());
					//List<Buttons> btnList = mysession.selectList("gsys.listPowerButtons", vmap);
					List<Buttons> btnList = mysession.selectList("gsys.listPowerButtons2", vmap);
					if (btnList != null) {
						buttonsMap.put(p.getPageid(), btnList);
					}
				}*/
			}
		} catch (Exception e) {
			//buttonList = null;
			e.printStackTrace();
		} finally {
			//mysession.close();
			DbExecutor.close(mysession);
		}

		/*if (buttonList == null) {
			buttonList = new ArrayList<Buttons>();
		}*/

		if (pageList == null) {
			pageList = new ArrayList<Pages>();
		}

		request.setAttribute("pageList", pageList);
		request.setAttribute("buttonsMap", buttonsMap);
		//request.setAttribute("buttonList", buttonList);
		//dictionary.put("list", buttonList);
		return CycleLogic.NEXT;
	}

}