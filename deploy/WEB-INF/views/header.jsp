<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cach">
<meta http-equiv="cache-control" content="no-cache, must-revalidate">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/color.css">
<link rel="stylesheet" type="text/css" href="style/main.css">
<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
function setCheckBoxAll(itemId,boxname){
	$("#"+itemId+" input:checkbox[name='"+boxname+"']").prop("checked",true);
}
function setCheckBoxNone(itemId,boxname){
	$("#"+itemId+" input:checkbox[name='"+boxname+"']").prop("checked",false);
}

</script>
<style type="text/css">
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

.n2 {
	border-top: 2px solid #000000;
}

.e2 {
	border-right: 2px solid #000000;
}

.s2 {
	border-bottom: 2px solid #000000;
}

.w2 {
	border-left: 2px solid #000000;
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

.tree-folder {
	display: inline-block;
	background: url('images/right.gif') no-repeat 4px 5px;
	width: 16px;
	height: 18px;
	vertical-align: bottom;
}

.tree-folder-open {
	background: url('images/down.gif') no-repeat 4px 6px;
	width: 16px;
	height: 18px;
	vertical-align: bottom;
}

.tree-file {
	display: inline-block;
	background: url('images/envvar_obj.gif') no-repeat 1px 3px;
	width: 16px;
	height: 18px;
	vertical-align: bottom;
}

.home_icon {
	background: url('images/brkp_type.gif') no-repeat;
}

#header{
	background-image: url('images/h_1.jpg');
	height:30px;
}
#header a{
	text-decoration: none;
	border: none;
	color: #FFFFFF;
}
#navi{
	background-image: url('images/h_2.jpg');
	height:67px;
	/*background-position:0 -2;*/
	background-repeat: repeat;
}
#navi a{
	text-decoration: none;
	border: none;
	color: #FFFFFF;
}
#logo{
	font-size: 20px;color: #FFFFFF;padding-left:5px; font-weight:bold;
	font-family:  Helvetica,Arial,Geneva,sans-serif;
}
.nav_class{
	font-size: 12px;
	font-weight: bold;
	font-family:sans-serif,宋体;
}
#foot{
	background:#ACBBC5;
	font-size: 12px;
	color: #000000;
	text-align:center;
	font-family: Geneva, Arial, Helvetica,sans-serif;
	height:25px;
	/*padding: 10px 0px;*/
}
/*
html,body{
	padding:0px;
	margin:0px;
}*/

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

.tabletd2{
	font-size:12px;
	padding:3px;
}
.odd2{
	background: #fafafa;
}
</style>
