<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="margin:0px">
<form id="sys_roles_form" name="sys_roles_form" method="post">
<input type="hidden" id="uuid" name="uuid" value="${bean.uuid}" />
<table id="sys_roles_table" style="">
<tr>
	<td>Role Name:<span class="star">*</span></td>
	<td>
	<input type="text" id="rolename" name="rolename" value="${bean.rolename}" style="width:200px;" class="easyui-validatebox" data-options="required:true,missingMessage:'Role Name is Required.'" />
	</td>
</tr>
<tr>
	<td>Role Memo:</td>
	<td>
	<input type="text" id="rolememo" name="rolememo" value="${bean.rolememo}" style="width:260px;" />
	</td>
</tr>
<tr>
	<td></td>
	<td>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="window.frames[0].saveInfo()">Apply</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="window.frames[0].saveCancel()">Cancel</a>
	</td>
</tr>
</table>
</form>
</div>