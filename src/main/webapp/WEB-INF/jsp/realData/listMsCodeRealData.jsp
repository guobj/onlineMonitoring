<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<title>监测站各设备实时数据</title>
    <link href="css/zdsssjcx.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.page.js"></script>
    <script type="text/javascript" src="js/sssj.js"></script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<div id="content_r">
    <h5><span>${ms_code }</span>站点实时数据查看</h5>
    <div id="main">
    <c:if test="${listAll eq null}" >
         <p  style="margin-top:250px; color:#ff0000;font-size:40px; margin-left:40%; ">${message }</p> 
    </c:if>
    <c:if test="${listAll != null}" >
	    <c:forEach items="${listAll }" var="list">
	        <c:choose>
	            <c:when test="${fn:startsWith(list.dev_code,'dev5')}">
	                <div id="meeq">
			            <p>设备编号：<span>${list.dev_code }</span></p>
			            <p>设备类型：<span>${list.dev_code_value }</span></p>
			            <p>数据上报时间：<span><fmt:formatDate value="${list.data_time }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span></p>
			            <div class="con">
			                <p>空气温度：<span>${list.air_t }</span>℃</p>
			                <p>空气湿度：<span>${list.air_h }</span>%</p>
			                <p>露点温度：<span>${list.dew_p }</span>℃</p>
			                <p>日照时数：<span>${list.sunshine_h }</span>h</p>
			                <p>土壤温度1：<span>${list.soil_t1 }</span>℃</p>
			                <p>土壤温度2：<span>${list.soil_t2 }</span>℃</p>
			                <p>土壤温度3：<span>${list.soil_t3 }</span>℃</p>
			                <p>土壤湿度1：<span>${list.soil_h1 }</span>%</p>
			                <p>土壤湿度2：<span>${list.soil_h2 }</span>%</p>
			                <p>土壤湿度3：<span>${list.soil_h3 }</span>%</p>
			                <p>土壤EC值：<span>${list.soil_ec }</span></p>
			                <p>大气压：<span>${list.atmo_s }</span></p>
			                <p>降雨量：<span>${list.rain_f }</span>mm</p>
			                <p>风速：<span>${list.wind_s }</span>m/s</p>
			                <p>风向：<span></span>${list.wind_d }</p>
			            </div>
	                </div>
	            </c:when>
	            <c:when test="${fn:startsWith(list.dev_code,'dev101') or fn:startsWith(list.dev_code,'dev201')}">
		            <div id="picture">
			            <p>设备编号：<span>${list.dev_code }</span></p>
			            <p>设备类型：<span>${list.dev_code_value }</span></p>
			            <p>数据上报时间：<span><fmt:formatDate value="${list.data_time }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span></p>
				            <div class="con">
				               
				            </div>
				            <div class="show">
				                <c:forTokens items="${list.data_value }" delims="," var="photo" begin="0" end="0">
                                      <img src="/upload/${list.ms_code }/${list.dev_code}/${photo}" />
                                </c:forTokens>
				            	<c:forTokens items="${list.data_value }" delims="," var="photo">
				                      <img src="/upload/${list.ms_code }/${list.dev_code}/${photo}" />
				                </c:forTokens>
				            </div>
		            </div>
	            </c:when>
	            <c:otherwise>
	                <div id="detyp">
			            <p>设备编号：<span>${list.dev_code }</span></p>
			            <p>设备类型：<span>${list.dev_code_value }</span></p>
			            <p>数据上报时间：<span><fmt:formatDate value="${list.data_time }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></p>
			            <div class="con">
			                <p>病虫数量:<span>${list.data_value }</span></p>
			            </div>
			        </div>
	            </c:otherwise>
	        </c:choose>
	    </c:forEach>
    </c:if>
    </div>
</div>
<div id="footer">
    <li>山东省植物保护总站</li>
</div>
<script>

</script>
</body>
</html>