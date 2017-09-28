package com.gsys.user;

import com.cmit.cycle.core.CycleLogic;
import com.gsys.common.ClassSettings;
import com.gsys.common.DES3;

import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Dictionary;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.our.cycle.utils.SafetyUtils;

public class LoginFail implements CycleLogic {

	public int invoker(Dictionary dictionary) {
		HttpServletResponse response = (HttpServletResponse) dictionary.get("response");
		Object code = dictionary.get(ClassSettings.STATUS_CODE);
		String msg = (String) dictionary.get(ClassSettings.STATUS_MSG);
		JSONObject userobj = (JSONObject)dictionary.get(ClassSettings.APP_USER);
		
		String iCode = "-1";
		String iMsg = "失败";
		if (code != null && msg != null) {
			iCode = String.valueOf(code);
			iMsg = msg;
		}

		JSONObject obj = new JSONObject();
		obj.put("status", iCode);
		obj.put("msg", iMsg);
		if(iCode.equals("100")){
			obj.put("user",userobj);
		}
		ServletOutputStream out =null;
		try {
			SafetyUtils.hDString(obj.toString(),response,out);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return CycleLogic.NEXT;
	}

}