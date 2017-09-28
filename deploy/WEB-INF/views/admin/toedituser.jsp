<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="margin:0px">
<form id="sys_users_form" name="sys_users_form" method="post">
<input type="hidden" id="uuid" name="uuid" value="${bean.uuid}" />
<input type="hidden" id="type" name="type" value="${bean.type}" />
<table id="sys_users_table" style="">
<tr>
	<td>Login ID:<span class="star">*</span></td>
	<td>
<c:choose>
<c:when test="${not empty bean.uuid}">
	<strong>${bean.loginid}</strong>
</c:when>
<c:otherwise>
	<input type="text" id="loginid" name="loginid" value="${bean.loginid}" style="width:200px" class="easyui-validatebox" data-options="required:true,missingMessage:'Login ID is Required.'" />
</c:otherwise>
</c:choose>
	</td>
</tr>
<tr>
	<td>Display Name:<span class="star">*</span></td>
	<td>
	<input type="text" id="displayname" name="displayname" value="${bean.displayname}" style="width:200px" class="easyui-validatebox" data-options="required:true,missingMessage:'Display Name is Required.'" />
	</td>
</tr>
<tr>
	<td>Email:<span class="star">*</span></td>
	<td>
	<input type="text" id="email" name="email" value="${bean.email}" style="width:200px" class="easyui-validatebox" data-options="required:true,missingMessage:'Email is Required.'" />
	</td>
</tr>
<c:if test="${empty bean.uuid}">
<tr>
	<td>Password:<span class="star">*</span></td>
	<td>
	<input type="password" id="password" name="password" value="${bean.password}" style="width:200px" class="easyui-validatebox" data-options="required:true,missingMessage:'Password is Required.'" />
	</td>
</tr>
</c:if>
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