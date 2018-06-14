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
                    $("#chakan").append("<table id='bg1'>" +
                            "<tr><td class='s1'>监测站名称</td><td class='s2'>"+data.ms_name+"</td></tr>" +
                            "<tr><td class='s1'>监测站</td><td class='s2'>"+data.ms_code+"</td></tr>" +
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
            $.ajax({
                type:"post",
                url:"station/updateStation",
                data:form,
                processData:false,
                contentType:false,
                dataType:'json',
                success:function (data) {
                    if(data.data >= 0){
                        alert("更新成功");
                        window.location.href="station/listStation";
                    }
                }
            });
        }
        function xg1(){
            $("#xiugai").hide();
            $("#bg").show();
            $("h4").html("数据列表");
            $("#page").show();
            $("#Paging").show();
        }
        //添加
        function tj(){
            var form = new FormData(document.getElementById("addForm"));
            $.ajax({
                type:"post",
                url:"station/addStation",
                data:form,
                processData:false,
                contentType:false,
                dataType:'json',
                success:function (data) {
                    if(data.data >= 0){
                        alert("添加成功");
                        window.location.reload();
                    }
                }
            });
        }
        function tj1(){
            $("#tianjia").hide();
            $("#bg").show();
            $("#page").show();
            $("h4").html("数据列表");
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
                        window.location.reload();
                    }
                })
                alert("删除成功");
            });

        }
   
    </script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>

