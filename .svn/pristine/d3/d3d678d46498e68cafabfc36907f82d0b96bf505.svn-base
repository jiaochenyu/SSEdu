<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.gsys.common.BaseAuthUtils" %>
<% BaseAuthUtils utils = new BaseAuthUtils(session); %>
<div style="padding:10px">
<form id="power_button_form" name="power_button_form" method="post">
<input type="hidden" name="roleid" value="${param.roleid}" />
<input type="hidden" name="type" value="b" />
<table id="power_button_table" style="width:100%;line-height:20px;">
<c:forEach items="${pageList}" var="p" varStatus="r">
<c:if test="${not empty buttonsMap[p.pageid]}">
<tr>
	<td colspan="2" style="font-weight:bold;">
	<label>
	<input type="checkbox" id="groupbox${r.count}" name="groupbox" value="${p.pageid}" class="checkbox"
	 onchange="changeBoxAll(this,'.box${r.count}')" /><span>${p.pagename}</span>
	</label>
	</td>
</tr>
<tr>
	<td></td>
	<td>
	<div style="line-height:20px;">
	<c:forEach items="${buttonsMap[p.pageid]}" var="item">
	<%-- <input type="hidden" name="menuid" value="${item.menuid}" />
	<input type="hidden" name="pageid" value="${item.pageid}" /> --%>
	<label style="margin-left:10px;">
	<input type="checkbox" name="buttonid" value="${item.buttonid}" class="checkbox box${r.count}"<c:if test="${not empty item.checked}"> checked="checked"</c:if>
	 onchange="changeBoxOne('groupbox${r.count}','.box${r.count}')" /><span>${item.buttonname}</span>
	</label>
	</c:forEach>
	</div>
	</td>
</tr>
</c:if>
</c:forEach>
</table>
</form>
<div style="margin:8px 0px">
<% if(utils.display("btn_power_button_apply")){ %>
<a href="javascript:void(0)" class="easyui-linkbutton" onclick="checkedBoxAll(true,'power_button_table')">Select All</a>
<a href="javascript:void(0)" class="easyui-linkbutton" onclick="checkedBoxAll(false,'power_button_table')">Unselect All</a>
<a href="javascript:void(0)" class="easyui-linkbutton" onclick="applyCheckedButtons()">Apply</a>
<% } %>
</div>
</div>