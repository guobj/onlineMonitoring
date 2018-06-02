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
<head>
    <base href="${basePath }">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />    
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<meta name="format-detection" content="telephone=yes"/>
<meta name="msapplication-tap-highlight" content="no" />

<title>登陆</title>
<link href="css/index.css" type="text/css" rel="stylesheet">
</head>

<body>
<img src="img/denglu/title.png" id="tit">

<div id="content">
	<div class="tit">
    	<p>登陆界面</p>
    </div>
    
    <div class="tou">
    	<form action="" method="">
        	<p>用户名：<input type="text" id="name"></p>
            <p>密<span>码</span>：<input type="password" id="pw"></p>
            <input type="submit" value="登陆" class="btn">
            <input type="reset" value="重置" class="btn" style="margin-left:30px;">
        </form>
    </div>
	
	

</div>
</body>
</html>
