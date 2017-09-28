package com.app.appinterface;

import com.app.entity.AppZtmain;
import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.ClassSettings;
import com.gsys.common.DbExecutor;
import com.gsys.common.PageHelper;

import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.our.cycle.utils.SafetyUtils;


public class LoadZtmain implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletResponse response = (HttpServletResponse) dictionary.get("response");
		int code=(int) dictionary.get(ClassSettings.STATUS_CODE);
		String msg = (String) dictionary.get(ClassSettings.STATUS_MSG);
		String data=(String) dictionary.get(ClassSettings.DOM_DOC);
		SqlSession mysession = null;
		JSONArray jsonary = new JSONArray();
		try{
			JSONObject dataobj = new JSONObject(data);
			Map map = new HashMap();
			if(dataobj.has("appuserid")){
				map.put("appuserid",dataobj.getString("appuserid"));
			}
			if(dataobj.has("courseid")){
				map.put("courseid", dataobj.getString("courseid"));
			}
			if(dataobj.has("limit") && dataobj.has("offset")){
				PageHelper.getPageMap(dataobj.getInt("limit"),dataobj.getInt("offset"), map);
			}
			mysession = DbExecutor.open();
			List<AppZtmain> list = mysession.selectList("gsys.listZtmain",map);
			
			for(AppZtmain azm:list){
				JSONObject obj = new JSONObject();
				obj.put("uuid",azm.getUuid());
				obj.put("ztmainname",azm.getZtmainname());
				obj.put("createtime",azm.getCreatetime());
				obj.put("times",azm.getTimes());
				obj.put("accuracy",azm.getAccuracy());
				obj.put("courseid",azm.getCourseid());
				obj.put("coursename",azm.getCoursename());
				
				jsonary.add(obj);
			}
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