<div id="content_r">
    <li class="tit"><p class="xx"><img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span> > <span id="zb2">站点信息</span> > <span id="zb3">站点信息管理</span></p></li>
    <div class="gn">
        <form action="station/listStation" method="post">
           <!--  <span class="span1">监测站名称：</span><input type="text" name="ms_name" placeholder="不限" > -->
            <span class="span1">资金来源：</span>
                <select class="select1" name="ms_fp">
                    <option value="">不限</option>
                    <c:forEach items="${msFp }" var="fp">
                        <option value="${fp.data_value }">${fp.data_name }</option>
                    </c:forEach>
                </select>
            
            <span class="span1">监测站类型：</span>
                <select class="select1" name="ms_type">
                    <option value="">不限</option>
                    <c:forEach items="${msType }" var="type">
                        <option value="${type.data_value }">${type.data_name }</option>
                    </c:forEach>
                </select>
            <span  class="span1">监测站区域：</span>
                <select id="s_city" name="city">
                    <option value="">市区</option>
                </select>
                <select id="s_area" name="city" >
                    <option value="">地级市</option>
                </select>
           
            <span class="span1 wg">网关类型： </span>
                <select class="select1" name="ms_gate">
                    <option value="">不限</option>
                    <c:forEach items="${msGate }" var="gate">
                        <option value="${gate.data_value }">${gate.data_name }</option>
                    </c:forEach>
                </select>
           

            <input type="submit"  value="查询" class="cx"> <input type="button" value="添加监测站" class="cx" onclick="tianjia()"/>
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
                    <td class="t9">修改</td>
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
                                <td class="t6"><input type="button" value="查看" class="input1" onclick="chakan(this,${station.id})"></td>
                                <td class="t7"><input type="button" value="删除"  class="input2" onclick="delStation(${station.id})" ></td>
                                <td class="t9"><input type="button" value="修改"  class="input1"  onclick="xiugai(this,${station.id})"></td>
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
                <span>网关类型:</span>
                <select name="ms_gate" id="ms_gate">
                    <c:forEach items="${msGate }" var="gate">
                       <option value="${gate.data_value }">${gate.data_name }</option>
                    </c:forEach>
                </select><br/>
                <span id="ms_dev">建设内容:</span>
                <span >监测站描述:</span>
                    <textarea name="ms_desc" id="ms_desc"></textarea><span id="tip_5">格式正确</span>
                <input class="xg" type="button" value="修改" id="mdi" onclick="xg()">
                <input class="xg" type="button" value="取消"  onclick="xg1()" style="margin-left: 5%;">
            </form>
        </div>
        <div id="tianjia">
            <form id="addForm">
                <span>监测站名称:</span>
                <input type="text" id="tj_text_1" name="ms_name">
                <span id="tj_tip_1">
                        请输入2-50位字符
                    </span>
                <br/>
                <span>监测站编码:</span>
                <input type="text" id="tj_text_2" name="ms_code">
                <span id="tj_tip_2">
                        请输入8位数字
                    </span>
                <br/>
                <span>建设时间:</span>
                <input type="date" style="margin-left: 28px;" name="ms_date1">
    
                <br/>
                <span>监测站位置:</span>
    
                <input type="text" class="lxfs" id="tj_text_6" name="ms_place">
                 <span id="tj_tip_6">
                        请输入0-100位字符
                    </span>
                <br/>
                <span>使用单位，联系人，联系方式:</span>
    
                <input type="text" class="lxfs" id="tj_text_3" name="ms_user">
                 <span id="tj_tip_3">
                        请输入0-100位字符
                    </span>
                <br/>
                <span>施工单位，联系人，联系方式:</span>
                <input type="text" class="lxfs" id="tj_text_4" name="ms_builder">
                 <span id="tj_tip_4">
                        请输入0-100位字符
                    </span>
                <br/>
                <span >监测站类型:</span>
                <select style="margin-left: 1%;" name="ms_type">
                    <c:forEach items="${msType }" var="type">
                       <option value="${type.data_value }">${type.data_name }</option>
                    </c:forEach>
                </select>
                <br/>
                <span>资金来源:</span>
                <select name="ms_fp">
                    <c:forEach items="${msFp }" var="fp">
                       <option value="${fp.data_value }">${fp.data_name }</option>
                    </c:forEach>
                </select>
                <br/>
                <span>网络类型:</span>
                <select name="ms_net">
                    <c:forEach items="${msNet }" var="net">
                       <option value="${net.data_value }">${net.data_name }</option>
                    </c:forEach>
                </select>
                <br/>
                <span>网关类型:</span>
                <select name="ms_gate">
                    <c:forEach items="${msGate }" var="gate">
                       <option value="${gate.data_value }">${gate.data_name }</option>
                    </c:forEach>
                </select>
                <br/>
                <span id="ms_dev1" >建设内容:
                    <c:forEach items="${msDev }" var="dev" >
                       <input type="checkbox" name="ms_dev" class="gn" value="${dev.data_value }">${dev.data_name }
                    </c:forEach>
                </span>
                    
                <br/>
                <span >监测站描述:</span>
                <textarea id="tj_text_5" name="ms_desc"></textarea>
                 <span id="tj_tip_5">
                        请输入0-300位字符
                    </span>
                <input class="tj" type="button" value="添加" id="add" onclick="tj()">
                <input class="tj" type="button" value="取消"  onclick="tj1()" style="margin-left: 3%;">
            </form>
        </div>   
       
    </div>

</div>
<div id="footer">
    <li>济南农智信息科技有限公司所有&copy; &nbsp;电话：12345677  &nbsp;<a href="#">关于我们</a> &nbsp;<a href="#">售后服务</a></li>
</div>

