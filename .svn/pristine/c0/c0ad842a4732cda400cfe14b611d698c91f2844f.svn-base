package com.app.home;

import com.app.entity.AppQuiz;
import com.cmit.cycle.core.CycleLogic;
import com.cmit.cycle.core.Utils;
import com.gsys.common.DbExecutor;
import com.gsys.common.FileGetExtName;

import java.io.File;
import java.util.Dictionary;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;


public class ImgTempConvert implements CycleLogic{
	

	public int invoker(Dictionary dictionary){
		HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
		String servletpath = request.getSession().getServletContext().getRealPath("");
		String imgdir = "appimgs\\";//最终图片存储文件夹
		SqlSession mysession = null;
		try{
			mysession = DbExecutor.open();
			
			List<AppQuiz> list = mysession.selectList("gsys.listtempimg",1);
			for(int i = 0;i<list.size();i++){
				AppQuiz apq = list.get(i);
	            File tempfile = new File(servletpath+apq.getImgpath());
	            if(!tempfile.exists()){
	            	continue;
	            }
	            File dir = new File(servletpath+imgdir);
	            if(!dir.exists()  && !dir.isDirectory()){
	            	dir.mkdir();
	            }
	            String newfilename = Utils.createUUID()+FileGetExtName.getExtName(tempfile.getName(),'.');
	            if (tempfile.renameTo(new File(servletpath+imgdir+newfilename))) {
	                apq.setImgpath(imgdir+newfilename);
	                apq.setImgstate(2);
	                mysession.update("gsys.updateAppQuiz",apq);
	            } else {
	                System.out.println("File is failed to move!");
	            }
			}
			mysession.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbExecutor.close(mysession);
		}
		return CycleLogic.NEXT;
	}

}