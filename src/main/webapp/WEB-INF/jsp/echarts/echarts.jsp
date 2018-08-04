<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
 <title></title>

    <link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css" />
    <link href="css/echarts.css" rel="stylesheet" type="text/css">
    <script src="js/jquery.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui.js"></script>
	<script src="js/angular.min.js" type="text/javascript"></script>
	<script src="js/jquery.datetimepicker.js" type="text/javascript"></script>
	<script src="js/sjck.js" type="text/javascript"></script>
    <script src="js/delete.js" type="text/javascript"></script>
    <script src="js/jilian.js" type="text/javascript"></script>
    <script src="js/echarts.min.js"></script>
    <script src="js/cascading.js" type="text/javascript"></script>
    
    <script type="text/javascript">
    var height=$("#content_r").height();
    var account = ${sessionScope.user.account};
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
    });
    $(function() {
        var myChart = echarts.init(document.getElementById('xq'));
        option = {
            dataZoom: [
                {
                    type: 'slider',
                    show: true,
                    start: 80,
                    end: 100,
                    handleSize: 8
                },
                {
                    type: 'inside',
                    start: 94,
                    end: 100
                },
                {
                    type: 'slider',
                    show: true,
                    yAxisIndex: 0,
                    filterMode: 'empty',
                    width: 12,
                    height: '70%',
                    handleSize: 8,
                    showDataShadow: false,
                    left: '93%'
                }
            ],

            color: ['#003366'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {
                data: ['']
            },
            toolbox: {
                show: true,
                orient: 'inline-axis',
                left: '90%',
                top: 'center',
                feature: {
                    mark: {show: true},
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            calculable: true,
             
            xAxis: [
                {
                    type: 'category',
                    axisTick: {show: false},
                    data : []
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ], 
            series: [

                {
                    name: '',
                    type: 'line',
                    data: []
                }
            ]
        };
          
        
            
           var hisAvg1 = document.getElementsByName("hisAvg1");
           var hisDate1 = document.getElementsByName("hisDate1");
           var weather = document.getElementById("weather").value;
           var color = document.getElementById("color");
           if (color != null) {
        	   option.color[0] = color.value;
           }
           for (var i = 0,n = hisAvg1.length; i < n; i++) {
        	    option.series[0].data[i] = hisAvg1[i].value;
        	    option.xAxis[0].data[i] = hisDate1[i].value;
           }  
           option.series[0].name = weather;
           option.legend.data[0] = weather;
           myChart.setOption(option);
    });


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
</head>
<body  ng-app='app' ng-controller='controller'>
<jsp:include page="../common/header.jsp"></jsp:include>
<div id="content_r">
    <li class="tit"><p class="xx"><img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span> > <span id="zb2">监测数据管理</span> > <span id="zb3">历史数据查询</span></p></li>
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
               
                <input type="submit" value="查找" class="search">
                <input type="reset" value="重置" class="reset">

            </form>

        </div>
   
    <c:forEach items="${listHisData }" var="his">
        <input type="hidden" name="hisAvg1" id="hisAvg1" value="${his.avg }">
        <input type="hidden" name="hisDate1" id="hisDate1" value="${his.date_time }">
    </c:forEach>
    <c:if test="${hisData.weather == null or hisData.weather == ''}">
        <input type="hidden" name="weather" id="weather" value="数量">
        <input type="hidden" name="color" id="color" value="#283dde">
    </c:if>
    <c:if test="${hisData.weather eq 'air_t'}">
        <input type="hidden" name="weather" id="weather" value="空气温度">
        <input type="hidden" name="color" id="color" value="#eb0808">
    </c:if>
    <c:if test="${hisData.weather eq 'air_h'}">
        <input type="hidden" name="weather" id="weather" value="空气湿度">
        <input type="hidden" name="color" id="color" value="#283dde">
    </c:if>
    <c:if test="${hisData.weather eq 'wind_s'}">
        <input type="hidden" name="weather" id="weather" value="风速">
    </c:if>
    <c:if test="${hisData.weather eq 'wind_d'}">
        <input type="hidden" name="weather" id="weather" value="风向">
    </c:if>
    <c:if test="${hisData.weather eq 'dew_p'}">
        <input type="hidden" name="weather" id="weather" value="露点温度">
    </c:if>
    <c:if test="${hisData.weather eq 'rain_f'}">
        <input type="hidden" name="weather" id="weather" value="降雨量">
        <input type="hidden" name="color" id="color" value="#283dde">
    </c:if>
    <c:if test="${hisData.weather eq 'sunshine_h'}">
        <input type="hidden" name="weather" id="weather" value="日照时数">
    </c:if>
    <c:if test="${hisData.weather eq 'atmo_s'}">
        <input type="hidden" name="weather" id="weather" value="大气压">
    </c:if>
    <c:if test="${hisData.weather eq 'soil_t1'}">
        <input type="hidden" name="weather" id="weather" value="土壤温度1">
    </c:if>
    <c:if test="${hisData.weather eq 'soil_t2'}">
        <input type="hidden" name="weather" id="weather" value="土壤温度2">
    </c:if>
    <c:if test="${hisData.weather eq 'soil_t3'}">
        <input type="hidden" name="weather" id="weather" value="土壤温度3">
    </c:if>
    <c:if test="${hisData.weather eq 'soil_h1'}">
        <input type="hidden" name="weather" id="weather" value="土壤湿度1">
    </c:if>
    <c:if test="${hisData.weather eq 'soil_h2'}">
        <input type="hidden" name="weather" id="weather" value="土壤湿度2">
    </c:if>
    <c:if test="${hisData.weather eq 'soil_h3'}">
        <input type="hidden" name="weather" id="weather" value="土壤湿度3">
    </c:if>
    <c:if test="${hisData.weather eq 'soil_ec'}">
        <input type="hidden" name="weather" id="weather" value="土壤EC值">
    </c:if>
    <div id="xq">

    </div>
</div>
<div id="footer">
    <li>山东省植物保护总站</li>
</div>
</body>

</html>