<script>
    var reg2 =/^(\d{8})$/;
    var reg1=/^.{2,50}$/;
    var reg3=/^.{0,100}$/;
    var reg4=/^.{0,100}$/;
    var reg5=/^.{0,300}$/;
    var reg6=/^.{0,100}$/;
    $(function(){
        $("#ms_name").change(function(){
                    var str1=reg1.test($("#ms_name").val());
                    if(str1==true){
                        $("#tip_1").html("格式正确")
                    }else{
                        $("#tip_1").html("请输入2-50位字符");
                    }
                    $("#mdi").attr("disabled", true);
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
                        }else {
                            $("#tip_2").html("格式正确")
                            temp = 0;
                        }
                    });
            	}else {
            		$("#tip_2").html("格式正确")
                    temp = 0;
            	}
            }else{
                $("#tip_2").html("请输入8位数字");
            }
            $("#mdi").attr("disabled", true);
        })
        $("#ms_user").change(function(){
            var str3 = reg3.test($("#ms_user").val());
                if (str3 == true) {
                    $("#tip_3").html("格式正确")
                } else {
                    $("#tip_3").html("请输入0-100位字符");
                }
            $("#mdi").attr("disabled", true);
        })
        $("#ms_builder").change(function(){
                    var str4=reg4.test($("#ms_builder").val());
                    if(str4==true){
                        $("#tip_4").html("格式正确")
                    }else{
                        $("#tip_4").html("请输入0-100位字符");
                    }
                    $("#mdi").attr("disabled", true);
                })
        $("#ms_desc").change(function() {
                    var str5 = reg5.test($("#ms_desc").val());
                    if (str5 == true) {
                        $("#tip_5").html("格式正确")
                    } else {
                        $("#tip_5").html("请输入0-300位字符");
                    }
                    $("#mdi").attr("disabled", true);
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

    $(function(){
        $("#tj_text_1").change(function(){
            var str1 = reg1.test($("#tj_text_1").val());
            if (str1 == true) {
                $("#tj_tip_1").html("格式正确")
            } else {
                $("#tj_tip_1").html("请输入2-50位字符");
            }
            $("#add").attr("disabled", true);
        })
        var temp = 0;
        $("#tj_text_2").change(function(){
            var str2 = reg2.test($("#tj_text_2").val());
            if (str2 == true) {
            	var code = $("#tj_text_2").val();
                $.post("station/existMsCode",{ms_code:code},function(data){
                    if (data > 0) {
                        $("#tj_tip_2").html("编码已重复")
                        temp = 1;
                    }else {
                        $("#tj_tip_2").html("格式正确")
                        temp = 0;
                    }
                });
            } else {
                $("#tj_tip_2").html(" 请输入8位数字");
            }
            $("#add").attr("disabled", true);
        })
        $("#tj_text_3").change(function(){
            var str3 = reg3.test($("#tj_text_3").val());
            if (str3 == true) {
                $("#tj_tip_3").html("格式正确")
            } else {
                $("#tj_tip_3").html("请输入0-100位字符");
            }
            $("#add").attr("disabled", true);
        })
        $("#tj_text_4").change(function(){
            var str4 = reg4.test($("#tj_text_4").val());
            if (str4 == true) {
                $("#tj_tip_4").html("格式正确")
            } else {
                $("#tj_tip_4").html("请输入0-100位字符");
            }
            $("#add").attr("disabled", true);
        })
        $("#tj_text_5").change(function(){
            var str5 = reg5.test($("#tj_text_5").val());
            if (str5 == true) {
                $("#tj_tip_5").html("格式正确")
            } else {
                $("#tj_tip_5").html("请输入0-300位字符");
            }
            $("#add").attr("disabled", true);
        })
        $("#tj_text_6").change(function(){
            var str6 = reg5.test($("#tj_text_6").val());
            if (str6 == true) {
                $("#tj_tip_6").html("格式正确")
            } else {
                $("#tj_tip_6").html("请输入0-300位字符");
            }
            $("#add").attr("disabled", true);
        })
        $("#tianjia").children().children().change(function(){
            if(temp==0&&reg1.test($("#tj_text_1").val())==true&&reg2.test($("#tj_text_2").val())==true&&reg3.test($("#tj_text_3").val())==true&&reg4.test($("#tj_text_4").val())==true&&reg5.test($("#tj_text_5").val())==true&&reg6.test($("#tj_text_6").val())==true){
                $("#add").removeAttr("disabled", true);
            }
        })

    })
</script>

</body>
</html>