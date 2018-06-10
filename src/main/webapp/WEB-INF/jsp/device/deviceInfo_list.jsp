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
    <link href="css/sbxxcx.css" rel="stylesheet" type="text/css">
    <link href="css/page.css" rel="stylesheet" type="text/css">
    <script src="js/jquery.js"></script>
    <script src="js/sbck.js" ></script>
    <script src="js/cascading.js" ></script>
    <script type="text/javascript">
        $(function () {
            $.ajax({
                type:"post",
                url:"data/queryDevType",
                dataType: "JSON",
                success:function (data) {
                    for(var o in data.data.devObject){
                        $("#dev_object").append("<option value="+data.data.devObject[o].data_value+">"+data.data.devObject[o].data_name+"</option>");
                    }
                    for(var type in data.data.devType){
                        $("#dev_type").append("<option value="+data.data.devType[type].data_value+">"+data.data.devType[type].data_name+"</option>");
                    }
                }
            });
        });
    </script>
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
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;站点信息查询</a></li>
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;站点配置管理</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="a_1"><img src="img/xt3.png">&nbsp;监测设备管理</a>
                    <ul class="zdgl" id="c2">
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;设备信息管理</a></li>
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;设备状态查看</a></li>
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;设备信息查询</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="a_1"><img src="img/xt3.png">&nbsp;监测数据管理</a>
                    <ul class="zdgl" id="c3">
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;实时数据查看</a></li>
                        <li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;历史数据查询功能</a></li>
                    </ul>
                </li>
                <!--<li>-->
                <!--<a href="#" class="a_1"><img src="img/xt3.png">&nbsp;系统管理</a>-->
                <!--<ul class="zdgl" id="c4">-->
                <!--<li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;用户管理</a></li>-->
                <!--<li><a href="#" class="a_2"><img src="img/xxtb.jpg">&nbsp;权限管理</a></li>-->
                <!--</ul>-->
                <!--</li>-->
            </ul>
        </div>
    </ul>
</div>

<div id="content_r">
    <li class="tit"><p class="xx"><img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span> > <span id="zb2">站点信息</span> > <span id="zb3">站点配置管理</span></p></li>
    <div class="menu">
        <form action="queryDeviceInfoList" method="post">
            <span>监测站名称：<input name="ms_name" type="text"/></span>
            <span>设备类型： <select class="select1" name="dev_type" id="dev_type">
                <option value="">设备类型</option>

            </select></span>
            <br/>
            <span>监测站编码：
                <select id="s_city" name="city">
                    <option value="">市区</option>
                </select>
                <select id="s_area" name="city" >
                    <option value="">区县</option>
                </select>
            </span>
            <span>监测对象：
                <select class="select1" name="dev_object" id="dev_object">
                    <option value="">监测对象</option>

                </select>
            </span>
            <br/>
            <input type="submit" value="查找" class="search"><input type="reset" value="重置" class="reset">

        </form>
    </div>
    <span class="title">查询列表</span>
    <table>
        <thead>
        <tr>
            <td class="t2">监测站名称</td>
            <td class="t3">监测站编码</td>
            <td class="t4">设备类型</td>
            <td class="t5">设备采集数据</td>
            <td class="t6">图片</td>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${list eq null}">
                <tr><td colspan="8" style="text-align: center;"><font color="red" size="4">${message }</font> </td></tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="list" items="${list}">
                    <tr>
                        <td class="t2">${list.station.ms_name}</td>
                        <td class="t3">${list.ms_code}</td>
                        <td class="t4">${list.dev_code}</td>
                        <td class="t5">设备采集数据</td>
                        <td class="t6"><input type="button" value="查看" onclick="check(this)"/></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
    <jsp:include page="../common/pages.jsp"></jsp:include>
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