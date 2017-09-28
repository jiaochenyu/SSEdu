<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<%-- <jsp:include page="../header.jsp"></jsp:include> --%>
<style type="text/css">
#container {
	margin: 16% auto;
}
#loginbox {
	width: 350px;
	height: auto;
	margin: 0 auto;
	padding: 15px 10px;
	position: relative;
	/* overflow: hidden !important; */
	-webkit-box-shadow: 0 0 8px #B9D8F6 inset;
	-moz-box-shadow: 0 0 8px #B9D8F6 inset;
	box-shadow: 0 0 8px #B9D8F6 inset;
	border: 1px solid #344E99;
}
#loginbox .normal_text {
	font-size: 12px;
	color: #213568;
	/* padding-top: 4px; */
	font-family: Verdana;
}
#loginbox .failed {
	font-size: 12px;
	color: #FF0000;
	font-family: Verdana;
}
#loginbox .title {
	font-size: 12px;
	color: #000;
	padding-top: 4px;
	font-weight: bold;
	font-family: Geneva,Arial,Helvetica,sans-serif;
}
#loginbox .input {
	height: 21px;
	width: 200px;
	padding: 0px 2px;
	margin: 0px;
	border: 1px solid #A9A9A9;
}
#loginbox .submit {
	height: 24px;
	width: 50px;
	font-size: 12px;
	cursor: hand;
	color: #333333;
	font-family: Verdana;
}
#loginbox .reset {
	height: 24px;
	width: 50px;
	font-size: 12px;
	cursor: hand;
	color: #333333;
	font-family: Verdana;
}
</style>
<script type="text/javascript">
function onloadwin(){
	if(window.top!=window.self){
		window.top.location.href="sys-index.cyc";
	}
}
</script>
</head>
<body onload="onloadwin()">
<div id="container">
<div id="loginbox">
<form id="login_form" name="login_form" method="post" action="sys-login.cyc">
<table style="margin:0 auto;height:auto;">
<tbody>
<tr>
	<td colspan="2" style="text-align:center;">
	<div class="normal_text"><span>森盛科技app后台管理系统</span></div>
	</td>
</tr>
<c:if test="${not empty error}">
<tr>
	<td colspan="2" align="center" valign="middle" class="info_text">
	<span class="failed">操作失败... 请检查你的用户名和密码</span>
	</td>
</tr>
</c:if>
<tr>
	<td><span class="title">用户名:</span></td>
	<td><input type="text" id="username" name="username" value="${username}" maxlength="50" class="input" /></td>
</tr>
<tr>
	<td><span class="title">密    码:</span></td>
	<td><input type="password" id="password" name="password" value="" maxlength="50" class="input" /></td>
</tr>
<tr>
	<td colspan="2" style="text-align:center;">
	<input type="submit" id="login" class="submit" value="登录" maxlength="50" />
	<input type="reset" id="reset" class="reset" value="重置" maxlength="50" />
	</td>
</tr>
</tbody>
</table>
</form>
</div>
</div>
</body>
</html>