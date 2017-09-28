<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="margin:0px">
<form id="sys_users_form" name="sys_users_form" method="post">
<input type="hidden" id="uuid" name="uuid" value="${param.uuid}" />
<table id="sys_users_table" style="">
<tr>
	<td>New Password:<span class="star">*</span></td>
	<td>
	<input type="password" id="password" name="password" value="" style="width:200px" class="easyui-validatebox" data-options="required:true,missingMessage:'New Password is Required.'" />
	</td>
</tr>
<!--<tr>
	<td>Confirmed Password:<span class="star">*</span></td>
	<td>
	<input type="password" id="repassword" name="repassword" value="" style="width:200px" class="easyui-validatebox" data-options="required:true,missingMessage:'Confirmed Password is Required.'" />
	</td>
</tr>-->
<tr>
	<td></td>
	<td>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="window.frames[0].savePwdInfo()">Apply</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="window.frames[0].saveCancel()">Cancel</a>
	</td>
</tr>
</table>
</form>
</div>