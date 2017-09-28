<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<form id="importFrom"  method="post" action="app-impQuiz.cyc" enctype="multipart/form-data" target="confirmdrc_box">
	
	<input type="hidden" id="schid" name="schoolid" />
	<input type="hidden" id="schname" name="schoolname" />
	<input type="hidden" id="courid" name="courseid" />
	<input type="hidden" id="courname" name="coursename" />
	<input type="hidden" id="teaid" name="teacherid" />
	<input type="hidden" id="teaname" name="teachername" />
	<table>
	<tr>
		<td>选择学校：</td>
		<td><input id="school" data-options="required:true" /></td>
	</tr>
	<tr>
		<td>选择学科：</td>
		<td><input id="course" data-options="required:true" class="easyui-combobox" /></td>
	</tr>
	<tr>
		<td>选择老师：</td>
		<td><input id="teacher" data-options="required:true" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input class="easyui-filebox" name="sourcefile" data-options="required:true,missingMessage:'必须选择文件',buttonText:'选择文件'" style="width:150px"></td>
	</tr>
	<tr>
		<td></td>
		<td>
			<a href="#" class="easyui-linkbutton" style="width:60px;" onclick="checkfile()" >导入文件</a>
			<a href="#" class="easyui-linkbutton" style="width:60px;" onclick="top.closeWindow();" >取消</a>
		</td>
	</tr>
</table>

</form>
<iframe id="confirmdrc_box" style="display:none;" name="confirmdrc_box">
</iframe> 
<script>
	$(function(){
		$('#school').combobox({    
		    url:'app-loadAppSchool.cyc',
		    valueField:'uuid',    
		    textField:'schoolname',
		    onSelect:function(record){
		    	$("#schid").val(record.uuid);
		    	$("#schname").val(record.schoolname);
		    	getCourse(record.uuid);
		    }
		});
		
		$('#teacher').combobox({    
		    url:'app-loadAppTeacher.cyc',
		    valueField:'uuid',    
		    textField:'realname',
		    onSelect:function(record){
		    	$("#teaid").val(record.uuid);
		    	$("#teaname").val(record.realname);
		    }
		});
	});
	
	
	function getCourse(schoolid){
    	$('#course').combobox(
    		{url:'app-loadAppCourse.cyc',    
   		    queryParams:{
   		    	schoolid:schoolid
   		    },
		    valueField:'uuid',
		    textField:'coursename',
		    onSelect:function(record){
		    	$("#courid").val(record.uuid);
		    	$("#courname").val(record.coursename);
		    }
		}); 
	}
	
	
	function checkfile(){
		if($("#importFrom").form('validate')){
			$("#importFrom").submit();
		}
	}
</script>