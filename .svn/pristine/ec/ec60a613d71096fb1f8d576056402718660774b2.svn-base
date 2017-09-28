package com.gsys.user;

import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.ClassSettings;
import com.gsys.common.DbExecutor;
import com.app.entity.AppUser;

import java.util.Dictionary;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;


public class CheckUser implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request =  (HttpServletRequest) dictionary.get("request");
        /*HttpSession session = request.getSession();
        JSONObject obj = new JSONObject();
        String rootpath = session.getServletContext().getRealPath("/");
        File file=new File(rootpath, "Accessories");
        if(!file.exists()){
            file.mkdir();
        }
        File tempfile=new File(file,"temp");
        if(!tempfile.exists()){
            tempfile.mkdir();
        }
        DiskFileItemFactory factory=new DiskFileItemFactory();
        factory.setSizeThreshold(1024*512);
        factory.setRepository(tempfile);
        ServletFileUpload fileUpload=new ServletFileUpload(factory);
        fileUpload.setHeaderEncoding("UTF-8");
        fileUpload.setFileSizeMax(10*1024*1024);
        try {
            List<FileItem> items=fileUpload.parseRequest(request);
            for(int i = 0;i<items.size();i++){
            	 FileItem item = items.get(i);
                 if(!item.isFormField()){
                     String name=item.getName();
                     String uuid = com.cmit.cycle.core.Utils.createUUID();
                     String extName=name.substring(name.lastIndexOf("."));
                     File file_check =new File(file,uuid+extName);
                     item.write(file_check);
                 }else{
                	 String name = item.getFieldName();
                	 String value = item.getString("UTF-8");
                	 obj.put(name,value);
                 }
            }
        }catch(Exception e){
        	e.printStackTrace();
        }
		*/
		
		boolean flag=false;
		SqlSession mysession = null;
		try {
			mysession = DbExecutor.open();
			/*String username = (String)obj.get("username");
			String password = (String)obj.get("password");
			String useruuid = (String)obj.get("useruuid");
			String data = (String)obj.get("data");*/
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String useruuid = request.getParameter("useruuid");
			String data = request.getParameter("data");
			
			//查询部分
			if(username!=null && password!=null){
				AppUser user=(AppUser) mysession.selectOne("gsys.findUserByName",username);
				if(user!=null){
					String pwd=user.getPassword();
					if (pwd.equals(password)) {
						flag=true;
					}
					if(data!=null){
						dictionary.put(ClassSettings.DOM_DOC,data);
					}
					JSONObject userobj = new JSONObject();
					userobj.put("uuid",user.getUuid());
					userobj.put("username",user.getUsername());
					userobj.put("password",user.getPassword());
					userobj.put("nickname",user.getNickname());
					userobj.put("part",user.getPart());
					userobj.put("email",user.getEmail());
					userobj.put("sex",user.getSex());
					userobj.put("schoolid",user.getSchoolid());
					userobj.put("schoolname",user.getSchoolname());
					userobj.put("courseid",user.getCourseid());
					userobj.put("coursename",user.getCoursename());
					userobj.put("state",user.getState());
					userobj.put("integral",user.getIntegral());
					userobj.put("vip",user.getVip());
					userobj.put("vipintegral",user.getVipintegral());
					userobj.put("vipstate",user.getVipstate());
					userobj.put("vipendtime",user.getVipendtime());
					userobj.put("realname",user.getRealname());
					userobj.put("imgpath",user.getImgpath());
					
					dictionary.put(ClassSettings.APP_USER,userobj);
					dictionary.put(ClassSettings.DOM_USERNAME,user.getUsername());
				}
			}else if(useruuid!=null){
				AppUser user=(AppUser) mysession.selectOne("gsys.findUserByUuid",useruuid);
				if(user!=null){
					flag=true;
					JSONObject userobj = new JSONObject();
					userobj.put("uuid",user.getUuid());
					userobj.put("username",user.getUsername());
					userobj.put("password",user.getPassword());
					userobj.put("nickname",user.getNickname());
					userobj.put("part",user.getPart());
					userobj.put("email",user.getEmail());
					userobj.put("sex",user.getSex());
					userobj.put("schoolid",user.getSchoolid());
					userobj.put("schoolname",user.getSchoolname());
					userobj.put("courseid",user.getCourseid());
					userobj.put("coursename",user.getCoursename());
					userobj.put("state",user.getState());
					userobj.put("integral",user.getIntegral());
					userobj.put("vip",user.getVip());
					userobj.put("vipintegral",user.getVipintegral());
					userobj.put("vipstate",user.getVipstate());
					userobj.put("vipendtime",user.getVipendtime());
					userobj.put("realname",user.getRealname());
					userobj.put("imgpath",user.getImgpath());
					
					dictionary.put(ClassSettings.APP_USER,userobj);
					dictionary.put(ClassSettings.DOM_USERNAME,user.getUsername());
				}
				if(data!=null){
					dictionary.put(ClassSettings.DOM_DOC,data);
				}
				
			}
			int code;
			String msg="";
			if(flag){
				code = 100;
				msg = "成功";
			}else{
				code = -1;
				msg = "用户名或密码不正确";
			}
			
			dictionary.put(ClassSettings.STATUS_CODE, code);
			dictionary.put(ClassSettings.STATUS_MSG, msg);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DbExecutor.close(mysession);
		}
		if(!flag){
			return CycleLogic.ERROR;
		}else{
			return CycleLogic.NEXT;
		}
	}

}