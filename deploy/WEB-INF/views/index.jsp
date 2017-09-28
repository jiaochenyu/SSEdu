<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>森盛科技app后台管理</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<!-- <link rel="stylesheet" type="text/css" href="easyui/themes/bootstrap/easyui.css"> -->
	<!-- <link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css"> -->
	<link rel="stylesheet" type="text/css" href="easyui/themes/color.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/locale/easyui-lang-en.js"></script>
	<style type="text/css">

	.logostr{
		font-family: Helvetica,Verdana,Geneva,Arial,Simsun; 
		/*font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;*/
		font-size: 18px;
		color: #fff;
		font-weight:bold;
		padding-left:5px;
	}

	.menu-pane{
		width:200px;height:auto;padding-left:20px;padding-top:5px;
	}
	.menu-item{
		padding:0px;margin:0px;
	}


	.tree-folder{
		display:inline-block;
		background:url('images/right.gif') no-repeat 4px 5px;
		width:16px;
		height:18px;
		vertical-align:bottom;
	}
	.tree-folder-open{
		background:url('images/down.gif') no-repeat 4px 6px;
		width:16px;
		height:18px;
		vertical-align:bottom;
	}

	.tree-file{
		display:inline-block;
		background:url('images/envvar_obj.gif') no-repeat 1px 2px;
		width:16px;
		height:18px;
		vertical-align:bottom;
	}

	.home_icon{
		background:url('images/brkp_type.gif') no-repeat;
	}
	.header{
		background:url('images/bg.jpg') repeat-x;
	}
	.mylink{
		text-decoration:none;
		color:#000000;
	}
	.mylin{
		text-decoration:none;
		color:#fff;
	}

	td,div {
		font-size: 12px;
	}

	.inputCtrl {
		border: 1px solid #6699cb;
	}

	.n {
		border-top: 1px solid #6699cb;
	}

	.e {
		border-right: 1px solid #6699cb;
	}

	.s {
		border-bottom: 1px solid #6699cb;
	}

	.w {
		border-left: 1px solid #6699cb;
	}

	.left {
		text-align: left;
	}

	.center {
		text-align: center;
	}

	.right {
		text-align: right;
	}

	.baseline {
		vertical-align: baseline;
	}

	.top {
		vertical-align: top;
	}

	.middle {
		vertical-align: middle;
	}

	.bottom {
		vertical-align: bottom;
	}

	.star {
		color: #FF0000;
	}

	form {
		margin: 0px;
		padding: 0px;
	}

	input {
		font-size: 12px;
	}

	textarea {
		font-size: 12px;
	}
	.trs1{background:#efefef;}
	.trs2{background:#ffffff;}
	.trsh{background:#aac5e7;}
	.table_header{
		background:#aac5e7;
		padding:5px;
		font-weight: bold;
	}
	.table_detail{
		padding:3px;
	}
	.treeHeader {
		font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
		font-size: 12px;
		color: #336699;
	}
	div,li,ul{
		font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
		font-size: 12px;
		/*color: #336699;*/
	}
	</style>
	<script type="text/javascript">
	function openWithWin(tit,url,param,w,h){
		$('#w').window({
			title: tit,
			modal: true,
			collapsible: false,
			minimizable: false,
			maximizable: false,
			shadow: false,
			closed: false,
			width:w,
			height:h
		});
		$('#w').window('center');
		getReloadURL(url,param,"popWin");
	}
	function closeWindow(){
		$('#w').window('close');
	}

	function getReloadURL(urlstr,param,pane){
		showLoading(pane);
		$.ajaxSetup({cache:false});
		$.ajax({
			type: "POST",
			url: urlstr,
			data: param,
			dataType: "text",
			beforeSend: function(XMLHttpRequest){
				//beforeLoading();
				return true;
			},
			success: function(responseData, textStatus){
				$("#"+pane).html(responseData);
				//document.getElementById(pane).innerHTML=responseData;
				$.parser.parse('#w');
			},
			complete: function(XMLHttpRequest, textStatus){
				//hideLoading();
			},
			error: function(){
				//errorLoading();
			}
		});
	}
	function getFormData(formId){
		var fd = $("#"+formId).serialize();
		return fd;
	}
	function showLoading(pane){
		document.getElementById(pane).innerHTML=document.getElementById("loadding").innerHTML;
	}

	function itemNodeJump(itemstr){
		document.getElementById("mframe").src=itemstr;
	}
	$(document).ready(function(){
		$('#ttmenu').tree({
			animate:true,
			lines:true,
			url:"main-loadmenus.cyc?parentid=0",
			/*onBeforeLoad:function(node,param){
				return true;
			},*/
			onBeforeExpand:function(node){
				$('#ttmenu').tree('options').url="main-loadmenus.cyc?parentid="+node.id;
				return true;
			},
			onClick:function(node){
				$(this).tree('toggle', node.target);
				//checknode(node.id);
				if(node.attributes.url!="#" && node.attributes.url!=""){
					checknode(node.id);
				}
			}
		});
	});
	function checknode(menuid){
		$.ajax({
			type: "POST",
			url: "main-urldata.cyc?menuid="+menuid,
			data: "",
			dataType: "text",
			beforeSend: function(XMLHttpRequest){
				$("<div class=\"datagrid-mask\" style=\"display:block\"></div>").css({width:"100%",height:"100%"}).appendTo($(document.body));
				return true;
			},
			success: function(responseData, textStatus){
				if(/^(null|undefined|#)$/.test(responseData)){
					document.getElementById("mframe").src="";
				} else {
					document.getElementById("mframe").src=responseData;
				}
			},
			complete: function(XMLHttpRequest, textStatus){
				$(document.body).children("div.datagrid-mask").remove();
			},
			error: function(){
				document.getElementById("mframe").src="";
			}
		});
	}
	function editPwdWin(){
		openWithWin('Modify password','main-toeditpwd.cyc','',500,260);
	}
	function savePwdWin(){
		var res = $('#login_users_form').form('validate');
		if(res){
			var pwd = $('#password').val();
			var repwd = $('#repassword').val();
			if(pwd != repwd){
				alert("The twice importations of new passwords are inconsistent.");
				$('#repassword').focus();
				return false;
			}
			var param = $('#login_users_form').serialize();
			$.post("main-savepwd.cyc",param,function(data){
				if(data=='true'){
					alert("Action Succeeded");
					closeWindow();
				}else if(data=='false'){
					alert("Action Failed");
				}else if(data=='password-not-equals'){
					alert("Action Failed\nOld password is not correct.");
				}
			},"text");
		}
	}
	function openNewWin(){
		window.top.open("main-index.cyc", "");
	}

	window.setInterval(function(){keepSession();}, 1000*60*5);

	function keepSession(){
		$.ajaxSetup({cache:false});
		var parma="n="+Math.random();
		$.ajax({
			type: "POST",
			url: "session-keep.cyc",
			data: parma,
			dataType: "text",
			beforeSend: function(XMLHttpRequest){
				//alert("url="+parma);
				return true;
			},
			success: function(responseData, textStatus){
				//alert(responseData);
			},
			complete: function(XMLHttpRequest, textStatus){
				//hideLoading();
			},
			error: function(){
				//errorLoading();
			}
		});
	}
	</script>
</head>
<body style="padding:0px;margin:0px;" class="easyui-layout">
	<div data-options="region:'north'" style="overflow:hidden;border:0px;height:40px;padding-top:8px;background:#495B79">
		<span class="logostr">森盛科技app后台管理</span>
		<div style="float:right;font-size:12px;color:#fff;text-align:right;padding:4px 8px 0px 0px">
		<span></span>
		<span></span>
		<span><a href="javascript:void(0)" onclick="editPwdWin()" title="Modify password" class="mylin">${username}</a></span>
		<span> | </span><span><a href="sys-logout.cyc" title="Logout" class="mylin">退出</a></span>
		</div>
	</div>
	<!-- <div data-options="region:'west',split:true,iconCls:'home_icon'" title="System Navigation" style="padding:3px;margin:0px;width:240px">
	<div style="height:16px;padding-left:10px;padding-top:2px">
		<div class="treeHeader" style="margin-left:9px"><span>System Navigation</span></div>
		<ul id="ttmenu" class="easyui-tree" style="color:#336699;"></ul>
	</div>
	</div> -->
	<div data-options="region:'west',split:true" style="padding:3px;margin:0px;width:240px">
	<div style="height:16px;padding-left:10px;padding-top:2px" class="home_icon">
		<div class="treeHeader" style="margin-left:9px"><span>导航</span></div>
		<ul id="ttmenu" class="easyui-tree" style="color:#336699;"></ul>
	</div>
	</div>
	<div data-options="region:'center'" style="background:#fff;border-top:0px;overflow:hidden">
		<iframe id="mframe" name="mframe" width="100%" height="100%" frameborder="0" style="border:none" src="about:blank"></iframe>
	</div>
	<!-- <div data-options="region:'center'" style="background:#fff;border-top:0px;overflow:hidden">
	<iframe id="mframe" name="mframe" frameborder="0" style="width:100%;height:100%;border:none" src="about:blank"></iframe>
	</div> -->

	<div data-options="closed:true,modal:true" id="w" class="easyui-window" title="My Window" style="width:750px;height:400px;padding:5px">
		<div data-options="fit:true" class="easyui-layout">
			<div data-options="region:'center'" id="popWin" style="padding:10px;background:#fff;border:1px solid #ccc">
			</div>
		</div>
	</div>
	<div id="loadding" style="display:none"><img alt="" src="images/panel_loading.gif"/></div>
</body>
</html>