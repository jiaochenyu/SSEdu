<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="com.gsys.common.BaseAuthUtils" %>
<% BaseAuthUtils utils = new BaseAuthUtils(session); %>
<!DOCTYPE html>
<html>
<head>
<title>Roles - Add User</title>
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
		url:'admin-loadroleusers.cyc',
		queryParams:{
			"roleid":"${bean.uuid}",
			"loginid":$("#q_loginid").val()
		},
		//frozenColumns:[[
		//]],
		columns:[[
			<% if(utils.display("btn_role_add_user_setting")){ %>
			{field:'action',title:'Actions',width:80,align:'center',
				formatter:function(value,row,index){
					if((/^(undefined|null)?$/).test(row.uuid)){
						var e = '<a href="#" onclick="editrow('+index+')"><input type="checkbox" name="box" value="" /></a>';
						return e;
					} else {
						var c = '<a href="#" onclick="cancelrow('+index+')"><input type="checkbox" name="box" value="" checked="checked" /></a>';
						return c;
					}
				}
			},<% } %>
			{field:'loginid',title:'Login ID',width:200},
			{field:'displayname',title:'Display Name',width:260},
			{field:'email',title:'Email',width:260}
		]],
		onLoadSuccess:function(data){
		}
	});
});
function doSearch(){
	$('#tableinfo').datagrid('load',{
		"roleid":"${bean.uuid}",
		"loginid":$("#q_loginid").val()
	});
}
function editrow(index){
	//var row = $('#tableinfo').datagrid('getSelected');
	$('#tableinfo').datagrid('selectRow', index);
	var row = $('#tableinfo').datagrid('getRows')[index];
	if(row){
		var param = "roleid=${bean.uuid}&userid="+row.userid;
		ajaxSendRequest('admin-saveroleusers.cyc',param,'edit');
	}
}
function cancelrow(index){
	//var row = $('#tableinfo').datagrid('getSelected');
	$('#tableinfo').datagrid('selectRow', index);
	var row = $('#tableinfo').datagrid('getRows')[index];
	if(row){
		var param = "roleid=${bean.uuid}&userid="+row.userid;
		ajaxSendRequest('admin-deleteroleusers.cyc',param,'cancel');
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
				$.messager.show({
					title:'Information',
					msg:'Action Succeeded.',
					timeout:5000,
					showType:'slide'
				});
				//alert("Action Succeeded");
				$('#tableinfo').datagrid('reload');
			}else if(responseData=='false'){
				//alert("Action Failed");
				$('#tableinfo').datagrid('reload');
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
function goBackWin(){
	window.location.href="admin-roles.cyc";
}
</script>
</head>
<body style="margin:0px;padding:0px">
<div class="breadcrumb">
<ul>
<li><span>Systems</span></li><li><a href="admin-roles.cyc">Roles</a></li><li><span>[${bean.rolename}] - Add User</span></li>
</ul>
</div>
<div style="margin:0px 8px">
<form id="query_form" name="query_form" method="post">
<table style="border:none">
<tbody>
<tr>
	<td><span>Login ID:</span></td>
	<td><input type="text" id="q_loginid" name="loginid" value="" style="width:260px" /></td>
	<td><a href="javascript:void(0)" class="easyui-linkbutton" onclick="doSearch()">Search</a></td>
</tr>
<tr>
</tr>
</tbody>
</table>
</form>
<table id="tableinfo"></table>
<div style="margin:8px 0px">
<a href="javascript:void(0)" class="easyui-linkbutton" onclick="goBackWin()">Go Back</a>
</div>
</div>
</body>
</html>