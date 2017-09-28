<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<title>试题管理</title>
<jsp:include page="../header.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	$('#tableinfo').datagrid({
		title:'题目管理',
		fit:true,
		nowrap:true,
		collapsible:false,
		rownumbers:true,
		singleSelect:false,
		striped:true,
		autoRowHeight:false,
		pagination:true,
		url:'app-loadquiz.cyc',
		footer:'#footer',
		columns:[[
			{field:'uuid',checkbox:true},
			{field:'schoolname',title:'学校名称'},
			{field:'coursename',title:'学科名称'},
			{field:'sectionname',title:'章节名称'},
			{field:'content',title:'题干',width:200},
			{field:'answer',title:'答案选项',width:200},
			{field:'rightanswer',title:'正确答案'},
			{field:'issingle',title:'题目类型',align:'center',
				formatter:function(value,row,index){
					if(value == 0){
						return "多选";
					}else{
						return "单选";
					}
				}
			},
			{field:'imgpath',title:'图片路径'},
			{field:'imgstate',title:'图片状态'},
			{field:'remarks',title:'做题解析'},
			{field:'times',title:'题目所需时间'}
		]]
	});
});
</script>
</head>
<body style="margin:0px;padding:0px">
	<table id="tableinfo"></table>
	<div id="footer" style="padding:5px">
		<a href="#" class="easyui-linkbutton" style="width:80px" onclick="top.openWithWin('导入试题','app-inImpQuiz.cyc','',320,300);">导入试题</a>  
	</div>
</body>
</html>