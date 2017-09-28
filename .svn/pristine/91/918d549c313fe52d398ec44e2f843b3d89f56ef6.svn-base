package com.app.appinterface;

import com.app.entity.AppQuiz;
import com.app.entity.AppSection;
import com.cmit.cycle.core.CycleLogic;
import com.cmit.cycle.core.Utils;
import com.gsys.common.DbExecutor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class ImpQuiz implements CycleLogic{


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
            JSONObject obj = new JSONObject();
            for(int i = 0;i<items.size();i++){
                FileItem item = items.get(i);
                if(!item.isFormField()){
                	String name = item.getName();
                    String uuid = Utils.createUUID();
                    String extName=name.substring(name.lastIndexOf("."));
                    File file_check =new File(file,uuid+extName);
                    item.write(file_check);
                    session.setAttribute("ExcelFile", file_check);
                    session.setAttribute("Filename", name);
                    boolean status = validate(file_check,obj);
                    dictionary.put("status", status);
                    if(status){
                    	file_check.delete();
                    }
                }else{
                	obj.put(item.getFieldName(),item.getString("UTF-8"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{

        }
		return CycleLogic.NEXT;
	}
	
	private boolean validate(File file,JSONObject obj) {
		String temping = "temping/";
		boolean status = true;
		SqlSession mysession = null;
		try {
			mysession = DbExecutor.open();
			String courseid = (String)obj.get("courseid");
			Map<String,AppSection> sectionmap = mysession.selectMap("gsys.mapSection",courseid,"sectionname");
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
			XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.RED.getIndex());
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			
			XSSFSheet sheet = workbook.getSheetAt(0);
			int lastrownum = sheet.getLastRowNum();
			List<AppQuiz> quizlist = new ArrayList<AppQuiz>();
			Timestamp createtime = new Timestamp(new Date().getTime());
			for(int i = 1;i<=lastrownum;i++){
				Row row = sheet.getRow(i);
				if(row.getCell(0) == null){
					continue;
				}
				int lastcellnum = row.getLastCellNum();
				
				String xuhao = getCellValue(row,0);
				String section = getCellValue(row,1).trim();
				int level = 1;
				if(!getCellValue(row,2).equals("")){
					level = Integer.valueOf(getCellValue(row,2));
				}
				String content = getCellValue(row,3).trim();
				
				String imgpaths = getCellValue(row,4);
				int imgstate = 0;
				JSONArray imgpathjary = new JSONArray();
				if(!imgpaths.equals("")){
					String[] imgpathary = imgpaths.split("/");
					for(int k = 0;k<imgpathary.length;k++){
						imgstate = 1;
						JSONObject imgpathobj = new JSONObject();
						imgpathobj.put("path",temping+imgpathary[k]);
						imgpathjary.add(imgpathobj);
					}
				}
				String imgpath = imgpathjary.toJSONString();
				
				String remarks = getCellValue(row,5);
				int times = 60000;
				if(!getCellValue(row,6).equals("")){
					times = Integer.valueOf(getCellValue(row,6));
				}
				String rightanswer = getCellValue(row,7);
				String[] rightary = rightanswer.split("-");
				int issingle = 1;
				if(rightary.length>1){
					issingle = 0;
				}
				
				if(section.equals("") || content.equals("") || rightanswer.equals("") || lastcellnum<8){
					status = false;
    				setStyle(row,style);
    				continue;
				}
				
				AppSection asection = sectionmap.get(section);
				if(asection == null){
					status = false;
    				setStyle(row,style);
    				continue;
				}
				
				JSONArray answerary = new JSONArray();
				int idindex = 1;
				for(int k=8;k<lastcellnum;k++){
					JSONObject anobj = new JSONObject();
					anobj.put("id",idindex++);
					anobj.put("name",getCellValue(row,k).trim());
					answerary.add(anobj);
				}
				String answer = answerary.toJSONString();
				
				if(status){
					AppQuiz quiz = new AppQuiz();
					quiz.setUuid(Utils.createUUID());
					quiz.setSchoolid((String)obj.get("schoolid"));
					quiz.setSchoolname((String)obj.get("schoolname"));
					quiz.setCourseid((String)obj.get("courseid"));
					quiz.setCoursename((String)obj.get("coursename"));
					quiz.setTeacherid((String)obj.get("teacherid"));
					quiz.setTeachername((String)obj.get("teachername"));
					quiz.setSectionid(asection.getUuid());
					quiz.setSectionname(asection.getSectionname());
					quiz.setContent(content);
					quiz.setAnswer(answer);
					quiz.setRightanswer(rightanswer);
					quiz.setIssingle(issingle);
					quiz.setImgpath(imgpath);
					quiz.setImgstate(imgstate);
					quiz.setRemarks(remarks);
					quiz.setTimes(times);
					quiz.setCreatetime(createtime);
					quiz.setLevel(level);
					quizlist.add(quiz);
				}
			}
			
			if(!status){
				workbook.write(new FileOutputStream(file));
			}else{
				for(AppQuiz quiz:quizlist){
					mysession.insert("gsys.addAppQuiz",quiz);
				}
				mysession.commit();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	
	private void setStyle(Row row, XSSFCellStyle style) {
        for (Cell cell : row){
            if (cell != null){
                cell.setCellStyle(style);
            }
        }
    }
	
	private String getCellValue(Row row,int index){
        String value = "";
        Cell cell = row.getCell(index);
        if(cell!=null){
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_BLANK :break;
                case Cell.CELL_TYPE_BOOLEAN :boolean b = cell.getBooleanCellValue();if(b==true) value = "1";break;
                case Cell.CELL_TYPE_ERROR :break;
                case Cell.CELL_TYPE_STRING :value= cell.getStringCellValue().trim();break;
                case Cell.CELL_TYPE_NUMERIC :cell.setCellType(Cell.CELL_TYPE_STRING);value = cell.toString();break;
            }
        }
        return value.replace("\n","").replace("\r","");
    }
}