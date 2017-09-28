<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="com.gsys.common.BaseAuthUtils" %>
<% BaseAuthUtils utils = new BaseAuthUtils(session); %>
<!DOCTYPE html>
<html>
<head>
<title>Roles</title>
<jsp:include page="../header.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	$('#tableinfo').datagrid({
		title:'Roles List',
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
		url:'admin-loadroles.cyc',
		queryParams:{
			"rolename":$("#q_rolename").val()
		},
		//frozenColumns:[[
		//]],
		columns:[[
			{field:'uuid',checkbox:true},
			<% if(utils.display("btn_role_add_user")){ %>
			{field:'action',title:'Actions',width:100,align:'center',
				formatter:function(value,row,index){
					var d = '<a href="javascript:void(0)" onclick="addRoleUsersWin('+index+')">Add User</a>';
					return d;
				}
			},<% } %>
			{field:'rolename',title:'Role Name',width:200,
				formatter:function(value,row,index){
					<% if(utils.display("btn_role_edit")){ %>
					var d = '<a href="javascript:void(0)" onclick="editRecordWin('+index+')">'+value+'</a>';
					return d;
					<% }else{ %>
					return value;
					<% } %>
				}
			},
			{field:'usernum',title:'Users',width:100},
			{field:'rolememo',title:'Memo',width:260}
		]],
		onLoadSuccess:function(data){
		}
	});
});
function doSearch(){
	$('#tableinfo').datagrid('load',{
		"rolename":$("#q_rolename").val()
	});
}
function addRoleUsersWin(index){
	//var row = $('#tableinfo').datagrid('getSelected');
	$('#tableinfo').datagrid('selectRow', index);
	var row = $('#tableinfo').datagrid('getRows')[index];
	if(row){
		var param = 'roleid='+row.uuid;
		window.location.href='admin-roleusers.cyc?'+param;
	}
}
function addRecordWin(){
	parent.openWithWin('Create New Role','admin-toeditrole.cyc','',500,200);
}
function editRecordWin(index){
	//var row = $('#tableinfo').datagrid('getSelected');
	$('#tableinfo').datagrid('selectRow', index);
	var row = $('#tableinfo').datagrid('getRows')[index];
	if(row){
		var param = 'uuid='+row.uuid;
		parent.openWithWin('Edit Role','admin-toeditrole.cyc',param,500,200);
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
			ids.push('uuid='+row.uuid);
		}
		ajaxSendRequest('admin-deleterole.cyc',ids.join('&'),'');
	}
}
function saveInfo(){
	var res = parent.$('#sys_roles_form').form('validate');
	if(res){
		var fd = parent.$('#sys_roles_form').serialize();
		ajaxSendRequest('admin-saverole.cyc',fd,'save');
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
<li><span>Systems</span></li><li><span>Roles</span></li>
</ul>
</div>
<div style="margin:0px 8px">
<form id="query_form" name="query_form" method="post">
<table style="border:none">
<tbody>
<tr>
	<td><span>Role Name:</span></td>
	<td><input type="text" id="q_rolename" name="rolename" value="" style="width:260px" /></td>
	<td><a href="javascript:void(0)" class="easyui-linkbutton" onclick="doSearch()">Search</a></td>
</tr>
<tr>
</tr>
</tbody>
</table>
</form>
<table id="tableinfo"></table>
<div style="margin:8px 0px">
<% if(utils.display("btn_role_new")){ %>
<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addRecordWin()">Create New Role</a>
<% } %>
<% if(utils.display("btn_role_delete")){ %>
<a href="javascript:void(0)" class="easyui-linkbutton" onclick="deleteRecordWin()">Delete</a>
<% } %>
</div>
</div>
</body>
</html>