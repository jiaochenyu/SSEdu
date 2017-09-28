package com.app.appinterface;

import com.app.entity.AppQuiz;
import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.DbExecutor;
import com.gsys.common.PageHelper;
import com.gsys.common.StringHelper;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;


public class LoadAppQuiz implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		Map map = new HashMap();
		PageHelper.getPageMap(request, map);
		
		int total = 0;
		List<AppQuiz> list = null;
		List<AppQuiz> lists = new ArrayList();
		SqlSession mysession = null;
		try{
			mysession = DbExecutor.open();
			
			total =(int)mysession.selectOne("gsys.selectQuizToTal",map);
			if(total>0){
				list = mysession.selectList("gsys.listQuizByMap",map);
				
				for(int i =0 ;i<list.size();i++){
					AppQuiz aqz = list.get(i);
					String answer = aqz.getAnswer();
					answer = answer.replace("\\","\\\\");
					answer = answer.replace("\"","\\\"");
					aqz.setAnswer(answer);
					

					String imgpath = aqz.getImgpath();
					imgpath = imgpath.replace("\\","\\\\");
					imgpath = imgpath.replace("\"","\\\"");
					aqz.setImgpath(imgpath);
					lists.add(i,aqz);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbExecutor.close(mysession);
		}
		dictionary.put("total",total);
		dictionary.put("list",lists);
		return CycleLogic.NEXT;
	}

}