<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>历史数据</title>

<link href="css/page.css" rel="stylesheet" type="text/css">
<link href="css/lssjcx.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css" />
<%--<link href="css/sjck.css" rel="stylesheet" type="text/css">--%>

<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script src="js/angular.min.js" type="text/javascript"></script>
<script src="js/jquery.datetimepicker.js" type="text/javascript"></script>
<script src="js/sjck.js" type="text/javascript"></script>
<script src="js/jilian.js" type="text/javascript"></script>
<script src="js/cascading.js" type="text/javascript"></script>


<script type="text/javascript">
        var account = ${sessionScope.user.account};
        function weather(obj,id){
            $(obj).parent().parent().parent().parent().hide();
            $("#weather").show();
            $("#page").hide();
            $("#Paging").hide();
            $.post("hisMeteorological/loadHisMeteorological",{id:id},function(data){
                if(data!=null){
                    $("#m_ms_code_value").html(data.ms_code_value);
                    $("#m_ms_code").html(data.ms_code);
                    $("#m_dev_code_value").html(data.dev_code_value);
                    $("#m_dev_code").html(data.dev_code);
                    $("#m_air_t").html(data.air_t);
                    $("#m_air_h").html(data.air_h);
                    $("#m_wind_s").html(data.wind_s);
                    $("#m_wind_d").html(data.wind_d);
                    $("#m_dew_p").html(data.dew_p);
                    $("#m_rain_f").html(data.rain_f);
                    $("#m_sunshine_h").html(data.sunshine_h);
                    $("#m_atmo_s").html(data.atmo_s);
                    $("#m_soil_t1").html(data.soil_t1);
                    $("#m_soil_t2").html(data.soil_t2);
                    $("#m_soil_t3").html(data.soil_t3);
                    $("#m_soil_h1").html(data.soil_h1);
                    $("#m_soil_h2").html(data.soil_h2);
                    $("#m_soil_h3").html(data.soil_h3);
                    $("#m_soil_ec").html(data.soil_ec);
                    $("#m_data_time").html(data.data_time);
                    $("#m_dev_status").html(data.dataDevStatus.data_name);
                }
            });
        } 

</script>
</head>

