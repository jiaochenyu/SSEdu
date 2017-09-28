<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.gsys.common.BaseAuthUtils" %>
<% BaseAuthUtils utils = new BaseAuthUtils(session); %>
<!DOCTYPE html>
<html>
<head>
<title>Privileges</title>
<jsp:include page="../header.jsp"></jsp:include>
<style type="text/css">
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
.itemlink {
	color: #000;
	text-decoration: none;
}
.itemlink:hover {
	text-decoration: underline;
}
.checkbox {
	_vertical-align:-1px;
	*vertical-align:-1px;
	vertical-align:-2px;
}
.radio {
	_vertical-align:-1px;
	*vertical-align:-1px;
	vertical-align:-2px;
}
</style>
<script type="text/javascript">
function convert(rows){
	function exists(rows, parentId){
		for(var i=0; i<rows.length; i++){
			if (rows[i].id == parentId){ return true; }
		}
		return false;
	}
	
	var nodes = [];
	// get the top level nodes
	for(var i=0; i<rows.length; i++){
		var row = rows[i];
		if (!exists(rows, row.parentId)){
			nodes.push({
				id:row.id,
				text:row.name,
				attributes:{uri:row.uri},
				checked:(row.checked!="")?true:false
			});
		}
	}
	
	var toDo = [];
	for(var i=0; i<nodes.length; i++){
		toDo.push(nodes[i]);
	}
	while(toDo.length){
		var node = toDo.shift();	// the parent node
		// get the children nodes
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			if (row.parentId == node.id){
				var child = {
					id:row.id,
					text:row.name,
					attributes:{uri:row.uri},
					checked:(row.checked!="")?true:false
				};
				if (node.children){
					node.children.push(child);
				} else {
					node.children = [child];
				}
				toDo.push(child);
			}
		}
	}
	return nodes;
}

