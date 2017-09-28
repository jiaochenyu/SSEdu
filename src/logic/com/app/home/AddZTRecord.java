package com.app.home;

import com.cmit.cycle.core.CycleLogic;
import com.cmit.cycle.core.Utils;
import com.gsys.common.ClassSettings;
import com.gsys.common.DbExecutor;
import com.app.entity.AppQuiz;
import com.app.entity.AppZtmain;
import com.app.entity.AppZtrecord;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Dictionary;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.our.cycle.utils.SafetyUtils;


public class AddZTRecord implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletResponse response=(HttpServletResponse) dictionary.get("response");
		int code=(int) dictionary.get(ClassSettings.STATUS_CODE);
		String msg = (String) dictionary.get(ClassSettings.STATUS_MSG);
		String data=(String) dictionary.get(ClassSettings.DOM_DOC);
		org.json.simple.JSONObject userobj = (org.json.simple.JSONObject) dictionary.get(ClassSettings.APP_USER);
		SqlSession mysession=null;
		try{
			mysession = DbExecutor.open();
			JSONArray dataary = new JSONArray(data);
			Timestamp nowtime = new Timestamp(new Date().getTime());
			JSONObject fobj = dataary.getJSONObject(0);
			String mainuuid = Utils.createUUID();
			AppQuiz aqiz = (AppQuiz) mysession.selectOne("gsys.findAppQuizById",fobj.getString("quizid"));
			String ztmainname = aqiz.getCoursename()+"练习";
			String courseid = aqiz.getCourseid();
			String coursename = aqiz.getCoursename();
			String appuserid = (String)userobj.get("uuid");
			AppZtmain ztm = new AppZtmain();
			ztm.setUuid(mainuuid);
			ztm.setZtmainname(ztmainname);
			ztm.setCreatetime(nowtime);
			ztm.setCourseid(courseid);
			ztm.setCoursename(coursename);
			ztm.setAppuserid(appuserid);
			int ztimes = 0;
			int t = 0;
			for(int i = 0; i<dataary.length();i++){
				JSONObject o = dataary.getJSONObject(i);
				String quizid = o.getString("quizid");
				AppQuiz aqz = (AppQuiz) mysession.selectOne("gsys.findAppQuizById",quizid);
				if(aqz!=null){
					AppZtrecord azt = new AppZtrecord();
					azt.setUuid(Utils.createUUID());
					azt.setCourseid(courseid);
					azt.setCoursename(coursename);
					azt.setCreatetime(nowtime);
					azt.setAppuserid(appuserid);
					azt.setZtmainid(mainuuid);
					azt.setQuizid(quizid);
					azt.setSectionid(aqz.getSectionid());
					azt.setSectionname(aqz.getSectionname());
					azt.setContent(aqz.getContent());
					azt.setRightanswer(aqz.getRightanswer());
					azt.setRemarks(aqz.getRemarks());
					azt.setImgpath(aqz.getImgpath());
					azt.setIssingle(aqz.getIssingle());
					azt.setAnswer(o.getString("answer"));
					azt.setMyanswer(o.getString("myanswer"));
					azt.setWhether(o.getInt("whether"));
					azt.setTimes(o.getInt("times"));
					if(o.getInt("whether") == 1){t++;}
					ztimes+=o.getInt("times");
					mysession.insert("gsys.addAppZtrecord",azt);
				}
			}
			ztm.setTimes(ztimes);
			if(t==0){
				ztm.setAccuracy("0.0%");
			}else{
				double zql = t*1.0/dataary.length();
				ztm.setAccuracy(getBaiFenBi(zql));
			}
			mysession.insert("gsys.addAppZtmain",ztm);
			
			mysession.commit();
			
		}catch(Exception e){
			e.printStackTrace();
			code = -1;
			msg = "保存做题记录失败";
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
	
	
	private static String getBaiFenBi(double d){
    	DecimalFormat df=new DecimalFormat(".#");
    	double dd = d*100;
    	
    	return df.format(dd)+"%";
    }

}