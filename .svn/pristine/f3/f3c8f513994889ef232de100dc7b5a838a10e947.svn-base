package com.app.home;

import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.our.cycle.common.Utils;
import org.our.cycle.utils.SafetyUtils;

import com.app.entity.AppSection;
import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.ClassSettings;


public class GetAllSections implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletResponse response=(HttpServletResponse) dictionary.get("response");
		String data=(String) dictionary.get(ClassSettings.DOM_DOC);
		int code=(int) dictionary.get(ClassSettings.STATUS_CODE);
		String msg = (String)dictionary.get(ClassSettings.STATUS_MSG);
		if(code == -1){
			return CycleLogic.NEXT;
		}
		SqlSession mysession=null;
		ServletOutputStream out=null;
		JSONArray arr = null;
		try {
			mysession = Utils.getSqlSessionFactory().openSession();
			JSONObject obj=(JSONObject) new JSONParser().parse(data);
			Map<String,Object> sqlMap=new HashMap();
			String username=(String) dictionary.get(ClassSettings.DOM_USERNAME);
			String sub=(String) obj.get("coursename");
			sqlMap.put("username",username);
			sqlMap.put("sub",sub);
			List<AppSection> list=mysession.selectList("gsys.getAllSection",sqlMap);
			arr=new JSONArray();
			for(AppSection s:list){
				JSONObject o=new JSONObject();
				o.put("uuid", s.getUuid());
				o.put("sectionname", s.getSectionname());
				arr.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = -1;
			msg = "获取章节失败";
		}finally{
			mysession.close();
			try {
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		JSONObject object=new JSONObject();
		object.put("status", code);
		object.put("msg",msg);
		if(code==100){
			object.put("array", arr);
		}
		try {
			SafetyUtils.hDString(object.toJSONString(), response,out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CycleLogic.NEXT;
	}

}