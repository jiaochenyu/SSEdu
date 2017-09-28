package com.gsys.common;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
public class ImportTestExcel {
	private static String path="C:\\Users\\13354\\Desktop\\导入试题.xls";
	public static void main(String[] args) {
		importExcel();
	}
	
	public static void importExcel(){
		PreparedStatement pstmt;
		Connection conn = getConn();
		
		boolean hasError=false;
		if(path!=null&&!"".equals(path)){
			Workbook workbook=null;
			FileInputStream stream=null;
			try {
				//开启事务
				conn.setAutoCommit(false);
				File file=new File(path);
				stream=new FileInputStream(file);
				workbook=WorkbookFactory.create(stream);
				int sheetCount = workbook.getNumberOfSheets();
				if(sheetCount>0){
					//只读取第一张sheet	
					Sheet sheet = workbook.getSheetAt(0);
					CellStyle style=workbook.createCellStyle();
					style.setFillPattern(XSSFCellStyle.FINE_DOTS );
					style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
					style.setFillBackgroundColor(IndexedColors.RED.getIndex());
					
					Row row = sheet.getRow(0);  
					//匹配excel的前6个标题是否对应上
					String[] title={"序号","所属学校","所属学科","所属章节","所属教师","难度","题目","正确答案"};
					int correntTitle=title.length;
					for(int x=0;x<title.length;x++){
						Cell cell=row.getCell(x);
						if(cell.getStringCellValue().trim().equals(title[x])){
							correntTitle--;
						}
					}
					
					if(correntTitle==0){
						int maxRow=sheet.getLastRowNum();
						for(int x=1;x<=maxRow;x++){
							//答案的集合
							List<String> answerList=new ArrayList<>();
							//答案组成的字符串
							StringBuffer answerString=new StringBuffer();
							Row r=sheet.getRow(x);
							String schoolname=r.getCell(1).getStringCellValue().trim();
							String coursename=r.getCell(2).getStringCellValue().trim();
							String sectionname=r.getCell(3).getStringCellValue().trim();
							String teachername=r.getCell(4).getStringCellValue().trim();
							
							if(r.getCell(5)!=null){
								r.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
							}
							String level=r.getCell(5).getStringCellValue().trim();
							String content=r.getCell(6).getStringCellValue().trim();
							if(r.getCell(7)!=null){
								r.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
							}
							String rightanswer=r.getCell(7).getStringCellValue().trim();
							if("".equals(schoolname)||"".equals(coursename)||"".equals(sectionname)||"".equals(content)||"".equals(rightanswer)||"".equals(level)||"".equals(teachername)){
								addColor(r, style);
								throw new Exception("肯定有空的单元格");
							}
							if(Integer.valueOf(level)>10){
								throw new Exception("难度只设定到10");
							}
							for(int y=8;y<r.getLastCellNum();y++){
								String answer=r.getCell(y).getStringCellValue().trim();
								if(!"".equals(answer)){
									answerString.append(answer);
									if(y!=r.getLastCellNum()-1){
										answerString.append("@$.*");
									}
								}else{
									addColor(r, style);
									throw new Exception("答案呢");
								}
								answerList.add(answer);
							}
							
//							String courseid=(String) mysession.selectOne("gsys.getCourseIdByName",coursename);
//							String schoolid=(String) mysession.selectOne("gsys.getSchoolIdByName",schoolname);
//							String sectionid=(String) mysession.selectOne("gsys.getSectionIdByName",sectionname);
							String sql1="select uuid from app_course where coursename=?";
							String sql2="select uuid from app_school where schoolname=?";
							String sql3="select uuid from app_section where sectionname=?";
							String sql5="select uuid from app_user where part=? and schoolname=? and realname=?";
							String courseid="";
							String schoolid="";
							String sectionid="";
							String teacherid="";
							pstmt = (PreparedStatement) conn.prepareStatement(sql1);
							pstmt.setString(1, coursename);
							ResultSet result1=pstmt.executeQuery();
							while(result1.next()){
								courseid=result1.getString("uuid");
							}
							pstmt = (PreparedStatement) conn.prepareStatement(sql2);
							pstmt.setString(1, schoolname);
							ResultSet result2=pstmt.executeQuery();
							while(result2.next()){
								schoolid=result2.getString("uuid");
							}
							pstmt = (PreparedStatement) conn.prepareStatement(sql3);
							pstmt.setString(1, sectionname);
							ResultSet result3=pstmt.executeQuery();
							while(result3.next()){
								sectionid=result3.getString("uuid");
							}
							pstmt = (PreparedStatement) conn.prepareStatement(sql5);
							pstmt.setString(1,"T");
							pstmt.setString(2, schoolname);
							pstmt.setString(3,teachername);
							ResultSet result5=pstmt.executeQuery();
							while(result5.next()){
								teacherid=result5.getString("uuid");
							}
							//检测id是否存在
							if("".equals(courseid)||"".equals(schoolid)||"".equals(sectionid)||"".equals(teacherid)){
								throw new Exception("学校，课程,教师或者章节的id木有找到");
							}
							//答案选项的个数
							int optionLength=r.getLastCellNum()-8;
							//正确答案，替换分隔符并比较填写的答案是否超范围
							String an="";
							if(rightanswer.contains(",")){
								an=rightanswer.replaceAll(",", "-");
							}else if(rightanswer.contains("，")){
								an=rightanswer.replaceAll("，", "-");
							}else{
								an=rightanswer;
							}
							
							String[] ans=an.split("-");
							for(String s:ans){
								if(Integer.valueOf(s)>optionLength){
									addColor(r, style);
									throw new Exception("正确答案的"+s+"是哪来的");
								}
							}
							
							
							if(courseid!=null&&schoolid!=null&&sectionid!=null&&teacherid!=null){
								String sql4="insert into app_quiz (uuid,schoolid,schoolname,courseid,coursename,sectionid,sectionname,content,answer,rightanswer,createtime,issingle,level,teacherid,teachername) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//								String[] arr={schoolname,coursename,sectionname};
								pstmt = (PreparedStatement) conn.prepareStatement(sql4);
								pstmt.setString(1,com.cmit.cycle.core.Utils.createUUID());
								pstmt.setString(2,schoolid);
								pstmt.setString(3, schoolname);
								pstmt.setString(4,courseid);
								pstmt.setString(5, coursename);
								pstmt.setString(6, sectionid);
								pstmt.setString(7, sectionname);
								pstmt.setString(8,content);
								pstmt.setString(9, answerString.toString());
								pstmt.setString(10, an);
								pstmt.setTimestamp(11,new Timestamp(System.currentTimeMillis()));
								if(ans.length>1){
									pstmt.setInt(12,0);
								}else{
									pstmt.setInt(12,1);
								}
								pstmt.setInt(13,Integer.valueOf(level));
								pstmt.setString(14,teacherid);
								pstmt.setString(15,teachername);
								pstmt.executeUpdate();
							}
						}
					}else{
						addColor(row,style);
						throw new Exception("标题不对");
					}
				}
				
				conn.commit();
				System.out.println("==========恭喜恭喜，导进去了===========");
			} catch (Exception e) {
				e.printStackTrace();
				try {
					//捕获异常事务回滚
					conn.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}finally{
				try {
					conn.setAutoCommit(true);
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static Connection getConn() {
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://127.0.0.1:5432/appservice";
		String username = "postgres";
		String password = "100101";
		Connection conn = null;
		try {
			Class.forName(driver); // classLoader,加载对应驱动
			conn = (Connection) DriverManager.getConnection(url, username,
					password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	private static void addColor(Row r,CellStyle style){
		for(int x=0;x<r.getLastCellNum();x++){
			r.getCell(x).setCellStyle(style);
		}
	}
	
	private boolean isEmpty(String[] arr){
		boolean flag=true;
		for(String s:arr){
			if("".equals(s)||s==null){
				flag=false;
			}
		}
		return flag;
	}
	
}
