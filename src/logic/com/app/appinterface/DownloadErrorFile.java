package com.app.appinterface;

import com.cmit.cycle.core.CycleLogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Dictionary;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DownloadErrorFile implements CycleLogic{


	@Override
	public int invoker(Dictionary dictionary){
        HttpServletRequest request = (HttpServletRequest) dictionary.get("request");
        HttpServletResponse response = (HttpServletResponse) dictionary.get("response");
        HttpSession session = request.getSession();
        File file = (File) session.getAttribute("ExcelFile");
        String filename = (String) session.getAttribute("Filename");
        if (file != null && file.exists()) {
            if (filename != null) {
                int index = filename.lastIndexOf(".");
                filename = filename.substring(0,index).replaceAll(" ","")+"（错误文件）"+ filename.substring(index);
            }
            download(request,response,file,filename);
        }

		return CycleLogic.NEXT;
	}
    public void download(HttpServletRequest request, HttpServletResponse response, File file, String downloadname) {
        try {
            String userAgent = request.getHeader("User-Agent");
            if (userAgent.contains("MSIE")||userAgent.contains("Trident")||userAgent.contains("Edge")) {
                //针对IE或者以IE为内核的浏览器：
                downloadname = URLEncoder.encode(downloadname, "UTF-8");
            } else {
                //非IE浏览器的处理：
                downloadname = new String(downloadname.getBytes("UTF-8"),"ISO-8859-1");
            }

            response.reset();
            response.setContentType("application/x-download;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename="+ downloadname);

            ServletOutputStream out = response.getOutputStream();
            FileInputStream input = new FileInputStream(file);

            byte[] data = new byte[2048];
            int len = 0;
            while((len = input.read(data))>0){
                out.write(data, 0, len);
            }
            out.flush();
            out.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}