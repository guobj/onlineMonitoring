<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    pageContext.setAttribute("path", path);
    pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE HTML>
<html>
<head lang="en">
    <base href="${basePath }">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>登陆</title>
    <link href="css/index.css" type="text/css" rel="stylesheet">
</head>

<body> 
<img src="img/title.png" id="tit">

<div id="content">
    <div class="tit">
        <p>登陆界面</p>
    </div>
    <c:if test="${message!=null }">${message }</c:if>
    <div class="tou">
        <form action="loginDo" method="post">
            <p>用户名：<input type="text" id="name" name="account"></p>
            <p>密<span>码</span>：<input type="password" id="pw" name="password"></p>
            <input type="submit" value="登陆" class="btn">
            <input type="reset" value="重置" class="btn" style="margin-left:30px;">
        </form>
    </div>
    
    

</div>
<div id="footer">
        <p>山东省植物保护总站</p>
    </div>
</body>
</html>
