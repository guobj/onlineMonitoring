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
<title>监测站点查询</title>
    <link href="css/xinxi.css" rel="stylesheet" type=text/css>
    <link href="css/page.css" rel="stylesheet" type=text/css>
    <script src="js/jquery.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <script type="text/javascript" src="js/jquery.page.js"></script>
    <script src="js/delete.js" type="text/javascript"></script>
    <script src="js/xinxi.js"  type="text/javascript"></script>
    <script src="js/cascading.js"  type="text/javascript"></script>
    <script type="text/javascript">
        var account = ${sessionScope.user.account};
    </script>
    <script type="text/javascript">
        function chakan(obj,id){
            $(obj).parent().parent().parent().parent().hide();
            $("#chakan").show();
            $("#page").hide();
            $("#chakan").html("");
            $("#Paging").hide();
            $.post("station/loadStation",{id:id},function(data){
                if(data!=null){
                	if (data.ms_dev_value == null) {
                		data.ms_dev_value = "";
                	}
                	if (data.ms_name == null) {
                        data.ms_name = "";
                    }
                	if (data.ms_date == null) {
                        data.ms_date = "";
                    }
                	if (data.ms_place == null) {
                        data.ms_place = "";
                    }
                	if (data.ms_user == null) {
                        data.ms_user = "";
                    }
                	if (data.ms_builder == null) {
                        data.ms_builder = "";
                    }
                	if (data.ms_desc == null) {
                        data.ms_desc = "";
                    }
                    $("#chakan").append("<table id='bg1'>" +
                            "<tr><td class='s1'>监测站名称</td><td class='s2'>"+data.ms_name+"</td></tr>" +
                            "<tr><td class='s1'>监测站</td><td class='s2'>"+data.ms_code_value+"</td></tr>" +
                            "<tr><td class='s1'>监测站编码</td><td class='s2'>"+data.ms_code+"</td></tr>" +
                            "<tr><td class='s1'>建设时间</td><td class='s2'>"+data.ms_date+"</td></tr>" +
                            "<tr><td class='s1'>建设内容</td><td class='s2'>"+data.ms_dev_value+"</td></tr>" +
                            "<tr><td class='s1'>监测站类型</td><td class='s2'>"+data.ms_type_value.data_name+"</td></tr>" +
                            "<tr><td class='s1'>资金来源</td><td class='s2'>"+data.ms_fp_value.data_name+"</td></tr>" +
                            "<tr><td class='s1'>监测站位置</td><td class='s2'>"+data.ms_place+"</td></tr>" +
                            "<tr><td class='s1'>经度</td><td class='s2'>"+data.ms_longitude+"</td></tr>" +
                            "<tr><td class='s1'>纬度</td><td class='s2'>"+data.ms_latitude+"</td></tr>" +
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
            $("#page").hide();
            $("#Paging").hide();
            $.post("station/getStation",{id:id},function(data){
                if(data!=null){
                	$("#id").val(data.data.station.id);
                	$("#ms_name").val(data.data.station.ms_name);
                	$("#ms_code").val(data.data.station.ms_code);
                	$("#ms_code11").val(data.data.station.ms_code);
                	$("#ms_date1").val(data.data.station.ms_date);
                	$("#ms_place").val(data.data.station.ms_place);
                	$("#ms_user").val(data.data.station.ms_user);
                	$("#ms_builder").val(data.data.station.ms_builder);
                	$("#ms_desc").val(data.data.station.ms_desc);
                	var ms_type = $("#ms_type").find("option"); //获取select下拉框的所有值
                	for (var j = 0; j < ms_type.length; j++) {
                		if ($(ms_type[j]).val() == data.data.station.ms_type) {
                		    $(ms_type[j]).attr("selected", "selected");
                		}
                	} 
                	var ms_fp = $("#ms_fp").find("option"); //获取select下拉框的所有值
                    for (var j = 0; j < ms_fp.length; j++) {
                        if ($(ms_fp[j]).val() == data.data.station.ms_fp) {
                            $(ms_fp[j]).attr("selected", "selected");
                        }
                    } 
                    var ms_net = $("#ms_net").find("option"); //获取select下拉框的所有值
                    for (var j = 0; j < ms_net.length; j++) {
                        if ($(ms_net[j]).val() == data.data.station.ms_net) {
                            $(ms_net[j]).attr("selected", "selected");
                        }
                    } 
                    var ms_gate = $("#ms_gate").find("option"); //获取select下拉框的所有值
                    for (var j = 0; j < ms_gate.length; j++) {
                        if ($(ms_gate[j]).val() == data.data.station.ms_gate) {
                            $(ms_gate[j]).attr("selected", "selected");
                        }
                    } 
                    
                    for (let i = 0, n = data.data.msDev.length; i < n; i++) {
                        $("#ms_dev").append("<input name='ms_dev' type='checkbox' id='msDev"+data.data.msDev[i].data_value+"' class='gn' value='"+data.data.msDev[i].data_value+"'>"+data.data.msDev[i].data_name+"");
                        if(i!=0&&i%3==0) {
                            $("#ms_dev").append("<br/>");
                       }
                    } 
                    if (data.data.station.ms_dev != null) {
                    	var msDev1 = data.data.station.ms_dev.split(",");
                        for (let i = 0,n = msDev1.length; i < n; i++) {
                            $("#msDev"+msDev1[i]).attr("checked","checked");
                        }
                    }
                }
            });
            
        }
        //修改
        function xg(){
            var form = new FormData(document.getElementById("updateForm"));
            $.MsgBox.Confirm("温馨提示", "确认修改？温馨提示", function () {  
            $.ajax({
                type:"post",
                url:"station/updateStation",
                data:form,
                processData:false,
                contentType:false,
                dataType:'json',
                success:function (data) {
                    if(data.data >= 0){
                    	$.MsgBox.Alert("消息", "修改成功");
                        window.location.href="station/listStation";
                    }
                }
            });
            
            });
        }
        function xg1(){
            $("#xiugai").hide();
            $("#bg").show();
            $("h4").html("数据列表");
            $("#page").show();
            $("#Paging").show();
        }
      
      //删除方法
        function delStation(id){
            $.MsgBox.Confirm("温馨提示", "执行删除后将无法恢复，确定继续吗？温馨提示", function () {
                $.ajax({
                    type:"get",
                    url:"station/deleteStation",
                    data:{id:id},
                    success:function (data) {
                    	$.MsgBox.Confirm("温馨提示", "删除成功");
                        window.location.reload();
                    }
                })
            });
            
        }
      function realData(ms_code){
    	  window.location.href="realData/listByMsCode?ms_code="+ms_code;
      }
   
    </script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>

<div id="content_r">
    <li class="tit"><p class="xx"><img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span> > <span id="zb2">站点管理</span> > <span id="zb3">监测站点查询</span></p></li>
    <div class="gn">
        <form action="station/listStation" method="post">
           <!--  <span class="span1">监测站名称：</span><input type="text" name="ms_name" placeholder="不限" > -->
            <span  class="span1">监测站区域：</span>
               <select id="s_city" name="city" style="width:6%">
                  <option value="">市区</option>
               </select>
               <select id="s_area" name="city" style="width:6%" >
                   <option value="">区县</option>
               </select>
            <span class="span1">监测站类型：</span>
                <select class="select1" name="ms_type">
                    <option value="">不限</option>
                    <c:forEach items="${msType }" var="type">
                        <option value="${type.data_value }">${type.data_name }</option>
                    </c:forEach>
                </select>
          
            <span class="span1">资金来源：</span>
                <select class="select1" name="ms_fp">
                    <option value="">不限</option>
                    <c:forEach items="${msFp }" var="fp">
                        <option value="${fp.data_value }">${fp.data_name }</option>
                    </c:forEach>
                </select>
 
            <span class="span1 wg">建站时间： </span>
                <input type="date" name="date_begin1" style="width:13%;">--<input type="date" name="date_end1" style="width:13%;">
         
            <input type="submit"  value="查询" class="cx"> 
        </form>
    </div>
    <%--<h5>数据列表：</h5>--%>
    <div id="xq">
        <table  id="bg">
            <thead>
                <tr>
                    <td class="t3">监测站名称</td>
                    <td class="t2">监测站编码</td>
                    <td class="t4">建设内容</td>
                    <td class="t5">监测站类型</td>
                    <td class="t8">实时数据</td>
                    <td class="t6">查看</td>
                     <td class="t9">修改</td>
                    <td class="t7">删除</td>
                </tr>
            </thead>
                <tbody>
                    <c:if test="${listStation eq null}" >
                        <tr><td colspan="8" style="text-align: center;"><font color="red" size="4">${message }</font> </td></tr>
                    </c:if>
                    <c:if test="${listStation != null}" >
                        <c:forEach items="${listStation }" var="station">
                            <tr>
                                <td class="t3">${station.ms_name }</td>
                                <td class="t2">${station.ms_code }</td>
                                <td class="t4">${station.ms_dev_value }</td>
                                <td class="t5">${station.ms_type_value.data_name }</td>
                                <td class="t8"><input type="button" value="查看" class="input1" onclick="realData(${station.ms_code})"></td>
                                <td class="t6"><input type="button" value="查看" class="input1" onclick="chakan(this,${station.id})"></td>
                                <td class="t9"><input type="button" value="修改"  class="input1"  onclick="xiugai(this,${station.id})"></td>
                                <td class="t7"><input type="button" value="删除"  class="input2" onclick="delStation(${station.id})" ></td>
                            </tr>
                        </c:forEach>
                     </c:if>
                </tbody>
            </table>
            <jsp:include page="../common/pages.jsp"></jsp:include>
        <div id="chakan"></div>
        <div id="xiugai">
            <form id="updateForm">"
                <input name="id" id="id" type="hidden" value=""><br/>
                <input name="ms_code11" id="ms_code11" type="hidden" value=""><br/>
                <span>监测站名称:</span><input name="ms_name" type="text" id="ms_name" value=""><span id="tip_1">格式正确</span><br/>
                <span>监测站编码:</span><input name="ms_code" type="text" id="ms_code" value=""><span id="tip_2">格式正确</span><br/>
                <span>建设时间:</span><input name="ms_date1" type="date" id="ms_date1" value="" style="margin-left: 28px;"><br/>
                <span>监测站位置:</span><input name="ms_place" type="text" id="ms_place" value=""><span id="tip_6">格式正确</span><br/>
                <span>使用单位，联系人，联系方式:</span><input name="ms_user" type="text" id="ms_user" value="" class="lxfs"><span id="tip_3">格式正确</span><br/>
                <span>施工单位，联系人，联系方式:</span><input name="ms_builder" type="text" id="ms_builder" value="" class="lxfs"><span id="tip_4">格式正确</span><br/>
                <span >监测站类型:</span>
                <select name="ms_type" id="ms_type" style="margin-left: 1%;">
                    <c:forEach items="${msType }" var="type">
                       <option value="${type.data_value }">${type.data_name }</option>
                    </c:forEach>
                </select><br/>
                <span>资金来源:</span>
                <select name="ms_fp" id="ms_fp">
                    <c:forEach items="${msFp }" var="fp">
                       <option value="${fp.data_value }">${fp.data_name }</option>
                    </c:forEach>
                </select><br/>
                <span>网络类型:</span>
                <select name="ms_net" id="ms_net">
                    <c:forEach items="${msNet }" var="net">
                       <option value="${net.data_value }">${net.data_name }</option>
                    </c:forEach>
                </select><br/>
               
                <span id="ms_dev">建设内容:</span>
                <span >监测站描述:</span>
                    <textarea name="ms_desc" id="ms_desc"></textarea><span id="tip_5">格式正确</span>
                <input class="xg" type="button" value="修改" id="mdi" onclick="xg()">
                <input class="xg" type="button" value="取消"  onclick="xg1()" style="margin-left: 5%;">
            </form>
        </div>
       

	</div>
</div>
<div id="footer">
    <li>山东省植物保护总站</li>
</div>

<script>
    var reg2 =/^(\d{8})$/;
    var reg1=/^.{2,50}$/;
    var reg3=/^.{0,100}$/;
    var reg4=/^.{0,100}$/;
    var reg5=/^.{0,300}$/;
    var reg6=/^.{0,100}$/;

    $(function(){
    	$("#mdi").attr("disabled", true);
        $("#ms_name").change(function(){
                    var str1=reg1.test($("#ms_name").val());
                    if(str1==true){
                        $("#tip_1").html("格式正确")
                    }else{
                        $("#tip_1").html("请输入2-50位字符");
                        $("#mdi").attr("disabled", true);
                    }
                })
         var temp = 0;
        $("#ms_code").change(function(){
            var str2=reg2.test($("#ms_code").val());
            if(str2==true){
            	var code = $("#ms_code").val();
            	var code11 = $("#ms_code11").val();
            	if (code != code11) {
            		$.post("station/existMsCode",{ms_code:code},function(data){
                        if (data > 0) {
                            $("#tip_2").html("编码已重复")
                            temp = 1;
                            $("#mdi").attr("disabled", true);
                        }else {
                            $("#tip_2").html("格式正确")
                            temp = 0;
                        }
                        if(temp==0&&reg1.test($("#ms_name").val())==true&&reg2.test($("#ms_code").val())==true&&reg3.test($("#ms_user").val())==true&&reg4.test($("#ms_builder").val())==true&&reg5.test($("#ms_desc").val())==true&&reg6.test($("#ms_place").val())==true){
                            $("#mdi").removeAttr("disabled", true);
                        }
                    });
            	}else {
            		$("#tip_2").html("格式正确")
                    temp = 0;
            	}
            }else{
                $("#tip_2").html("请输入8位数字");
                $("#mdi").attr("disabled", true);
            }
            
        })
        $("#ms_user").change(function(){
            var str3 = reg3.test($("#ms_user").val());
           
                if (str3 == true) {
                    $("#tip_3").html("格式正确")
                    console.log(str3);
                } else {
                    $("#tip_3").html("请输入0-100位字符");
                    $("#mdi").attr("disabled", true);
                }
            
        })
        $("#ms_builder").change(function(){
                    var str4=reg4.test($("#ms_builder").val());
                    if(str4==true){
                        $("#tip_4").html("格式正确")
                    }else{
                        $("#tip_4").html("请输入0-100位字符");
                        $("#mdi").attr("disabled", true);
                    }
                    
                })
        $("#ms_desc").change(function() {
                    var str5 = reg5.test($("#ms_desc").val());
                    if (str5 == true) {
                        $("#tip_5").html("格式正确")
                    } else {
                        $("#tip_5").html("请输入0-300位字符");
                        $("#mdi").attr("disabled", true);
                    }
                    
                }
        )
    $("#ms_place").change(function() {
        var str6 = reg6.test($("#ms_place").val());

        if (str6 == true) {
            $("#tip_6").html("格式正确")
        } else {
            $("#tip_6").html("请输入0-300位字符");
            $("#mdi").attr("disabled", true);
        }
    })

    $("#xiugai").children().children().change(function(){
        if(temp==0&&reg1.test($("#ms_name").val())==true&&reg2.test($("#ms_code").val())==true&&reg3.test($("#ms_user").val())==true&&reg4.test($("#ms_builder").val())==true&&reg5.test($("#ms_desc").val())==true&&reg6.test($("#ms_place").val())==true){
            $("#mdi").removeAttr("disabled", true);
        }
    })

    })


</script>

</body>
</html>