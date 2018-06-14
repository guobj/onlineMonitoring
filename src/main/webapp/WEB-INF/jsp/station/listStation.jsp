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
            $("#Paging").hide();
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
            $("#caidan").css("height",height)
            $("#page").hide();
            $("#Paging").hide();
            $("#caidan").css("height","840px");
            $("#xiugai").html("");
            $.post("station/getStation",{id:id},function(data){
                if(data!=null){
                    $("#xiugai").append("<form id='updateForm'>"+
                            "<input name='id' type='hidden' value='"+data.data.station.id+"'><br/>" +
                            "<span>监测站名称:</span><input name='ms_name' type='text' id='text_1' value='"+data.data.station.ms_name+"'><span id='tip_1'>格式正确</span><br/>" +
                            "<span>监测站编码:</span><input name='ms_code' type='text' id='text_2' value='"+data.data.station.ms_code+"'><span id='tip_2'>格式正确</span><br/>" +
                            "<span>建设时间:</span><input name='ms_date1' type='date' value='"+data.data.station.ms_date+"' style='margin-left: 28px;'><br/>" +
                            "<span>监测站位置:</span><input name='ms_place' type='text' id='text_6' value='"+data.data.station.ms_place+"'><span id='tip_6'>格式正确</span><br/>" +
                            "<span>使用单位，联系人，联系方式:</span><input name='ms_user' type='text' id='text_3' value='"+data.data.station.ms_user+"' class='lxfs'><span id='tip_3'>格式正确</span><br/>" +
                            "<span>施工单位，联系人，联系方式:</span><input name='ms_builder' type='text' id='text_4' value='"+data.data.station.ms_builder+"' class='lxfs'><span id='tip_4'>格式正确</span><br/>" +
                            "<span >监测站类型:</span>" +
                            "<select name='ms_type' id='ms_type' style='margin-left: 1%;'>"+ 
                                "<option value='"+data.data.station.ms_type+"'>"+data.data.station.ms_type_value.data_name+"</option>" + 
                            "</select><br/>" +
                            "<span>资金来源:</span>" +
                            "<select name='ms_fp' id='ms_fp'>"+
                                "<option value='"+data.data.station.ms_fp+"'>"+data.data.station.ms_fp_value.data_name+"</option>" +
                            "</select><br/>" +
                            "<span>网络类型:</span>" +
                            "<select name='ms_net' id='ms_net'>" +
                                "<option value='"+data.data.station.ms_net+"'>"+data.data.station.ms_net_value.data_name+"</option>" +
                            "</select><br/>" +
                            "<span>网关类型:</span>" +
                            "<select name='ms_gate' id='ms_gate'>" +
                                "<option value='"+data.data.station.ms_gate+"'>"+data.data.station.ms_gate_value.data_name+"</option>" +
                            "</select><br/>" +
                            "<span>建设内容:</span>" +
                                "<span id='ms_dev'></span>" +
                            "<span >监测站描述:</span>" +
                                "<textarea name='ms_desc' id='text_5'>"+data.data.station.ms_desc+"</textarea><span id='tip_3'>格式正确</span>" +
                            "<input class='xg' type='button' value='修改' id='mdi' onclick='xg()'>" +
                            "<input class='xg' type='button' value='取消'  onclick='xg1()' style='margin-left: 5%;'>"+
                            "</form>"
                    );
                    
                    for (let i = 0, n = data.data.msType.length; i < n; i++) {
                        $("#ms_type").append("<option value='"+data.data.msType[i].data_value+"'>"+data.data.msType[i].data_name+"</option>");
                    }
                    for (let i = 0, n = data.data.msFp.length; i < n; i++) {
                        $("#ms_fp").append("<option value='"+data.data.msFp[i].data_value+"'>"+data.data.msFp[i].data_name+"</option>");
                    }
                    for (let i = 0, n = data.data.msGate.length; i < n; i++) {
                        $("#ms_gate").append("<option value='"+data.data.msGate[i].data_value+"'>"+data.data.msGate[i].data_name+"</option>");
                    }
                    for (let i = 0, n = data.data.msNet.length; i < n; i++) {
                        $("#ms_net").append("<option value='"+data.data.msNet[i].data_value+"'>"+data.data.msNet[i].data_name+"</option>");
                    }
                    for (let i = 0, n = data.data.msDev.length; i < n; i++) {
                        $("#ms_dev").append("<input name='ms_dev' type='checkbox' id='msDev"+data.data.msDev[i].data_value+"' class='gn' value='"+data.data.msDev[i].data_value+"'>"+data.data.msDev[i].data_name+"");
                    } 
                    var msDev = data.data.station.ms_dev.split(",");
                    for (let i = 0,n = msDev.length; i < n; i++) {
                         $("#msDev"+msDev[i]).attr("checked","checked");
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
                    if(data.data >= 1){
                        alert("更新成功");
                        window.location.href="station/listStation";
                    }
                }
            });
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
                    if(data.data >= 1){
                        alert("添加成功");
                        window.location.reload();
                    }
                }
            });
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
    <li class="tit"><p class="xx"><img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span> > <span id="zb2">站点管理</span> > <span id="zb3">站点信息查询</span></p></li>
    <div class="gn">
        <form action="station/listStation" method="post">
            <%--<span class="span1">监测站名称：</span><input type="text" name="ms_name" placeholder="不限" >--%>
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
                
            <span class="span1">网关类型： </span>
                <select class="select1" name="ms_gate">
                    <option value="">不限</option>
                    <c:forEach items="${msGate }" var="gate">
                        <option value="${gate.data_value }">${gate.data_name }</option>
                    </c:forEach>
                </select>
           

            <input type="submit"  value="查询" class="search"> <input type="button" value="添加监测站" class="reset" onclick="tianjia()"/>
        </form>
    </div>


    <h5>数据列表：</h5>

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
                                <td class="t8"><input type="button" value="配置" class="input1" onclick="peizhi(this)"></td>
                                <td class="t9"><input type="button" value="修改"  class="input1"  onclick="xiugai(this,${station.id})"></td>
                            </tr>
                        </c:forEach>
                     </c:if>
                </tbody>
            </table>
            <jsp:include page="../common/pages.jsp"></jsp:include>
        <div id="chakan"></div>
        <div id="xiugai"></div>
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
                <span>监测性质:</span>
                    <c:forEach items="${msDev }" var="dev" >
                       <input type="checkbox" name="ms_dev" class="gn" value="${dev.data_value }">${dev.data_name }
                    </c:forEach>
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
    var reg6=/^.{0,300}$/;
    $(function(){
    	
    })
    
    $("#xiugai").children().change(function(){
            var str1=reg1.test($("#text_1").val());
            var str2=reg2.test($("#text_2").val());
            var str3=reg3.test($("#text_3").val());
            var str4=reg4.test($("#text_4").val());
            var str5=reg5.test($("#text_5").val());
            var str6=reg6.test($("#text_6").val());
            if(str1==true){
                $("#tip_1").html("格式正确")
            }else{
                $("#tip_1").html("请输入2-50位字符");
            }
            if(str2==true){
                var code = $("#text_2").val();
                $.post("station/existMsCode",{ms_code:code},function(data){
                    if (data >= 0) {
                        $("#tip_2").html("编码已重复")
                    }else {
                        $("#tip_2").html("格式正确")
                    }
                });
            }else{
                $("#tip_2").html("请输入8位数字");
            }
            if(str3==true){
                $("#tip_3").html("格式正确")
            }else{
                $("#tip_3").html("请输入0-100位字符");
            }
            if(str4==true){
                $("#tip_4").html("格式正确")
            }else{
                $("#tip_4").html("请输入0-100位字符");
            }
            if(str5==true){
                $("#tip_5").html("格式正确")
            }else{
                $("#tip_5").html("请输入0-300位字符");
            }
            if(str6==true){
                $("#tip_6").html("格式正确")
            }else{
                $("#tip_6").html("请输入0-300位字符");
            }
        })
    $("#tianjia").children().change(function(){
        var str1=reg1.test($("#tj_text_1").val());
        var str2=reg2.test($("#tj_text_2").val());
        var str3=reg3.test($("#tj_text_3").val());
        var str4=reg4.test($("#tj_text_4").val());
        var str5=reg5.test($("#tj_text_5").val());
        var str6=reg6.test($("#tj_text_6").val());
        
        if(str1==true){
            $("#tj_tip_1").html("格式正确")
        }else{
            $("#tj_tip_1").html("请输入2-50位字符");
        }
        if(str2==true){
            var code = $("#tj_text_2").val();
            $.post("station/existMsCode",{ms_code:code},function(data){
                if (data >= 0) {
                    $("#tj_tip_2").html("编码已重复")
                }else {
                    $("#tj_tip_2").html("格式正确")
                }
            });
        }else{
            $("#tj_tip_2").html("请输入8位数字");
        }
        if(str3==true){
            $("#tj_tip_3").html("格式正确")
        }else{
            $("#tj_tip_3").html("请输入0-100位字符");
        }
        if(str4==true){
            $("#tj_tip_4").html("格式正确")
        }else{
            $("#tj_tip_4").html("请输入0-100位字符");
        }
        if(str5==true){
            $("#tj_tip_5").html("格式正确")
        }else{
            $("#tj_tip_5").html("请输入0-300位字符");
        }
        if(str6==true){
            $("#tj_tip_6").html("格式正确")
        }else{
            $("#tj_tip_6").html("请输入0-300位字符");
        }
    })
</script>
</body>
</html>