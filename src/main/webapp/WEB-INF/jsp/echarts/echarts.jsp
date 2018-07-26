<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="css/echarts.css" rel="stylesheet" type="text/css">
    <script src="js/jquery.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <script type="text/javascript" src="js/jquery.page.js"></script>

    <script src="js/delete.js" type="text/javascript"></script>
    <script src="js/jilian.js" type="text/javascript"></script>
    <script src="js/echarts.min.js"></script>
    <script type="text/javascript">
    var height=$("#content_r").height();

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
                data: ['空气温度']
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
                    data : new Array(),
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [

                {
                    name: '空气温度',
                    type: 'line',
                    data: [98, 77, 101, 99, 40]
                }
            ]
        };
          
        myChart.setOption(option);
            
           var listHisData = document.getElementsByName("hisData1");
             for (var i = 0,n = listHisData.length; i < n; i++) {
            	option.series[0].data[i] = listHisData[i].value;
            	
            }  
            
    });


    </script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<div id="content_r">
    <li class="tit"><p class="xx"><img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span> > <span id="zb2">站点信息</span> > <span id="zb3">气象设备查询</span></p></li>
   <c:set var="weather" value="${hisData.weather}" scope="page"></c:set>
    <c:forEach items="${listHisData }" var="his">
        <input type="hidden" name="hisData1" id="hisData1" value="${his[weather] }">
    </c:forEach>
    
    <div id="xq">

    </div>
</div>
<div id="footer">
    <li>山东省植物保护总站</li>
</div>
</body>

</html>