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
    <%--<meta charset="UTF-8">--%>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
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
        function look(obj) {
            $("#img").show();
            $.ajax({
                type:"post",
                url:"device/photo",
                dataType: "JSON",
                success:function (data) {
                    for(var o in data.data){
                        $("#imga").append("<img class='min' src='/upload/"+data.data[o]+"'/>");
                    }
                }
            });
        }
        // $(function () {
        //     console.log("aaa");
        //     $("img.min").click(function(){
        //         console.log("aaa");
        //         $(this).toggleClass("min");
        //         $(this).toggleClass("max");
        //     })
        // })
    </script>
</head>

<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<div id="content_r">
    <li class="tit"><p class="xx"><img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span> > <span id="zb2">监测设备管理</span> > <span id="zb3">设备信息查询</span></p></li>
    <div class="menu">
        <form action="device/queryDeviceInfoList" method="post">
            <%--<span>监测站名称：<input name="ms_name" type="text"/></span>--%>
           <span>设备区域：</span>
                <select id="s_city" name="city" style="margin-left: 0.5%;">
                    <option value="">市区</option>
                </select>
                <select id="s_area" name="city" style="margin-left: 0.5%;">
                    <option value="">区县</option>
                </select>
            <span>设备类型： </span>
	            <select class="select1" name="dev_type" id="dev_type">
	                <option value="">不限</option>
	            </select>
            <span style="margin-left: 4.1%;">监测对象：</span>
                <select class="select1" name="dev_object" id="dev_object">
                    <option value="">不限</option>
                </select>
            <c:if test="${device.controller_ms_code != null and device.controller_ms_code != ''}">
                <input type="hidden" name="controller_ms_code" value="${device.controller_ms_code }">
            </c:if>
            <input type="submit" value="查找" class="search"><input type="reset" value="重置" class="reset">

        </form>
    </div>
    <%--<span class="title">查询列表</span>--%>
    <table>
        <thead>
        <c:choose>
            <c:when test="${device.controller_ms_code == null or device.controller_ms_code == ''}">
                <tr>
                    <td class="t2">监测站名称</td>
                    <td class="t3">监测站编码</td>
                    <td class="t5">查看监测站下设备</td>
                </tr>
            </c:when>
            <c:otherwise>
	            <tr>
		            <td class="t2">监测站名称</td>
		            <td class="t3">监测站编码</td>
		            <td class="t4">设备类型</td>
		            <td class="t5">设备采集数据</td>
	           </tr>
            </c:otherwise>
        </c:choose>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${list eq null}">
                <tr><td colspan="8" style="text-align: center;"><font color="red" size="4">${message }</font> </td></tr>
            </c:when>
            <c:when test="${device.controller_ms_code == null or device.controller_ms_code == ''}">
                <c:forEach var="list" items="${list}">
                    <tr>
                        <td class="t2">${list.ms_code_value}</td>
                        <td class="t3">${list.ms_code}</td>
                        <td class="t5">
                            <form action="device/queryDeviceInfoList" method="post">
                               <input type="hidden" name="controller_ms_code" value="${list.ms_code}">
                               <input type="submit" value="查询">
                            </form>
                        <%-- <a href="device/queryDeviceInfoList?controller_ms_code=${list.ms_code}" class="input1">查询</a> --%>
                        </td>
                    </tr>
                </c:forEach>            
            </c:when>
            <c:otherwise>
                <c:forEach var="list" items="${list}">
                    <tr>
                        <td class="t2">${list.station.ms_name}</td>
                        <td class="t3">${list.ms_code}</td>
                        <td class="t4">${list.dev_value}</td>
                        <td class="t5">
                            <form action="realData/loadByMsCodeAndDevCode" method="post">
                                <input type="hidden" name="ms_code" value="${list.ms_code}">
                                <input type="hidden" name="dev_code" value="${list.dev_code}">
                                <input type="submit" value="查询">
                            </form>
                        </td>
                        <%-- <td class="t5">
                            <a href="realData/loadByMsCodeAndDevCode?ms_code=${list.ms_code}&dev_code=${list.dev_code}" class="input1">查询</a>
                        </td> --%>
                        <%--<td class="t6"><input type="button" value="查看" onclick="look(this)"/></td>--%>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
    <jsp:include page="../common/pages.jsp"></jsp:include>
    <%--<div id="img" style="overflow-x: scroll;overflow-x: hidden;">
        <div id="imga"></div>
        <div id="clo">
            <input type="button" value="关闭" onclick="clos()"/>
        </div>
    </div>--%>
</div>
<div id="footer">
    <li>山东省植物保护总站</li>
</div>
</body>
</html>