$(document).ready(function(){
	$('#tt').tree({
		dnd:false,
		animate:true,
		lines:true,
		checkbox:true,
		url:"admin-loadmenus.cyc?roleid=${bean.uuid}",
		loadFilter:function(rows){
			return convert(rows);
		},
		//onLoadSuccess:function(node,data){
		//},
		onClick:function(node){
			if(node.checked==true){
				$('#tt').tree('uncheck', node.target);
			}else{
				$('#tt').tree('check', node.target);
			}
		}
	});
	$('#tabMain').tabs({
		onSelect:function(title,index){
			if(title=="Buttons"){
				var pp = $('#tabMain').tabs('getSelected');
				pp.panel({
					onLoad:function(){
						$("#power_button_table input[name='buttonid']").change();
					}
				});
			}
		}
	});
});
function ajaxSendRequest(urlstr,param,type){
	 $.ajax({
		type: "POST",
		//async: false,
		url: urlstr,
		data: param,
		dataType: "text",
		beforeSend: function(XMLHttpRequest){
			//beforeLoading();
			$("<div class=\"datagrid-mask\" style=\"display:block\"></div>").css({width:"100%",height:"100%"}).appendTo($(document.body));
			var msg=$("<div class=\"datagrid-mask-msg\" style=\"display:block;left:50%\"></div>").html("Processing, please wait ... ").appendTo($(document.body));
			msg.outerHeight(40);
			msg.css({marginLeft:(-msg.outerWidth()/2),lineHeight:(msg.height()+"px")});
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
				if(type=='save'){
					$('#tt').tree('reload');
				}else if(type=='apply'){
					var pp = $('#tabMain').tabs('getSelected');
					pp.panel('refresh');
				}
			}else if(responseData=='false'){
				alert("Action Failed");
			}
		},
		complete: function(XMLHttpRequest, textStatus){
			//hideLoading();
			$(document.body).children("div.datagrid-mask-msg").remove();
			$(document.body).children("div.datagrid-mask").remove();
		},
		error: function(){
			//errorLoading();
		}
	});
}
function applyChecked(){
	//var nodes = $('#tt').tree('getChecked');
	var nodes = $('#tt').tree('getChecked',['checked','indeterminate']);
	var box = [];
	box.push("roleid=${bean.uuid}");
	box.push("type=m");
	for(var i=0; i<nodes.length; i++){
		var node = nodes[i];
		box.push("authid="+node.id);
	}
	ajaxSendRequest('admin-applymenus.cyc',box.join("&"),'save');
}
function checkedAll(checked){
	var nodes = $('#tt').tree('getRoots');
	if(checked==true){
		for(var i=0; i<nodes.length; i++){
			var node = nodes[i];
			$('#tt').tree('check', node.target);
		}
	}else{
		for(var i=0; i<nodes.length; i++){
			var node = nodes[i];
			$('#tt').tree('uncheck', node.target);
		}
	}
}
function displaytab(url,tstr,index){
	if($('#tabMain').tabs('exists',index)){
		//$('#tabMain').tabs('enableTab', index);
		$('#tabMain').tabs('select', index);
	}else{
		$('#tabMain').tabs('add',{
			title:tstr,
			href:url,
			selected:true,
			closable:false,
			cache:false,
			onSelect:function(title,index){
				if(title=="Buttons"){
					var pp = $('#tabMain').tabs('getSelected');
					pp.panel({
						onLoad:function(){
							$("#power_button_table input[name='buttonid']").change();
						}
					});
				}
			},
			onLoad:function(panel){
			}
		});
		//$('#tabMain').tabs('disableTab', 0);
	}
}
function showtabPages(){
	//var nodes = $('#tt').tree('getChecked');
	var nodes = $('#tt').tree('getChecked',['checked','indeterminate']);
	var box = [];
	box.push("roleid=${bean.uuid}");
	box.push("type=m");
	for(var i=0; i<nodes.length; i++){
		var node = nodes[i];
		box.push("authid="+node.id);
	}
	$.post("admin-applymenus.cyc",box.join("&"),function(data){
		if(data=='true'){
			displaytab("admin-pages.cyc?roleid=${bean.uuid}","Pages",1);
		}else if(data=='false'){
			alert("Action Failed");
		}
	},"text");
	//displaytab("admin-pages.cyc?roleid=${bean.uuid}","Pages",1);
}
function showtabButtons(){
	var param = $("#power_page_form").serialize();
	$.post("admin-applypages.cyc",param,function(data){
		if(data=='true'){
			displaytab("admin-buttons.cyc?roleid=${bean.uuid}","Buttons",2);
		}else if(data=='false'){
			alert("Action Failed");
		}
	},"text");
	//displaytab("admin-buttons.cyc?roleid=${bean.uuid}","Buttons",2);
}
function checkedBoxAll(checked,elementId){
	var element = document.getElementById(elementId);
	if(checked==true){
		$(element).find("input:checkbox").each(function(index, domEle){
			$(domEle).get(0).checked=true;
		});
	}else{
		$(element).find("input:checkbox").each(function(index, domEle){
			$(domEle).get(0).checked=false;
		});
	}
}
function applyCheckedPages(){
	var param = $("#power_page_form").serialize();
	ajaxSendRequest('admin-applypages.cyc',param,'apply');
}
function applyCheckedButtons(){
	var param = $("#power_button_form").serialize();
	ajaxSendRequest('admin-applybuttons.cyc',param,'apply');
}
function changeBoxAll(elem,style){
	if($(elem).get(0).checked==true){
		$("#power_button_table input:checkbox"+style).each(function(index, domEle){
			$(domEle).get(0).checked=true;
		});
	}else{
		$("#power_button_table input:checkbox"+style).each(function(index, domEle){
			$(domEle).get(0).checked=false;
		});
	}
}
function changeBoxOne(boxId,style){
	var total = $("#power_button_table input:checkbox"+style).length;
	var len = $("#power_button_table input:checkbox:checked"+style).length;
	if(total>0 && len==total){
		document.getElementById(boxId).checked=true;
	}else{
		document.getElementById(boxId).checked=false;
	}
}
</script>
</head>
<body style="margin:0px;padding:0px">
<div class="breadcrumb">
<ul>
<li><span>Systems</span></li><li><span>[${bean.rolename}] - Privileges</span></li>
</ul>
</div>
<div style="margin:8px">
<div id="tabMain" class="easyui-tabs" style="">
	<div title="Menus" style="padding:10px">
		<ul id="tt" class="easyui-tree"></ul>
		<div style="margin:8px 0px">
		<% if(utils.display("btn_power_menu_apply")){ %>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="checkedAll(true)">Select All</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="checkedAll(false)">Unselect All</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="applyChecked()">Apply</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="showtabPages()">Go Next</a>
		<% } %>
		</div>
	</div>
	<div title="Pages" data-options="href:'admin-pages.cyc?roleid=${bean.uuid}',cache:false"></div>
	<div title="Buttons" data-options="href:'admin-buttons.cyc?roleid=${bean.uuid}',cache:false"></div>
</div>
</div>
</body>
</html>