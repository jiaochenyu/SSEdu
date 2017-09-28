<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="com.gsys.common.BaseAuthUtils" %>
<% BaseAuthUtils utils = new BaseAuthUtils(session); %>
<!DOCTYPE html>
<html>
<head>
<title>Users</title>
<jsp:include page="../header.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	$('#tableinfo').datagrid({
		title:'Users List',
		width:"100%",
		height:500,
		//height:$(window.top).height()*0.75,
		nowrap:true,
		//fitColumns:true,
		collapsible:false,
		rownumbers:true,
		singleSelect:false,
		striped:true,
		autoRowHeight:false,
		pagination:true,
		//pageNumber:1,
		//pageSize:10,
		url:'admin-loadusers.cyc',
		queryParams:{
			"text":$("#q_text").val()
		},
		//frozenColumns:[[
		//]],
		columns:[[
			{field:'uuid',checkbox:true},
			<% if(utils.display("btn_user_editpwd")){ %>
			{field:'action',title:'Actions',width:100,align:'center',
				formatter:function(value,row,index){
					var d = '<a href="javascript:void(0)" onclick="updatePwdWin('+index+')">Reset Password</a>';
					return d;
				}
			},<% } %>
			{field:'loginid',title:'Login ID',width:200,
				formatter:function(value,row,index){
					<% if(utils.display("btn_user_edit")){ %>
					var d = '<a href="javascript:void(0)" onclick="editRecordWin('+index+')">'+value+'</a>';
					return d;
					<% }else{ %>
					return value;
					<% } %>
				}
			},
			{field:'displayname',title:'Display Name',width:260},
			{field:'email',title:'Email',width:260}
		]],
		onLoadSuccess:function(data){
		}
	});
});
function doSearch(){
	$('#tableinfo').datagrid('load',{
		"text":$("#q_text").val()
	});
}
function updatePwdWin(index){
	//var row = $('#tableinfo').datagrid('getSelected');
	$('#tableinfo').datagrid('selectRow', index);
	var row = $('#tableinfo').datagrid('getRows')[index];
	if(row){
		var param = 'uuid='+row.uuid;
		parent.openWithWin('Reset Password','admin-toeditpwd.cyc',param,500,180);
	}
}
function addRecordWin(){
	parent.openWithWin('New Account','admin-toedituser.cyc','',500,260);
}
function editRecordWin(index){
	//var row = $('#tableinfo').datagrid('getSelected');
	$('#tableinfo').datagrid('selectRow', index);
	var row = $('#tableinfo').datagrid('getRows')[index];
	if(row){
		var param = 'uuid='+row.uuid;
		parent.openWithWin('Edit Account','admin-toedituser.cyc',param,500,230);
	}
}
function ajaxSendRequest(urlstr,param,type){
	 $.ajax({
		type: "POST",
		//async: false,
		url: urlstr,
		data: param,
		dataType: "text",
		beforeSend: function(XMLHttpRequest){
			//beforeLoading();
			return true;
		},
		success: function(responseData, textStatus){
			if(responseData=='true'){
				alert("Action Succeeded");
				$('#tableinfo').datagrid('reload');
				if(type=='save'){
					parent.closeWindow();
				}
			}else if(responseData=='false'){
				alert("Action Failed");
			}else if(responseData=='loginid-exist'){
				alert("Action Failed\nLogin ID already exists, do not repeat.");
			}
		},
		complete: function(XMLHttpRequest, textStatus){
			//hideLoading();
		},
		error: function(){
			//errorLoading();
		}
	});
}
function deleteRecordWin(){
	var rows = $('#tableinfo').datagrid('getSelections');
	if(rows.length==0){ return false; }
	if(window.confirm("Are you sure you want to do this?")){
		var ids = [];
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			//ids.push(row.uuid);
			if(row.loginid!="admin"){
				ids.push('uuid='+row.uuid);
			}
		}
		ajaxSendRequest('admin-deleteuser.cyc',ids.join('&'),'');
	}
}
function saveInfo(){
	var res = parent.$('#sys_users_form').form('validate');
	if(res){
		var fd = parent.$('#sys_users_form').serialize();
		ajaxSendRequest('admin-saveuser.cyc',fd,'save');
	}
}
function savePwdInfo(){
	var res = parent.$('#sys_users_form').form('validate');
	if(res){
		/*var pwd = parent.$('#password').val();
		var repwd = parent.$('#repassword').val();
		if(pwd != repwd){
			alert("The twice importations of passwords are inconsistent.");
			parent.$('#repassword').focus();
			return false;
		}*/
		var fd = parent.$('#sys_users_form').serialize();
		ajaxSendRequest('admin-savepwd.cyc',fd,'save');
	}
}
function saveCancel(){
	parent.closeWindow();
}
</script>
</head>
<body style="margin:0px;padding:0px">
<div class="breadcrumb">
<ul>
<li><span>Systems</span></li><li><span>Users</span></li>
</ul>
</div>
<div style="margin:0px 8px">
<form id="query_form" name="query_form" method="post">
<table style="border:none">
<tbody>
<tr>
	<td><span>Login ID or Email:</span></td>
	<td><input type="text" id="q_text" name="text" value="" style="width:260px" /></td>
	<td><a href="javascript:void(0)" class="easyui-linkbutton" onclick="doSearch()">Search</a></td>
</tr>
<tr>
</tr>
</tbody>
</table>
</form>
<table id="tableinfo"></table>
<div style="margin:8px 0px">
<% if(utils.display("btn_user_new")){ %>
<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addRecordWin()">New Account</a>
<% } %>
<% if(utils.display("btn_user_delete")){ %>
<a href="javascript:void(0)" class="easyui-linkbutton" onclick="deleteRecordWin()">Delete</a>
<% } %>
</div>
</div>
</body>
</html>