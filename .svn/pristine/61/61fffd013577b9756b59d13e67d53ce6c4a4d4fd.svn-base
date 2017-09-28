package org.our.cycle.common;

import com.cmit.cycle.core.CycleLogic;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;


public class UseVelocityTemplate implements CycleLogic{

	private static final String DI_TEMPLATENAME = "TemplateName";
	private static final String DO_TEMPLATEOUTPUTSTRING = "TemplateOutputString";

	public int invoker(Dictionary dictionary){
		StringWriter sw=null;
		try {
			Velocity.init((Properties)dictionary.get("velo.config"));
			String templateName=(String)dictionary.get(DI_TEMPLATENAME);
			VelocityContext v_context = new VelocityContext();
			if(!dictionary.isEmpty()){
				Enumeration e= dictionary.keys();
				while(e.hasMoreElements()){
					String key=(String)e.nextElement();
					v_context.put(key, dictionary.get(key));
				}
			}
			Template template = Velocity.getTemplate(templateName);
			sw = new StringWriter();
			template.merge(v_context, sw);
			sw.flush();
			dictionary.put(DO_TEMPLATEOUTPUTSTRING,sw.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(sw!=null)sw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return CycleLogic.NEXT;
	}

}