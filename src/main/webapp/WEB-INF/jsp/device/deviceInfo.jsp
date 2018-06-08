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
    <meta charset="UTF-8">
    <title></title>
    <link href="css/sbxxgl.css" rel="stylesheet" type="text/css">
    <script src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.page.js"></script>
    <script src="js/sbck.js" ></script>
    <script type="text/javascript" src="js/jilian.js"></script>
</head>

<body>
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
<jsp:include page="../common/header.jsp"></jsp:include>

<div id="content_r">
    <li class="tit"><p class="xx"><img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span> > <span id="zb2">站点信息</span> > <span id="zb3">站点配置管理</span></p></li>
    <!--<div class="menu">-->
        <!--<form>-->
            <!--<span>监测站名称：<input type="text"/></span>-->
            <!--<span>设备类型： <select class="select1">-->
                <!--<option value="病害监测">病害监测</option>-->
                <!--<option value="害虫监测">害虫监测</option>-->
                <!--<option value="鼠情监测">鼠情监测</option>-->
                <!--<option value="环境因子监测">环境因子监测</option>-->
                <!--<option value="视频图像监测">视频图像监测</option>-->
                <!--<option value="其它监测">其他监测设备类型</option>-->
            <!--</select></span>-->
            <!--<br/>-->
            <!--<span>监测站编码：-->
                <!--<select id="s_province" name="s_province"></select>-->
                        <!--<select id="s_city" name="s_city" ></select>-->
                        <!--<script type="text/javascript">_init_area();</script>-->
            <!--</span>-->
            <!--<span>监测对象：-->
                <!--<select class="select1">-->
                    <!--<option value="病害监测">病害监测</option>-->
                    <!--<option value="害虫监测">害虫监测</option>-->
                    <!--<option value="杂草监测">杂草监测</option>-->
                    <!--<option value="鼠情监测">鼠情监测</option>-->
                    <!--<option value="环境因子监测">环境因子监测</option>-->
                    <!--<option value="视频图像监测">视频图像监测</option>-->
                    <!--<option value="其它监测">其它监测</option>-->
                <!--</select>-->
            <!--</span>-->
            <!--<br/>-->
            <!--<input type="button" value="查找" class="search"><input type="reset" value="重置" class="reset">-->

        <!--</form>-->
    <!--</div>-->
    <span class="title">信息列表</span>
    <table>
        <thead>
            <tr>
                <td class="t2" >监测站名称</td>
                <td class="t3" >监测站编码</td>
                <td class="t4">设备编码</td>
                <td class="t5">通信协议</td>
                <td class="t6">通信接口</td>
                <td class="t7">从机地址</td>
                <td class="t8">IP地址</td>
                <td class="t9">通信端口</td>
                <td class="t10">质保期限</td>
                <td class="t11">设备厂家、品牌、型号、服务电话</td>
                <td class="t12">设备描述</td>
            </tr>
        </thead>
        <tbody>
            <c:if test="${list eq null}">
                <tr><td colspan="11" style="text-align: center;"><font color="red" size="4">${message }</font> </td></tr>
            </c:if>
            <c:forEach var="list" items="${list}">
                <tr>
                    <td class="t2" >${list.ms_name}</td>
                    <td class="t3" >${list.ms_code}</td>
                    <td class="t4">${list.dev_code}</td>
                    <td class="t5">${list.dataProtocol.data_value}</td>
                    <td class="t6">${list.dataInterface.data_value}</td>
                    <td class="t7">${list.dev_regad}</td>
                    <td class="t8">${list.dev_ip}</td>
                    <td class="t9">${list.dataPort.data_name}</td>
                    <td class="t10">${list.dev_warraty}</td>
                    <td class="t11">${list.dev_mfrs}</td>
                    <td class="t12">${list.dev_desc}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div id="page"></div>
    <div id="img">
        <div id="imga"></div>
        <form>
            <input type="button" value="关闭" onclick="clos()"/>
        </form>
    </div>
</div>
<div id="footer">
    <li>济南农智信息科技有限公司所有&copy; &nbsp;电话：12345677  &nbsp;<a href="#">关于我们</a> &nbsp;<a href="#">售后服务</a></li>
</div>
</body>
</html>