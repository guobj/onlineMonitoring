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
    <link href="css/sbzt.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script src="js/cascading.js" ></script>
    <link href="css/page.css" rel="stylesheet" type="text/css">
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
    <li class="tit"><p class="xx"><img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span> > <span id="zb2">监测设备管理</span> > <span id="zb3">设备状态查看</span></p></li>
    <div class="menu">
        <form action="vDevStatus/devcieStatusList" method="post">
            <%--<span>监测站名称：<input type="text"/></span>--%>
            <span>设备类型： </span>
                <select class="select1" name="dev_type" id="dev_type">
                    <option value="">设备类型</option>
                </select>
            <span>监测区域：</span>
                 <select id="s_city" name="city">
                    <option value="">市区</option>
                </select>
                <select id="s_area" name="city" >
                    <option value="">区县</option>
                </select>
            <span>监测对象：</span>
                <select class="select1" name="dev_object" id="dev_object">
                    <option value="">监测对象</option>
                </select>
            <input type="submit" value="查找" class="search"><input type="reset" value="重置" class="reset">

        </form>
    </div>
    <h5>信息列表</h5>
    <br/>
        <table>
            <thead>
            <tr>
            <td class="t2">监测站名称</td>
            <td class="t3">监测站编码</td>
            <td class="t4">设备类型</td>
            <td class="t5">监测对象</td>
            <td class="t6">设备状态</td>
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
                                <td class="t2">${list.ms_name}</td>
                                <td class="t3">${list.ms_code}</td>
                                <td class="t4">${list.dataDevType.data_name}</td>
                                <td class="t5">${list.dev_code}</td>
                                <td class="t6">${list.dataDevStatus.data_name}</td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    <jsp:include page="../common/pages.jsp"></jsp:include>
    </div>
    <div id="footer">
        <li>山东省植物保护总站</li>
    </div>
</body>
<script>
 $(function(){
    var height=$("#content_r").height();
    //日期
    var date=new Date();
    $("#year").html(date.getFullYear());
    $("#month").html(date.getMonth()+1);
    $("#dat").html(date.getDate());

    var day=date.getDay();

    if(day=="1"){
        $("#day").html("一");
    }else if((day=="2")){
        $("#day").html("二");
    }else if(day=="3"){
        $("#day").html("三");
    }
    else if(day=="4"){
        $("#day").html("四");
    }
    else if(day=="5"){
        $("#day").html("五");
    }
    else if(day=="6"){
        $("#day").html("六");
    }
    else if(day=="0"){
        $("#day").html("日");
    }
    //菜单
    $("#a_1").click(function(){
        $("#c1").toggle()
    })
    $("#a_2").click(function(){
        $("#c2").toggle()
    })
    $("#a_3").click(function(){
        $("#c3").toggle()
    })
    $("#a_4").click(function(){
        $("#c4").toggle()
    })


    $("#ceNav li a").click(function(){
        $(this).siblings().toggle();
    })
    $("#img").hide();
});


$(function(){
    $(" tbody tr").mouseover(function(){
        $(this).css("background","#fff4e5");
    })
    $(" tbody tr").mouseout(function(){
        $(this).css("background","#EEF4F9");
    })
})


</script>
</html>