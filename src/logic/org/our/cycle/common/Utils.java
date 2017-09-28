package org.our.cycle.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
public class Utils {
	private static Utils utils=null;
	private static SqlSessionFactory sqlSessionFactory = null;   
    
	private Utils(){
		
	}
	
	public static Utils instance(){
		if(utils==null){
			utils=new Utils();
		}
		return utils;
	}
	
    static {   
        String resource = "mybatis.property";    
           
        Reader reader = null;   
        try {   
            reader = Resources.getResourceAsReader(resource);   
        } catch (IOException e) {   
            e.printStackTrace();   
        }          
           
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);   
    }   
       
    public static SqlSessionFactory getSqlSessionFactory(){   
        return sqlSessionFactory;   
    }
    
    public static Date getUTC(){
    	java.util.Calendar cal = java.util.Calendar.getInstance();   
    	//2、取得时间偏移量：   
    	int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);   
    	//3、取得夏令时差：   
    	int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);   
    	//4、从本地时间里扣除这些差量，即可以取得UTC时间：   
    	cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));   
    	//之后调用cal.get(int x)或cal.getTimeInMillis()方法所取得的时间即是UTC标准时间。   
    	System.out.println("UTC:"+new Date(cal.getTimeInMillis()));  
    	return new Date(cal.getTimeInMillis());
    }

    public static String formatDate(Date d) {
    	if(d!=null){
    		return (new SimpleDateFormat("yyyy-MM-dd")).format(d);
    	}else{
    		return "";
    	}
    }
    
    public static String formatDateTime(Date d) {
    	if(d!=null){
    		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(d);
    	}else{
    		return "";
    	}
    }
    
    public static void saveXML(Document doc,File file) throws IOException{
    	XMLWriter writer = null;
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        writer = new XMLWriter( new FileOutputStream(file), format);
        writer.write( doc );
        writer.flush();
        writer.close(); 
    }
}
