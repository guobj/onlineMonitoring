<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实时数据</title>
<script src="js/jquery.js"></script>
    <link href="css/page.css" rel="stylesheet" type="text/css">
    <link href="css/sjck.css" rel="stylesheet" type="text/css">
    <script src="js/pageNav.js" type="text/javascript"></script>
    <script src="js/sjck.js" type="text/javascript"></script>
    <script src="js/jilian.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(function(){
        city();
        $("#s_city").change(function city(){
            var city_id = $(this).val();
            $("#s_area").html("<option value=''>地级市</option>");
            $.post("dict/listArea",{city_id:city_id},function(data){
                if(data!=null&&data.length>0){
                    for(var i=0;i<data.length;i++){
                        $("#s_area").append("<option value="+data[i].data_value+">"+data[i].data_name+"</option>");
                    }
                }
            });
        });
    })
    function city(){
        $.post("dict/listCity",function(data){
            if(data!=null&&data.length>0){
                for(var i=0;i<data.length;i++){
                    $("#s_city").append("<option value="+data[i].data_value+">"+data[i].data_name+"</option>");
                }
            }
        })
    }
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
    <li class="tit"><p class="xx"><img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span> > <span id="zb2">数据管理</span> > <span id="zb3">实时数据查询</span></p></li>
    <div class="menu">
        <form action="realData/listRealData" method="post">
            <span>设备类型：
	            <select class="select1" name="device_type">
	                <option value="">不限</option>
	                <c:forEach items="${devType1 }" var="type1">
	                    <c:choose>
	                        <c:when test="${type1.data_value < 10 }">
	                            <option value="00${type1.data_value }">${type1.data_name }</option>
	                        </c:when>
	                        <c:when test="${type1.data_value < 100 }">
                                <option value="0${type1.data_value }">${type1.data_name }</option>
                            </c:when>
	                        <c:otherwise>
	                            <option value="${type1.data_value }">${type1.data_name }</option>
	                        </c:otherwise>
	                    </c:choose>
	                </c:forEach>
	            </select>
            </span>
             <span>设备状态：
                <select class="select1" name="dev_stauts">
                    <option value="">不限</option>
                    <c:forEach items="${devStauts }" var="stauts">
                        <option value="${stauts.data_value }">${stauts.data_name }</option>
                    </c:forEach>
                </select>
            </span>
            <br/>
            <span>监测站编码：
                <select id="s_city" name="city">
                    <option value="">市区</option>
                </select>
                <select id="s_area" name="city" >
                    <option value="">地级市</option>
                </select>
               
            </span>
            <span>监测对象：
                <select class="select1" name="device_object">
                    <option value="">不限</option>
                    <c:forEach items="${devType }" var="type">
                        <option value="${type.data_value }">${type.data_name }</option>
                    </c:forEach>
                </select>
            </span>

            <br/>
            <input type="submit" value="查找" class="search"><input type="reset" value="重置" class="reset">

        </form>

    </div>
    <table>
        <thead class="name">
        <tr>
            <td class="t2">监测站编码</td>
            <td class="t3">监测设备编码</td>
            <td class="t5">采集时间</td>
            <td class="t6">数据查看</td>
        </tr>
        </thead>
        <tbody>
	        <c:forEach items="${listRealData }" var="real">
		        <tr style="border-bottom: 1px solid #adadad;">
		            <td class="t2">${real.ms_code }</td>
		            <td class="t3">${real.dev_code }</td>
		            <td class="t5"><fmt:formatDate value="${real.data_time }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>  </td>
		            <td class="t6">${real.data_value }<!-- <input type="button" value="查看" onclick="look()"/> --></td>
		        </tr>
	        </c:forEach>
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
