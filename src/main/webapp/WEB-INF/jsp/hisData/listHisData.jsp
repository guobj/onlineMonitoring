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
    <link href="css/xinxi.css" rel="stylesheet" type=text/css>
    <link href="css/page.css" rel="stylesheet" type="text/css">
    <link href="css/sjck.css" rel="stylesheet" type="text/css">
    <script src="js/pageNav.js" type="text/javascript"></script>
    <script src="js/sjck.js" type="text/javascript"></script>
    <script src="js/jilian.js" type="text/javascript"></script>
    <script src="js/cascading.js"  type="text/javascript"></script>
    <script type="text/javascript">
        var account = ${sessionScope.user.account};
    </script>
</head>

<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<div id="content_r">
    <li class="tit"><p class="xx"><img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span> > <span id="zb2">数据管理</span> > <span id="zb3">实时数据查询</span></p></li>
    <div class="menu">
        <form action="realData/listRealData" method="post">
            <span>设备类型：</span>
	            <select class="select1" name="device_type">
	                <option value="">不限</option>
	                <c:forEach items="${devType }" var="type">
	                    <c:choose>
	                        <c:when test="${type.data_value < 10 }">
	                            <option value="00${type.data_value }">${type.data_name }</option>
	                        </c:when>
	                        <c:when test="${type.data_value < 100 }">
                                <option value="0${type.data_value }">${type.data_name }</option>
                            </c:when>
	                        <c:otherwise>
	                            <option value="${type.data_value }">${type.data_name }</option>
	                        </c:otherwise>
	                    </c:choose>
	                </c:forEach>
	            </select>
            
             <span>设备状态：</span>
                <select class="select1" name="dev_status">
                    <option value="">不限</option>
                    <c:forEach items="${devStauts }" var="status">
                        <option value="${status.data_value }">${status.data_name }</option>
                    </c:forEach>
                </select>
            
            
            <span>监测站编码： </span>
                <select id="s_city" name="city">
                    <option value="">市区</option>
                </select>
                <select id="s_area" name="city" >
                    <option value="">地级市</option>
                </select>
               
           <br/>
            <span>监测对象：</span>
                <select class="select1" name="device_object">
                    <option value="">不限</option>
                    <c:forEach items="${devObject }" var="ob">
                        <option value="${ob.data_value }">${ob.data_name }</option>
                    </c:forEach>
                </select>

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
            <c:if test="${listHisData eq null}" >
                <tr><td colspan="8" style="text-align: center;"><font color="red" size="4">${message }</font> </td></tr>
            </c:if>
            <c:if test="${listHisData != null}" >
		        <c:forEach items="${listHisData }" var="his">
			        <tr style="border-bottom: 1px solid #adadad;">
			            <td class="t2">${his.ms_code }</td>
			            <td class="t3">${his.dev_code_value }</td>
			            <td class="t5"><fmt:formatDate value="${his.data_time }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>  </td>
			            <td class="t6">${his.data_value }<!-- <input type="button" value="查看" onclick="look()"/> --></td>
			        </tr>
		        </c:forEach>
	        </c:if>
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
