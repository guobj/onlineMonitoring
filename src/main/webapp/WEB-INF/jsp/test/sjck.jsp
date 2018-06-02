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
    <title></title>
    <script src="js/jquery.js"></script>
    <link href="css/sjck.css" rel="stylesheet" type="text/css">
    <script src="js/sjck.js" type="text/javascript"></script>

</head>

<body>
<div id="header">
    <div class="tit">
        山东省病虫害物联网在线监测管理系统
    </div>
    <ul class="three">
        <a href="#"><img src="img/fz.png">&nbsp;返回首页</a>
        <a href="#"><img src="img/bg2.png">&nbsp;个人信息</a>
        <a href="#"><img src="img/gb.png">&nbsp;退出系统</a>
    </ul>
</div>

<div id="content_l">
    <a class="a1" id="a1" href="#">
        <li><img class="i1" src="img/sjgl.jpg">数据管理<img src="img/xia.png" id="i2" ></li></a>
    <ul class="b1" id="b2" >
        <li><a href="#">预测预警</a></li>
        <li><a href="#">报警规则设置</a></li>
        <li> <a href="#">报警方式设置</a></li>
    </ul>
    <a class="a1" href="#" id="a2"><li><img class="i1" src="img/ss.jpg">实时数据查看<img src="img/xia.png" id="i2"></li></a>
    <ul class="b1" id="b1" >
        <a href="#">预测预警</a>
        <a href="#">报警规则设置</a>
        <a href="#">报警方式设置</a>
    </ul>
    <a class="a1" href="#"><li><img class="i1" src="img/ls.jpg">历史数据查看<img src="img/xia.png" id="i2"></li></a>
    <a class="a1" href="#"><li><img class="i1" src="img/sj.jpg">数据分析<img src="img/xia.png" id="i2"></li></a>
</div>

<div id="content_r">
    <li class="tit"><p class="xx"><img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span> > <span id="zb2">数据管理</span> > <span id="zb3">实时数据查询</span></p></li>
    <form action="" method="">
        <input type="text" class="search" placeholder="搜索" />
        <input type="submit" value="确定" class="submit"/>
    </form>

    <table>
        <thead class="name">
        <tr >
            <td class="t1">监测站名称</td>
            <td class="t2">监测站编码</td>
            <td class="t3">设备类型</td>
            <td class="t4">监测对象</td>
            <td class="t5">采集时间</td>
            <td class="t6">监测数据</td>
            <td class="t7">监测图片</td>
            <td class="t9">操作</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="t1">3</td>
            <td class="t2">4</td>
            <td class="t3">5</td>
            <td class="t4">6</td>
            <td class="t5">7</td>
            <td class="t6">8</td>
            <td class="t7"><a href="#">查 看</a></td>
            <td class="t9"><form method="" action=""><input type="button" value="修改" style="margin-right: 8%;" id="update"> <input id="del" type="button" value="删除"></form></td>
        </tr>
        <tr>
            <td class="t1">3</td>
            <td class="t2">4</td>
            <td class="t3">5</td>
            <td class="t4">6</td>
            <td class="t5">7</td>
            <td class="t6">8</td>
            <td class="t7"><a href="#">查 看</a></td>
            <td class="t9"><form method="" action=""><input type="button" value="修改" style="margin-right: 8%;" id="update"> <input id="del" type="button" value="删除"></form></td>
        </tr>
        <tr>
            <td class="t1">3</td>
            <td class="t2">4</td>
            <td class="t3">5</td>
            <td class="t4">6</td>
            <td class="t5">7</td>
            <td class="t6">8</td>
            <td class="t7"><a href="#">查 看</a></td>
            <td class="t9"><form method="" action=""><input type="button" value="修改" style="margin-right: 8%;" id="update"> <input id="del" type="button" value="删除"></form></td>
        </tr>
        <tr>
            <td class="t1">3</td>
            <td class="t2">4</td>
            <td class="t3">5</td>
            <td class="t4">6</td>
            <td class="t5">7</td>
            <td class="t6">8</td>
            <td class="t7"><a href="#">查 看</a></td>
            <td class="t9"><form method="" action=""><input type="button" value="修改" style="margin-right: 8%;" id="update"> <input id="del" type="button" value="删除"></form></td>
        </tr>
        <tr>
            <td class="t1">3</td>
            <td class="t2">4</td>
            <td class="t3">5</td>
            <td class="t4">6</td>
            <td class="t5">7</td>
            <td class="t6">8</td>
            <td class="t7"><a href="#">查 看</a></td>
            <td class="t9"><form method="" action=""><input type="button" value="修改" style="margin-right: 8%;" id="update"> <input id="del" type="button" value="删除"></form></td>
        </tr>
        <tr>
            <td class="t1">3</td>
            <td class="t2">4</td>
            <td class="t3">5</td>
            <td class="t4">6</td>
            <td class="t5">7</td>
            <td class="t6">8</td>
            <td class="t7"><a href="#">查 看</a></td>
            <td class="t9"><form method="" action=""><input type="button" value="修改" style="margin-right: 8%;" id="update"> <input id="del" type="button" value="删除"></form></td>
        </tr>
        <tr>
            <td class="t1">3</td>
            <td class="t2">4</td>
            <td class="t3">5</td>
            <td class="t4">6</td>
            <td class="t5">7</td>
            <td class="t6">8</td>
            <td class="t7"><a href="#">查 看</a></td>
            <td class="t9"><form method="" action=""><input type="button" value="修改" style="margin-right: 8%;" id="update"> <input id="del" type="button" value="删除"></form></td>
        </tr>

        </tbody>
    </table>

    <div class="but">
        <input type="button" value="首页" class="but1" onclick="sy()">
        <input type="button" value="上一页" class="but2" onclick="syy()">
        <li class="but3" id="but1">1</li>
        <input type="button" value="下一页" class="but2" onclick="xyy()">
        <input type="button" value="尾页" class="but1" style="margin-left:5px;" onclick="xyy">
        共<span id="c1"></span>页，<span id="c2"></span>条数据,转到<input type="text" class="but1" style="float:right; margin:0px;">
    </div>

</div>
</body>
</html>
