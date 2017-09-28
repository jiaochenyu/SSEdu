package org.our.cycle.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gsys.common.ClassSettings;
import com.gsys.common.DES3;

import sun.misc.BASE64Decoder;

public class SafetyUtils {
	
	public static void encryptString(String jsonString,HttpServletResponse response,ServletOutputStream out) throws Exception{
		byte[] key = new BASE64Decoder().decodeBuffer(ClassSettings.KEY);
		byte[] enBytes;
		enBytes = DES3.des3EncodeECB(key, jsonString.toString().getBytes("UTF-8"));
		out=response.getOutputStream();
		out.write(enBytes);
		out.flush();
	}
	
	public static String deciphering(HttpServletRequest request) throws Exception{
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		InputStream in=request.getInputStream();
		byte[] by=new byte[1024];
		int len=0;
		while((len=in.read(by))!=-1){
			out.write(by,0,len);
		}
		byte[] key=new BASE64Decoder().decodeBuffer(ClassSettings.KEY);
		byte[] dataArr= DES3.ees3DecodeECB(key, out.toByteArray());
		out.close();
		return new String(dataArr,"utf-8");
	}
	
	public static String deciphering(byte[] bary) throws Exception{
		byte[] key=new BASE64Decoder().decodeBuffer(ClassSettings.KEY);
		byte[] dataArr= DES3.ees3DecodeECB(key,bary);
		return new String(dataArr,"utf-8");
	}
	
	public static void hDString(String jsonString,HttpServletResponse response,ServletOutputStream out) throws Exception{
		byte[] enBytes = jsonString.getBytes("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		out=response.getOutputStream();
		out.write(enBytes);
		out.flush();
	}
}