<body ng-app='app' ng-controller='controller'>
    <jsp:include page="../common/header.jsp"></jsp:include>
    <div id="content_r">
        <li class="tit"><p class="xx">
                <img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span>
                > <span id="zb2">监测数据管理</span> > <span id="zb3">历史数据查询</span>
            </p></li>
        <div class="menu">
            <form action="hisData/listHisData" method="post">
                <span>监测站区域： </span> 
                     <select id="s_city" name="city">
                             <option value="">市区</option>
                       </select> 
                       <select id="s_area" name="city">
                             <option value="">区县</option>
                      </select>
                <span>设备类型：</span> <select class="select1" name="device_type">
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
                <span>监测对象：</span> <select class="select1" name="device_object">
                    <option value="">不限</option>
                    <c:forEach items="${devObject }" var="ob">
                        <option value="${ob.data_value }">${ob.data_name }</option>
                    </c:forEach>
                </select> 
                  <span>设备状态：</span> <select class="select1" name="dev_status">
                    <option value="">不限</option>
                    <c:forEach items="${devStauts }" var="status">
                        <option value="${status.data_value }">${status.data_name }</option>
                    </c:forEach>
                </select> 
                <br /> 
                <span>数据时间：</span> <input type="text" name="data_time_begin1" id="datetimepicker" />
                                  -- <input type="text" name="data_time_end1" id="datetimepicker1" />
                <c:if test="${hisData != null and hisData.dev_code != null }">
                    <input type="hidden" name="dev_code" value="${hisData.dev_code }"> 
                </c:if>
                <c:if test="${hisData != null and hisData.ms_code != null }">
                    <input type="hidden" name="ms_code" value="${hisData.ms_code }"> 
                </c:if>
                
                <c:if test="${hisData != null and hisData.ms_code != null }">
                    <span>显示形式：</span><select class="select1" name="view">
                        <option value="table">表格</option>
                        <option value="chart">折线图</option>
                    </select>
                </c:if>
                <c:if test="${hisData != null and hisData.dev_code != null and fn:startsWith(hisData.dev_code,'dev5')}">
                    <span>气象条件：</span> <select class="select1" name="weather">
                        <option value="air_t">空气温度</option>
                        <option value="air_h">空气湿度</option>
                        <option value="wind_s">风速</option>
                        <option value="wind_d">风向</option>
                        <option value="dew_p">露点温度</option>
                        <option value="rain_f">降雨量</option>
                        <option value="sunshine_h">日照时数</option>
                        <option value="atmo_s">大气压</option>
                        <option value="soil_t1">土壤温度1</option>
                        <option value="soil_t2">土壤温度2</option>
                        <option value="soil_t3">土壤温度3</option>
                        <option value="soil_h1">土壤湿度1</option>
                        <option value="soil_h2">土壤湿度2</option>
                        <option value="soil_h3">土壤湿度3</option>
                        <option value="soil_ec">土壤EC值</option>
                    </select> 
                </c:if>
              
                <input type="submit" value="查找" class="search" style="left:73%; top: 30px;">
                <input type="reset" value="重置" class="reset"  style="left:80%; top: 30px;">

            </form>

        </div>
        <table id="main">
            <thead class="name">
                <c:if test="${hisData != null and hisData.dev_code != null }">
                    <tr>
                        <td class="t1">监测站</td>
                        <td class="t2">监测站编码</td>
                        <td class="t3">监测设备编码</td>
                        <td class="t5">采集时间</td>
                        <td class="t6"><c:choose>
                                <c:when
                                    test="${fn:startsWith(hisData.dev_code,'dev101') or fn:startsWith(hisData.dev_code,'dev201')}">
                                                                     查看
                       </c:when>
                                <c:when test="${fn:startsWith(his.dev_code,'dev5')}">
                                                                            气象查看
                       </c:when>
                                <c:otherwise>
                                                                            数据
                       </c:otherwise>
                            </c:choose></td>
                    </tr>
                </c:if>
                <c:if test="${hisData == null or hisData.dev_code == null }">
                    <tr>
                        <td class="t1">监测站</td>
                        <td class="t2">监测站编码</td>
                        <td class="t3">监测设备</td>
                        <td class="t6">查看</td>
                    </tr>
                </c:if>
            </thead>
            <tbody>
                <c:if test="${hisData != null and hisData.dev_code != null }">
                    <c:if test="${listHisData eq null}">
                        <tr>
                            <td colspan="8" style="text-align: center;"><font color="red" size="4">${message }</font></td>
                        </tr>
                    </c:if>
                    <c:if test="${listHisData != null}">
                        <c:forEach items="${listHisData }" var="his" >
                            <c:choose>
                                <c:when
                                    test="${fn:startsWith(his.dev_code,'dev101') or fn:startsWith(his.dev_code,'dev201')}">
                                    <tr style="border-bottom: 1px solid #adadad;">
                                        <td class="t1">${his.ms_code_value }</td>
                                        <td class="t2">${his.ms_code }</td>
                                        <td class="t3">${his.dev_code_value }</td>
                                        <td class="t5"><fmt:formatDate value="${his.data_time }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                                        <td class="t6"><input type="button" value="查看" onclick="look('${his.dev_code }',${his.ms_code})"></td>
                                    </tr>
                                </c:when>
                                <c:when test="${fn:startsWith(his.dev_code,'dev5')}">
                                    <tr style="border-bottom: 1px solid #adadad;">
                                        <td class="t1">${his.ms_code_value }</td>
                                        <td class="t2">${his.ms_code }</td>
                                        <td class="t3">${his.dev_code_value }</td>
                                        <td class="t5"><fmt:formatDate value="${his.data_time }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                                        <td class="t6"><input type="button" value="查看" onclick="weather(this,${his.id})"></td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr style="border-bottom: 1px solid #adadad;">
                                        <td class="t1">${his.ms_code_value }</td>
                                        <td class="t2">${his.ms_code }</td>
                                        <td class="t3">${his.dev_code_value }</td>
                                        <td class="t5"><fmt:formatDate value="${his.data_time }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                                        <td class="t6">${his.data_value }</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:if>
                </c:if>
                <c:if test="${hisData == null or hisData.dev_code == null }">
                    <c:if test="${listHisData eq null}">
                        <tr>
                            <td colspan="8" style="text-align: center;">
                                 <font color="red" size="4">${message }</font>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${listHisData != null}">
                        <c:forEach items="${listHisData }" var="his">
                            <tr style="border-bottom: 1px solid #adadad;">
                                <td class="t1">${his.ms_code_value }</td>
                                <td class="t2">${his.ms_code }</td>
                                <td class="t3">${his.dev_code_value }</td>
                                <td class="t6">
                                    <form action="hisData/listHisData" method="post">
                                        <input type="hidden" id="dev_code" name="dev_code" value="${his.dev_code }"> 
                                        <input type="hidden" id="ms_code" name="ms_code" value="${his.ms_code }"> 
                                        <input type="submit" value="查看设备历史数据">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </c:if>
            </tbody>
        </table>
        <jsp:include page="../common/pages.jsp"></jsp:include>

        <div id="img">
            <div id="imga"></div>
            <form>
                <input type="button" value="关闭" onclick="clos()" />
            </form>
        </div>
        <div id="weather" style="display: none">
            <table>
                <tr>
                    <td>监测站名称</td>
                    <td id="m_ms_code_value"></td>
                    <td>监测站编码</td>
                    <td id="m_ms_code"></td>
                </tr>
                <%--<tr>--%>
                <%--<td>监测站编码</td>--%>
                <%--<td id="m_ms_code"></td>--%>
                <%--</tr>--%>
                <tr>
                    <td>设备名称</td>
                    <td id="m_dev_code_value"></td>
                    <td>设备编码</td>
                    <td id="m_dev_code"></td>
                </tr>
                <%--<tr>--%>
                <%--<td>设备编码</td>--%>
                <%--<td id="m_dev_code"></td>--%>
                <%--</tr>--%>
                <tr>
                    <td>空气温度</td>
                    <td id="m_air_t"></td>
                    <td>空气湿度</td>
                    <td id="m_air_h"></td>
                </tr>
                <%--<tr>--%>
                <%--<td>空气湿度</td>--%>
                <%--<td id="m_air_h"></td>--%>
                <%--</tr>--%>
                <tr>
                    <td>风速</td>
                    <td id="m_wind_s"></td>
                    <td>风向</td>
                    <td id="m_wind_d"></td>
                </tr>
                <%--<tr>--%>
                <%--<td>风向</td>--%>
                <%--<td id="m_wind_d"></td>--%>
                <%--</tr>--%>
                <tr>
                    <td>露点温度</td>
                    <td id="m_dew_p"></td>
                    <td>降雨量</td>
                    <td id="m_rain_f"></td>
                </tr>
                <%--<tr>--%>
                <%--<td>降雨量</td>--%>
                <%--<td id="m_rain_f"></td>--%>
                <%--</tr>--%>
                <tr>
                    <td>日照时数</td>
                    <td id="m_sunshine_h"></td>
                    <td>大气压</td>
                    <td id="m_atmo_s"></td>
                </tr>
                <%--<tr>--%>
                <%--<td>大气压</td>--%>
                <%--<td id="m_atmo_s"></td>--%>
                <%--</tr>--%>
                <tr>
                    <td>土壤温度1</td>
                    <td id="m_soil_t1"></td>
                    <td>土壤温度2</td>
                    <td id="m_soil_t2"></td>
                </tr>
                <%--<tr>--%>
                <%--<td>土壤温度2</td>--%>
                <%--<td id="m_soil_t2"></td>--%>
                <%--</tr>--%>
                <tr>
                    <td>土壤温度3</td>
                    <td id="m_soil_t3"></td>
                    <td>土壤湿度1</td>
                    <td id="m_soil_h1"></td>
                </tr>
                <%--<tr>--%>
                <%--<td>土壤湿度1</td>--%>
                <%--<td id="m_soil_h1"></td>--%>
                <%--</tr>--%>
                <tr>
                    <td>土壤湿度2</td>
                    <td id="m_soil_h2"></td>
                    <td>土壤湿度3</td>
                    <td id="m_soil_h3"></td>
                </tr>
                <%--<tr>--%>
                <%--<td>土壤湿度3</td>--%>
                <%--<td id="m_soil_h3"></td>--%>
                <%--</tr>--%>
                <tr>
                    <td>土壤EC值</td>
                    <td id="m_soil_ec"></td>
                    <td>时间</td>
                    <td id="m_data_time"></td>
                </tr>
                <tr>
                    <td>设备状态</td>
                    <td id="m_dev_status"></td>
                </tr>
                <%--<tr>--%>
                <%--<td>设备状态</td>--%>
                <%--<td id="m_dev_status"></td>--%>
                <%--</tr>--%>
            </table> 
            <form>
                <input type="button" value="关闭" onclick="clo()" />
            </form>
        </div>
    </div>

    <div id="footer">
        <li>山东省植物保护总站</li>
    </div>
    <script type="text/javascript">
$.noConflict();
    angular.module('app',[]).controller('controller',function($scope){
        $('#datetimepicker').datetimepicker({
            lang:'ch',
            format:'Y-m-d H:00:00',
        });
        $('#datetimepicker1').datetimepicker({
            lang:'ch',
            format:'Y-m-d H:00:00',
        });
       
    });
    
       
    
</script>
</body>
</html>
