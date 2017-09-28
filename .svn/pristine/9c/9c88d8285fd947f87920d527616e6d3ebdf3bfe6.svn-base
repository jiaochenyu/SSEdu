<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.gsys.common.BaseAuthUtils" %>
<% BaseAuthUtils utils = new BaseAuthUtils(session); %>
<div style="padding:10px">
<form id="power_page_form" name="power_page_form" method="post">
<input type="hidden" name="roleid" value="${param.roleid}" />
<input type="hidden" name="type" value="p" />
<table id="power_page_table" style="width:100%;line-height:20px;">
<c:forEach items="${pageList}" var="p">
<tr>
	<td style="font-weight:bold;">
	<label>
	<input type="checkbox" name="pageid" value="${p.pageid}" class="checkbox"<c:if test="${not empty p.checked}"> checked="checked"</c:if> /><span>${p.pagename}</span>
	</label>
	</td>
</tr>
</c:forEach>
</table>
</form>
<div style="margin:8px 0px">
<% if(utils.display("btn_power_page_apply")){ %>
<a href="javascript:void(0)" class="easyui-linkbutton" onclick="checkedBoxAll(true,'power_page_table')">Select All</a>
<a href="javascript:void(0)" class="easyui-linkbutton" onclick="checkedBoxAll(false,'power_page_table')">Unselect All</a>
<a href="javascript:void(0)" class="easyui-linkbutton" onclick="applyCheckedPages()">Apply</a>
<a href="javascript:void(0)" class="easyui-linkbutton" onclick="showtabButtons()">Go Next</a>
<% } %>
</div>
</div>