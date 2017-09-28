<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="margin:0px">
<form id="login_users_form" name="login_users_form" method="post">
<table id="login_users_table" style="">
<tr>
	<td>Login ID:<span class="star">*</span></td>
	<td><strong>${username}</strong></td>
</tr>
<tr>
	<td>Old Password:<span class="star">*</span></td>
	<td>
	<input type="password" id="oldpassword" name="oldpassword" value="" style="width:200px" class="easyui-validatebox" data-options="required:true,missingMessage:'Old Password is Required.'" />
	</td>
</tr>
<tr>
	<td>New Password:<span class="star">*</span></td>
	<td>
	<input type="password" id="password" name="password" value="" style="width:200px" class="easyui-validatebox" data-options="required:true,missingMessage:'New Password is Required.'" />
	</td>
</tr>
<tr>
	<td>Confirmed New Password:<span class="star">*</span></td>
	<td>
	<input type="password" id="repassword" name="repassword" value="" style="width:200px" class="easyui-validatebox" data-options="required:true,missingMessage:'Confirmed New Password is Required.'" />
	</td>
</tr>
<tr>
	<td></td>
	<td>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="savePwdWin()">Apply</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow()">Cancel</a>
	</td>
</tr>
</table>
</form>
</div>