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
<title>监测站信息</title>
    <link href="css/xinxi.css" rel="stylesheet" type="text/css">
    <script src="js/jquery.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <script type="text/javascript" src="js/jquery.page.js"></script>
    <script src="js/delete.js" type="text/javascript"></script>
    <script src="js/xinxi.js"  type="text/javascript"></script>
    <script src="js/jilian.js" type="text/javascript"></script>
    <script type="text/javascript">
	    $(function(){
	        city();
	        $("#s_city").change(function city(){
	            var city_id = $(this).val();
	            $("#s_area").html("<option value=''>地级市</option>");
	            $.post("data/listArea",{city_id:city_id},function(data){
	                if(data!=null&&data.length>0){
	                    for(var i=0;i<data.length;i++){
	                        $("#s_area").append("<option value="+data[i].data_value+">"+data[i].data_name+"</option>");
	                    }
	                }
	            });
	        });
	    })
	    function city(){
	        $.post("data/listCity",function(data){
	            if(data!=null&&data.length>0){
	                for(var i=0;i<data.length;i++){
	                    $("#s_city").append("<option value="+data[i].data_value+">"+data[i].data_name+"</option>");
	                }
	            }
	        })
	    }
	    
	    function chakan(obj,id){
	        $(obj).parent().parent().parent().parent().hide();
	        $("#chakan").show();
	        $("#page").hide();
	        $("#chakan").html("");
	        $.post("station/loadStation",{id:id},function(data){
	            if(data!=null){
	                $("#chakan").append("<table id='bg1'>" +
	                        "<tr><td class='s1'>监测站名称</td><td class='s2'>"+data.ms_name+"</td></tr>" +
	                        "<tr><td class='s1'>监测站</td><td class='s2'>"+data.ms_code+"</td></tr>" +
	                        "<tr><td class='s1'>建设时间</td><td class='s2'>"+data.ms_date+"</td></tr>" +
	                        "<tr><td class='s1'>建设内容</td><td class='s2'>"+data.ms_dev_value+"</td></tr>" +
	                        "<tr><td class='s1'>监测站类型</td><td class='s2'>"+data.ms_type_value.data_name+"</td></tr>" +
	                        "<tr><td class='s1'>资金来源</td><td class='s2'>"+data.ms_fp_value.data_name+"</td></tr>" +
	                        "<tr><td class='s1'>监测站位置</td><td class='s2'>"+data.ms_place+"</td></tr>" +
	                        "<tr><td class='s1'>经纬度</td><td class='s2'>"+data.ms_position+"</td></tr>" +
	                        "<tr><td class='s1'>使用单位，联系人，联系方式</td><td class='s2'>"+data.ms_user+"</td></tr>" +
	                        "<tr><td class='s1'>施工单位，联系人，联系方式</td><td class='s2'>"+data.ms_builder+"</td></tr>" +
	                        "<tr><td class='s1'>网络类型</td><td class='s2'>"+data.ms_net_value.data_name+"</td></tr>" +
	                        "<tr><td class='s1'>网关类型</td><td class='s2'>"+data.ms_gate_value.data_name+"</td></tr>" +
	                        "<tr><td class='s1'>监测站描述</td><td class='s2'>"+data.ms_desc+"</td></tr>" +
	                        "</table><form><input type='button' value='关闭' onclick='guanbi()' /></form>");
	            }
	        });
	    }
	    
	    function xiugai(obj,id){
            $(obj).parent().parent().parent().parent().hide();
            $("#xiugai").show();
            $("h4").html("修改信息");
            $("#caidan").css("height",height)
            $("#page").hide();
            $("#caidan").css("height","840px");
            $("#xiugai").html("");
            $.post("station/loadStation",{id:id},function(data){
                if(data!=null){
                    $("#xiugai").append("<form action='station/loadStation' method='post'>"+
                            "<span>监测站名称:</span><input name='ms_name' type='text' value='"+data.ms_name+"'><br/>" +
                            "<span>监测站编码:</span><input name='ms_code' type='text' value='"+data.ms_code+"'><br/>" +
                            "<span>建设时间:</span><input name='ms_date' type='text' value='"+data.ms_date+"' style='margin-left: 28px;'><br/>" +
                            "<span>使用单位，联系人，联系方式:</span><input name='ms_user' type='text' value='"+data.ms_user+"' class='lxfs'><br/>" +
                            "<span>施工单位，联系人，联系方式:</span><input name='ms_builder' type='text' value='"+data.ms_builder+"' class='lxfs'><br/>" +
                            "<span >监测站类型:</span>" +
                            "<select name='ms_type' style='margin-left: 1%;'>"+ 
                                "<option value='"+data.ms_type+"'>"+data.ms_type_value.data_name+"</option>" + 
                                "<option value='1'>新建重点监测站</option>" +
                                "<option value='2'>改建重点监测站</option>" +
                                "<option value='3'>新建普通监测站</option>" +
                                "<option value='4'>改建普通监测站</option>" +
                            "</select><br/>" +
                            "<span>资金来源:</span>" +
                            "<select name='ms_fp'>"+
                                "<option value='"+data.ms_fp+"'>"+data.ms_fp_value.data_name+"</option>" +
                                "<option value='1'>省资金</option>" +
                                "<option value='2'>国家资金</option>" +
                                "<option value='3'>其他</option>" +
                            "</select><br/>" +
                            "<span>网络类型:</span>" +
                            "<select name='ms_net'>" +
                                "<option value='"+data.ms_net+"'>"+data.ms_net_value.data_name+"</option>" +
                                "<option value='1'>无线</option>" +
                                "<option value='2'>有线</option>" +
                            "</select><br/>" +
                            "<span>网关类型:</span>" +
                            "<select name='ms_gate'>" +
                                "<option value='"+data.ms_gate+"'>"+data.ms_gate_value.data_name+"</option>" +
                                "<option value='1'>NZ2000</option>" +
                                "<option value='2'>NZ1000</option>" +
                            "</select><br/>" +
                            "<span>建设内容:</span>" +
                                "<input name='ms_dev' type='checkbox' name='gn' class='gn' value='1'>病害监测孢子捕捉仪" +
                                "<input name='ms_dev' type='checkbox' name='gn' class='gn' value='2'>害虫监测测报灯" +
                                "<input name='ms_dev' type='checkbox' name='gn' class='gn' value='3'>害虫监测性诱监测仪" +
                                "<input name='ms_dev' type='checkbox' name='gn' class='gn' value='4'>鼠情监测鼠害监测仪" +
                                "<input name='ms_dev' type='checkbox' name='gn' class='gn' value='5'>环境因子监测气象监测仪" +
                                "<input name='ms_dev' type='checkbox' name='gn' class='gn' value='6'>环境因子监测土壤监测仪" +
                                "<input name='ms_dev' type='checkbox' name='gn' class='gn' value='7'>视频图像监测高清摄像头<br/>" +
                            "<span >监测站描述:</span>" +
                                "<textarea name='ms_desc'>"+data.ms_desc+"</textarea>" +
                            "<input class='xg' type='button' value='修改' onclick='xg()'>" +
                            "</form>"
                    );
                }
            });
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
<jsp:include page="../common/header.jsp"></jsp:include>

<div id="content_r">
    <li class="tit"><p class="xx"><img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span> > <span id="zb2">站点信息</span> > <span id="zb3">站点配置管理</span></p></li>
    <div class="gn">
        <form action="station/listStation" method="post">
            <span class="span1">监测站名称：<input type="text" name="ms_name" placeholder="不限" ></span>
            <span class="span1">资金来源：
                <select class="select1" name="ms_fp">
                    <option value="">不限</option>
                    <c:forEach items="${msFp }" var="fp">
                        <option value="${fp.data_value }">${fp.data_name }</option>
                    </c:forEach>
                </select>
            </span>
            <span class="span1">监测站类型：
                <select class="select1" name="ms_type">
                    <option value="">不限</option>
                    <c:forEach items="${msType }" var="type">
                        <option value="${type.data_value }">${type.data_name }</option>
                    </c:forEach>
                </select>
            </span>
            <span  class="span1">监测站区域：
                <select id="s_city" name="city">
                    <option value="">市区</option>
                </select>
                <select id="s_area" name="city" >
                    <option value="">地级市</option>
                </select>
                </span>
            <span class="span1">网关类型：
                <select class="select1" name="ms_gate">
                    <option value="">不限</option>
                    <c:forEach items="${msGate }" var="gate">
                        <option value="${gate.data_value }">${gate.data_name }</option>
                    </c:forEach>
                </select>
            </span>

            <input type="submit"  value="查询" class="cx"> <input type="button" value="添加监测站" class="btn1" onclick="tianjia()"/>
        </form>
    </div>


    <h4>数据列表：</h4>

    <div id="xq">
        <table  id="bg">
            <thead>
                <tr>
                    <td class="t3">监测站名称</td>
                    <td class="t2">监测站编码</td>
                    <td class="t4">建设内容</td>
                    <td class="t5">监测站类型</td>
                    <td class="t6">查看</td>
                    <td class="t7">删除</td>
                    <td class="t8">配置</td>
                    <td class="t9">修改</td>
                </tr>
            </thead>
                <tbody>
                    <c:forEach items="${listStation }" var="station">
	                    <tr>
	                        <td class="t3">${station.ms_name }</td>
	                        <td class="t2">${station.ms_code }</td>
	                        <td class="t4">${station.ms_dev_value }</td>
	                        <td class="t5">${station.ms_type_value.data_name }</td>
	                        <td class="t6"><input type="button" value="查看" class="input1" onclick="chakan(this,${station.id})"></td>
	                        <td class="t7"><input type="button" value="删除"  class="input2" onclick="del(this,${station.id})" ></td>
	                        <td class="t8"><input type="button" value="配置" class="input1" onclick="peizhi(this)"></td>
	                        <td class="t9"><input type="button" value="修改"  class="input1"  onclick="xiugai(this,${station.id})"></td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        <div  id="page">
        </div>
        <div id="chakan"></div>
        <div id="xiugai"></div>
    <div id="tianjia">
        <form>
            <span>监测站名称:</span>
            <input type="text" >
            <br/>
            <span>监测站编码:</span>
            <input type="text" >
            <br/>
            <span>建设时间:</span>
            <input type="text" style="margin-left: 28px;">
            <br/>
            <span>使用单位，联系人，联系方式:</span>

            <input type="text" class="lxfs">
            <br/>
            <span>施工单位，联系人，联系方式:</span>
            <input type="text" class="lxfs">
            <br/>
            <span >监测站类型:</span>
            <select style="margin-left: 1%;">
                <option>新建重点监测站</option>
                <option>改建重点监测站</option>
                <option>新建普通监测站</option>
                <option>改建普通监测站</option>
            </select>
            <br/>
            <span>资金来源:</span>
            <select>
                <option>省资金</option>

                <option>国家资金</option>
                <option>其他</option>
            </select>
            <br/>
            <span>网络类型:</span>
            <select>
                <option>无线</option>

                <option>有线</option>
            </select>
            <br/>
            <span>网关类型:</span>
            <select>
                <option>NZ2000</option>

                <option>NZ1000</option>
            </select>
            <br/>
            <span>监测性质:</span>
            <input type="checkbox" name="gn" class="gn">病害监测
            <input type="checkbox" name="gn"  class="gn">害虫监测
            <input type="checkbox" name="gn"  class="gn"> 鼠情监测
            <input type="checkbox" name="gn"  class="gn">环境因子监测
            <input type="checkbox" name="gn"  class="gn">视频图像监测
            <input type="checkbox" name="gn"  class="gn">其他监测
            <br/>
            <span >监测站描述:</span>
            <textarea></textarea>
            <input class="tj" type="button" value="添加" onclick="tj()">
        </form>
    </div>
    </div>

</div>
<div id="footer">
    <li>济南农智信息科技有限公司所有&copy; &nbsp;电话：12345677  &nbsp;<a href="#">关于我们</a> &nbsp;<a href="#">售后服务</a></li>
</div>
</body>
</html>