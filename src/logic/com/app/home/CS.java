package com.app.home;

import com.cmit.cycle.core.CycleLogic;
import com.cmit.cycle.core.Utils;

import java.io.File;
import java.util.Dictionary;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class CS implements CycleLogic{


	public int invoker(Dictionary dictionary){
		HttpServletRequest request =  (HttpServletRequest) dictionary.get("request");
        HttpSession session = request.getSession();
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
                     System.out.println("---fieldname:"+item.getFieldName());
                     System.out.println("name:"+item.getName());
                     String uuid = Utils.createUUID();
                     String extName=name.substring(name.lastIndexOf("."));
                     File file_check =new File(file,uuid+extName);
                     item.write(file_check);
                 }else{
                	 String name = item.getFieldName();
                	 
                	 String value = item.getString("UTF-8");
                	 //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                	 System.out.println(name + "=" + value);
                 }
            }
        }catch(Exception e){
        	e.printStackTrace();
        }
        
		return CycleLogic.NEXT;
	}

}