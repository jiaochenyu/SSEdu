package com.app.appinterface;

import com.app.entity.AppSection;
import com.app.entity.AppUser;
import com.cmit.cycle.core.CycleLogic;
import com.cmit.cycle.core.Utils;
import com.gsys.common.ClassSettings;
import com.gsys.common.DbExecutor;

import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.our.cycle.utils.SafetyUtils;


public class ToRegister implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletResponse response = (HttpServletResponse) dictionary.get("response");
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String part = request.getParameter("part");
		
		
		SqlSession mysession = null;
		JSONArray jsonary = null;
		int code = 0;
		String msg = "";
		try{
			mysession = DbExecutor.open();
			
			AppUser user = new AppUser();
			user.setUuid(Utils.createUUID());
			user.setUsername(username);
			user.setPassword(password);
			user.setPart(part);
			AppUser u = (AppUser) mysession.selectOne("gsys.findAppUserByUsername",username);
			if(u!=null){
				code = -1;
				msg = "用户名已存在";
			}else{
				mysession.insert("gsys.addAppUser",user);
				mysession.commit();
				code = 100;
				msg = "注册成功";
			}
		}catch(Exception e){
			e.printStackTrace();
			code = -1;
			msg = "注册失败";
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