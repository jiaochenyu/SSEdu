package org.our.cycle.common;

import com.cmit.cycle.core.CycleLogic;
import java.util.Dictionary;


public class CreatePageBar implements CycleLogic{

	private static final String DI_CURRENTPAGE = "CurrentPage";
	private static final String DI_BUTTONACTION = "ButtonAction";
	private static final String DI_TOTALPAGE = "TotalPage";
	private static final String DO_PAGEBARSTRING = "PageBarString";

	public int invoker(Dictionary dictionary){
		int cur=Integer.parseInt(dictionary.get(DI_CURRENTPAGE).toString());
		int tot=Integer.parseInt(dictionary.get(DI_TOTALPAGE).toString());
		boolean enddisabled=false;
		boolean startdisabled=false;
		if(cur<=1){
			cur=1;
			startdisabled=true;
			dictionary.put(DI_CURRENTPAGE, "1");
		}else if(cur>=tot){
			cur=tot;
			enddisabled=true;
			dictionary.put(DI_CURRENTPAGE, String.valueOf(tot));
		}
		int totalcount=(Integer)dictionary.get("totalcount");
		int pagestart=(Integer)dictionary.get("pagestart");
		int pagesize=(Integer)dictionary.get("pagesize");
		int end=pagestart+pagesize;
		if((pagestart+pagesize)>=totalcount){
			end=totalcount;
		}
		StringBuffer buf=new StringBuffer();
		buf.append("\n<table class=\"pagecursor\">");
		buf.append("<tr>");
		buf.append("<td class=\"pagebutton\" nowrap>");
		buf.append("<span class=\"totalitem\">Total:"+totalcount+" Now:"+pagestart+"-"+end+" </span>");
		buf.append("<input type=\"button\" name=\"start\" id=\"page_start\" onclick=\""+dictionary.get(DI_BUTTONACTION)+"\" value=\"<<\" class=\"go_start\"");
		if(startdisabled){
			buf.append(" disabled ");
		}
		buf.append("/>");
		buf.append("<input type=\"button\" name=\"prev\" id=\"page_prev\" onclick=\""+dictionary.get(DI_BUTTONACTION)+"\" value=\"<\" class=\"prev_bt\"");
		if(startdisabled){
			buf.append(" disabled ");
		}
		buf.append("/>");
		buf.append("<input type=\"button\" name=\"next\" id=\"page_next\" onclick=\""+dictionary.get(DI_BUTTONACTION)+"\" value=\">\" class=\"next_bt\"");
		if(enddisabled){
			buf.append(" disabled ");
		}
		buf.append("/>");
		buf.append("<input type=\"button\" name=\"end\" id=\"page_end\" onclick=\""+dictionary.get(DI_BUTTONACTION)+"\" value=\">>\" class=\"go_end\"");
		if(enddisabled){
			buf.append(" disabled ");
		}
		buf.append("/>");
		buf.append("<input type=\"hidden\" name=\"pageNo\" id=\"hidden_pageno\" value=\""+dictionary.get(DI_CURRENTPAGE)+"\"/>");
		buf.append("<input type=\"hidden\" name=\"totalPage\" id=\"hidden_total_page\" value=\""+dictionary.get(DI_TOTALPAGE)+"\"/>");
		buf.append("<input type=\"text\" name=\"curentNo\" id=\"curentNo\" value=\""+dictionary.get(DI_CURRENTPAGE)+"\" class=\"cur_txt\"/>");
		buf.append("<span class=\"pagetotal\">/"+dictionary.get(DI_TOTALPAGE)+"</span>");
		buf.append("<input type=\"button\" name=\"gobt\" id=\"page_go\" onclick=\""+dictionary.get(DI_BUTTONACTION)+"\" value=\"GO\" class=\"go_bt\"/>");
		buf.append("</td>");
		buf.append("</tr>");
		buf.append("<table>\n");
		dictionary.put(DO_PAGEBARSTRING, buf.toString());
		return CycleLogic.NEXT;
	}

}