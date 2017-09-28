package com.app.home;

import java.io.IOException;
import java.util.Dictionary;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.our.cycle.common.Utils;
import org.our.cycle.utils.SafetyUtils;

import com.app.entity.AppQuiz;
import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.ClassSettings;


public class GetRandomQuiz implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletResponse response=(HttpServletResponse) dictionary.get("response");
		int code=(int) dictionary.get(ClassSettings.STATUS_CODE);
		String msg = (String)dictionary.get(ClassSettings.STATUS_MSG);
		if(code==-1){
			return CycleLogic.NEXT;
		}
		String data=(String) dictionary.get(ClassSettings.DOM_DOC);
		SqlSession mysession=null;
		ServletOutputStream out=null;
		JSONArray arr = null;
		try{
			mysession = Utils.getSqlSessionFactory().openSession();
			JSONObject obj=new JSONObject(data);
			String uuids=(String) obj.get("uuids");
			String[] strArr=uuids.split("-");
			List<AppQuiz> list=mysession.selectList("gsys.getRandomQuiz",strArr);
			arr=new JSONArray();
			
			for(AppQuiz aq:list){
				JSONObject o=new JSONObject();
				o.put("uuid", aq.getUuid());
				o.put("schoolid", aq.getSchoolid());
				o.put("schoolname", aq.getSchoolname());
				o.put("courseid", aq.getCourseid());
				o.put("coursename", aq.getCoursename());
				o.put("sectionid", aq.getSectionid());
				o.put("sectionname", aq.getSectionname());
				o.put("content", aq.getContent());
				org.json.JSONArray anary = new org.json.JSONArray(aq.getAnswer());
				int len = anary.length();
				int[] source = new int[len];
				for(int i = 0;i<anary.length();i++){
					source[i] = i+1;
				}
				int[] result = new int[len];
				Random rd = new Random();
				int index = 0;
				for (int i = 0; i < result.length; i++) {
					index = Math.abs(rd.nextInt() % len--);
					result[i] = source[index];
					source[index] = source[len];
				}
				org.json.JSONArray newanary = new org.json.JSONArray();
				for(int i = 0;i<result.length;i++){
					for(int k =0;k<anary.length();k++){
						JSONObject anobj = anary.getJSONObject(k);
						if(anobj.getString("id").equals(result[i]+"")){
							newanary.put(anobj);
						}
					}
				}
				o.put("answer", newanary);
				o.put("rightanswer", aq.getRightanswer());
				o.put("issingle", aq.getIssingle());
				if(!aq.getImgpath().trim().equals("")){
					org.json.JSONArray pathary = new org.json.JSONArray(aq.getImgpath());
					o.put("imgpath",pathary);
				}
				o.put("imgstate", aq.getImgstate());
				o.put("level",aq.getLevel());
				o.put("remarks",aq.getRemarks());
				o.put("times",aq.getTimes());
				arr.add(o);
			}
		}catch(Exception e){
			e.printStackTrace();
			code=-1;
			msg="获取试题失败";
		}finally{
			if(mysession!=null){
				mysession.close();
			}
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
			SafetyUtils.hDString(object.toString(), response, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CycleLogic.NEXT;
	}

}