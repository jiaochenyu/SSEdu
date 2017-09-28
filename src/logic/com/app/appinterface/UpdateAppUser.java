package com.app.appinterface;

import com.app.entity.AppSection;
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


public class UpdateAppUser implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletResponse response = (HttpServletResponse) dictionary.get("response");
		int code=(int) dictionary.get(ClassSettings.STATUS_CODE);
		String msg = (String) dictionary.get(ClassSettings.STATUS_MSG);
		String data=(String) dictionary.get(ClassSettings.DOM_DOC);
		org.json.simple.JSONObject userobj = (org.json.simple.JSONObject) dictionary.get(ClassSettings.APP_USER);
		String useruuid = (String)userobj.get("uuid");
		SqlSession mysession = null;
		JSONArray jsonary = null;
		try{
			mysession = DbExecutor.open();
			AppUser user = (AppUser) mysession.selectOne("gsys.findUserByUuid",useruuid);
			JSONObject dataobj = new JSONObject(data);
			if(dataobj.has("nickname")){
				user.setNickname(dataobj.getString("nickname"));
			}
			if(dataobj.has("email")){
				user.setEmail(dataobj.getString("email"));
			}
			if(dataobj.has("sex")){
				user.setSex(dataobj.getString("sex"));
			}
			if(dataobj.has("realname")){
				user.setRealname(dataobj.getString("realname"));
			}
			
			mysession.update("gsys.updateAppUser",user);
			mysession.commit();
			
		}catch(Exception e){
			e.printStackTrace();
			code = -1;
			msg = "修改失败";
		}finally{
			DbExecutor.close(mysession);
		}
		
		JSONObject object=new JSONObject();
		object.put("status", code);
		object.put("msg",msg);
		
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