<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function succ(){
		top.closeWindow();
		mframe.dosearch();
	}
</script>
<c:if test="${param.status=='true'}">
	<table style="width: 100%;height: 100%;">
        <tr>
            <td>导入成功！</td>
        </tr>
        <tr><td></td></tr>
        <tr>
            <td align="right">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="top.closeWindow()" style="width: 60px;">关闭</a>
            </td>
        </tr>
    </table>
</c:if>
<c:if test="${param.status=='false'}">
    <table style="width: 100%;height: 100%;">
        <tr>
            <td>导入失败</td>
        </tr>
        <tr><td></td></tr>
        <tr>
            <td align="right">
                <a href="app-downloadQuizerrorfile.cyc" class="easyui-linkbutton">下载错误文件</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="top.closeWindow()" style="width: 60px;">取消</a>
            </td>
        </tr>
    </table>
</c:if>