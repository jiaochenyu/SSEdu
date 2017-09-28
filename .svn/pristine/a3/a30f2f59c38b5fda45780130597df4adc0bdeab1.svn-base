package com.app.appinterface;

import com.app.entity.AppUser;
import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.ClassSettings;
import com.gsys.common.DbExecutor;

import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.our.cycle.utils.SafetyUtils;


public class LoadMyStudents implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletResponse response = (HttpServletResponse) dictionary.get("response");
		int code=(int) dictionary.get(ClassSettings.STATUS_CODE);
		String msg = (String) dictionary.get(ClassSettings.STATUS_MSG);
		org.json.simple.JSONObject userobj = (org.json.simple.JSONObject) dictionary.get(ClassSettings.APP_USER);
		String appuserid = (String)userobj.get("uuid");
		String data=(String) dictionary.get(ClassSettings.DOM_DOC);
		SqlSession mysession = null;
		JSONArray jsonary = null;
		try{
			JSONObject dataobj = new JSONObject(data);
			Map map = new HashMap();
			map.put("appuserid",appuserid);
			if(dataobj.has("condition")){
				map.put("condition",dataobj.getString("condition"));
			}
			mysession = DbExecutor.open();
			List<AppUser> list = mysession.selectList("gsys.listMyStudents",map);
			jsonary = new JSONArray(list);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbExecutor.close(mysession);
		}
		
		JSONObject object=new JSONObject();
		object.put("status", code);
		object.put("msg",msg);
		if(code==100){
			object.put("array",jsonary);
		}
		ServletOutputStream out=null;
		try {
			SafetyUtils.hDString(object.toString(), response, out);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return CycleLogic.NEXT;
	}

}