<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Privileges</title>
<jsp:include page="../header.jsp"></jsp:include>
<style type="text/css">
.itemlink {
	color: #000;
	text-decoration: none;
}
.itemlink:hover {
	text-decoration: underline;
}
</style>
<script type="text/javascript">
function showcontent(roleid){
	var urlstr = "admin-powerview.cyc?roleid="+roleid;
	document.getElementById("powerframe").src=urlstr;
}
</script>
</head>
<body style="margin:0px;padding:0px" class="easyui-layout">
<!-- <div class="breadcrumb">
<ul>
<li><span>Systems</span></li><li><span>Privileges</span></li>
</ul>
</div> -->
	<div data-options="region:'west',split:true" title="" style="width:200px;padding:5px;border-top:none;border-bottom:none;border-left:none;">
	<form id="query_form" name="query_form" method="post">
	<c:if test="${not empty rolesList}">
	<table style="border:none;width:100%;height:auto;line-height:20px;">
	<tbody>
	<c:forEach items="${rolesList}" var="bean">
	<tr>
		<td>
		<a href="javascript:void(0)" onclick="showcontent('${bean.uuid}')" class="itemlink">${bean.rolename}</a>
		</td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	</c:if>
	</form>
	</div>
	<div data-options="region:'center'" title="" style="background:#fff;border-top:0px;overflow:hidden">
	<iframe id="powerframe" name="powerframe" width="100%" height="100%" frameborder="0" style="border:none" src=""></iframe>
	</div>
</body>
</html>