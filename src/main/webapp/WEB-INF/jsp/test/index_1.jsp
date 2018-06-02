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
<meta http-equiv="X-UA-Compatible" content="chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1" />

<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />    
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<meta name="format-detection" content="telephone=yes"/>
<meta name="msapplication-tap-highlight" content="no" />

<title>山东省病虫害物联网检测系统</title>
<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<link href="css/index_1.css" type="text/css" rel="stylesheet">
    <script src="js/jquery.js"></script>
<script src="js/index_1.js" type="text/javascript" ></script>

</head>

<body onscroll="no">
	<div id="tit">
    	<p>山东省病虫害物联网监测系统</p>
        <ul>
        	<li style="width:8.7%;"><img src="img/yh.png"></li>
    		<li>欢迎您，Admin. 今天是<span id="year"></span>年<span id="month"></span>月<span id="dat"></span>日，星期<span id="day"></span>.</li>
            <li style=" margin-top:11px; width:100%; background:url(img/sbg.png) no-repeat; height:32px;">
            	<ul class="three">
                	<a href="#"><img src="img/fz.png">&nbsp;返回首页</a>
                    <a href="#"><img src="img/bg2.png">&nbsp;个人信息</a>
                    <a href="#"><img src="img/gb.png" style="margin-top:7px;">&nbsp;退出系统</a>
                </ul>
            </li>
    	</ul>
    </div>
    <div id="caidan">
    	<ul>
        	<li class="tit">
            	<img src="img/gl1.png">
                &nbsp;管理菜单
            </li>
            <li class="cdq">
            	<img src="img/bf.png">
                <a>功能菜单区</a>
            </li>
            <div class="cd_4">
            	<ul id="ceNav">
                    <li>
                        <a href="#" class="a_1"> <img src="img/xt3.png">&nbsp;站点管理</a>
                        <ul class="zdgl" id="c1">
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;站点修改</a></li>
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;网络设置</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#" class="a_1"><img src="img/xt3.png">&nbsp;数据管理</a>
                        <ul class="zdgl" id="c2">
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;实时数据查看</a></li>
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;历史数据查看</a></li>
                        </ul>
                    </li>        
                    <li>
                        <a href="#" class="a_1"><img src="img/xt3.png">&nbsp;虫害管理</a>
                        <ul class="zdgl" id="c3">
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;病虫害数据分析</a></li>
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;病虫害数据报表</a></li>
                        </ul>
                    </li>
                     <li>
                        <a href="#" class="a_1"><img src="img/xt3.png">&nbsp;系统管理</a>
                        <ul class="zdgl" id="c3">
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;用户管理</a></li>
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;权限管理</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </ul>
    </div>
    
    <div id="content">
    	<div class="tit3">
        	<div class="left">
                <p>气象信息</p>
            </div>
            <div class="right">
                <div class="img1">
                    <img src="img/dibiao.jpg">
                    <p>定位</p>
                </div>
                <div class="se">
                     <div class="sel">
                        <select id="s_province" name="s_province"></select> 
                        <select id="s_city" name="s_city" ></select> 
                        <script type="text/javascript">_init_area();</script>
                    </div>
                </div>
            </div>
        </div>
        <img src="img/dt.jpg" style="width:100%; height:90%;">
    </div>
    
    <div id="footer">
    	<li>济南农智信息科技有限公司所有&copy; &nbsp;电话：12345677  &nbsp;<a href="#">关于我们</a> &nbsp;<a href="#">售后服务</a></li>
    </div>
    
</body>
</html>
