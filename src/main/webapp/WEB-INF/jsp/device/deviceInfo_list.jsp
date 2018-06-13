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
        var account = ${sessionScope.user.account};
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
<jsp:include page="../common/header.jsp"></jsp:include>
<div id="content_r">
    <li class="tit"><p class="xx"><img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span> > <span id="zb2">站点信息</span> > <span id="zb3">站点配置管理</span></p></li>
    <div class="menu">
        <form action="device/queryDeviceInfoList" method="post">
            <%--<span>监测站名称：<input name="ms_name" type="text"/></span>--%>
            <span>设备类型： <select class="select1" name="dev_type" id="dev_type">
                <option value="">不限</option>

            </select></span>
            <span>监测站编码：
                <select id="s_city" name="city" style="margin-left: 0.5%;">
                    <option value="">市区</option>
                </select>
                <select id="s_area" name="city" style="margin-left: 0.5%;">
                    <option value="">区县</option>
                </select>
            </span>
            <span style="margin-left: 4.1%;">监测对象：
                <select class="select1" name="dev_object" id="dev_object">
                    <option value="">不限</option>

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
    <li>山东省植物保护总站</li>
</div>
</body>
</html>