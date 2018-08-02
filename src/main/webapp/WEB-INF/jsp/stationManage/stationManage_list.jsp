<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("path", path);
pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <base href="${basePath }">
    <meta charset="UTF-8">
    <title>站点信息配置</title>
    <link href="css/xinxi.css" rel="stylesheet" type="text/css">
    <link href="css/page.css" rel="stylesheet" type="text/css">
    <script src="js/jquery.js"></script>
    <script src="js/delete.js" type="text/javascript"></script>
    <script src="js/xinxi.js" ></script>
    <script type="text/javascript" src="js/queryLike.js"></script>
    <%--<script type="text/javascript" src="js/cascading.jsp"></script>--%>
    <script src="js/cascading.js" charset="UTF-8"></script>
    <script type="text/javascript">
        var account = ${sessionScope.user.account};
        //模糊查询调用方法
        function submit() {
            var form = new FormData(document.getElementById("like"));
            $("#s_city").removeAttr("disabled");
           // console.log(text);
           //  form.append("content",text);
            $.ajax({
                type:"post",
                url:"manage/queryStationInfo",
                data:form,
                processData:false,
                contentType:false,
                success:function (data) {
                    window.location.reload();
                }
            });
        }
        //查看调用
        function look(obj,id){
            $(obj).parent().parent().parent().parent().hide();
            $("#chakan").show();
            $("#page").hide();
            $("#Paging").hide();
            $.ajax({
                type:"get",
                url:"manage/load",
                data:{id:id},
                success:function (data) {
                    console.log("测试："+data.data.station.ms_fp_value.data_name);
                    $("#ms_name").html(data.data.station.ms_name);
                    $("#ms_code").html(data.data.ms_code);
                    $("#ms_date").html(data.data.station.ms_date);
                    $("#manage_ms_dev").html(data.data.station.ms_dev_value);
                    $("#ms_type1").html(data.data.station.dict.data_name);
                    $("#ms_fp1").html(data.data.station.ms_fp_value.data_name);
                    $("#ms_place").html(data.data.station.ms_place);
                    $("#ms_position").html(data.data.station.ms_longitude+","+data.data.station.ms_latitude);
                    $("#ms_user").html(data.data.station.ms_user);
                    $("#ms_builder").html(data.data.station.ms_builder);
                    $("#ms_net1").html(data.data.station.ms_net_value.data_name);
                    $("#ms_gate1").html(data.data.station.ms_gate_value.data_name);
                    $("#ms_desc").html(data.data.station.ms_desc);
                }
            });
        }
        //删除方法
        function deleteById(id){
            $.MsgBox.Confirm("温馨提示", "执行删除后将无法恢复，确定继续吗？温馨提示", function () {
                $.ajax({
                    type:"get",
                    url:"manage/deleteById",
                    data:{id:id},
                    success:function (data) {
                        window.location.reload();
                    }
                })
                $.MsgBox.Alert("消息", "删除成功");
            });

        }
        //修改方法
        function updateById() {
            var form = new FormData(document.getElementById("update"));
            $.ajax({
                type:"post",
                url:"manage/updateById",
                data:form,
                processData:false,
                contentType:false,
                dataType:'json',
                success:function (data) {
                    if(data.data >= 1){
                        $.MsgBox.Alert("消息", "修改成功");
                    }
                }
            });
        }
        //配置方法
        function conf() {
            var form = new FormData(document.getElementById("conf"));
            $.ajax({
                type:"post",
                url:"manage/updateById",
                data:form,
                processData:false,
                contentType:false,
                dataType:'json',
                success:function (data) {
                    if(data.data >= 1){
                        $.MsgBox.Alert("消息", "配置成功");
                    }
                }
            });
        }
        //调出div
        function update(obj,id){
            $(obj).parent().parent().parent().parent().hide();
            $("#peizhi").show();
            $("h4").html("");
            $("#caidan").css("height","700px");
            $("#page").hide();
            $("#Paging").hide();
            $.ajax({
                type:"get",
                url:"manage/load",
                data:{id:id},
                success:function (data) {
                    $("#manageId").val(id);
                    $("#ms_code1").val(data.data.ms_code);
                    $("#server_ip").val(data.data.server_ip);
                    $("#data_upload").val(data.data.data_upload);
                    $("#server_port").val(data.data.server_port);
                    $("#data_storage").val(data.data.data_storage);
                }
            });
        }

    </script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<div id="content_r">
    <li class="tit"><p class="xx"><img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span> > <span id="zb2">站点管理</span> > <span id="zb3">站点信息配置</span></p></li>
    <div class="gn">
        <form id="like" method="post">
            <%--<span class="span1">监测站名称：</span><input name="ms_name" type="text" placeholder="不限" >--%>
            <span class="span1">监测站区域：</span>
                <select id="s_city" name="city">
                    <option value="">市区</option>
                </select>
                <select id="s_area" name="city">
                    <option value="">区县</option>
                </select>
            <span class="span1">监测站类型：</span>
                <select id="ms_type" name="station.ms_type">
                    <option value="">不限</option>
                </select>
            <span class="span1">资金来源：</span>
                <select id="ms_fp" name="station.ms_fp">
                    <option value="">不限</option>
                </select>
           <span class="span1">建站时间： </span>
                <input type="date" name="date_begin1" style="width:13%;">--<input type="date" name="date_end1" style="width:13%;">
           
            <input type="button"  value="查询" class="search" onclick="submit()">
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
                <td class="t6">查看</td>
                <td class="t7">删除</td>
                <td class="t8">配置</td>
                <%--<td class="t9">修改</td>--%>
            </tr>
            </thead>
            <tbody>
            <c:if test="${stationInfoList eq null}" >
                <tr><td colspan="8" style="text-align: center;"><font color="red" size="4">${message }</font> </td></tr>
            </c:if>
            <c:if test="${stationInfoList != null}">
                <c:forEach var="list" items="${stationInfoList}">
                    <tr>
                        <td class="t3">${list.station.ms_name}</td>
                        <td class="t2">${list.ms_code}</td>
                        <td class="t4">${list.station.ms_dev_value}</td>
                        <td class="t5">${list.station.dict.data_name}</td>
                        <td class="t6"><input type="button" value="查看" class="input1" onclick="look(this,${list.id})"></td>
                        <td class="t7"><input type="button" value="删除"  class="input2" onclick="deleteById(${list.id})"></td>
                        <td class="t8"><input type="button" value="配置" class="input1" onclick="peizhi(this,${list.id})"></td>
                        <%--<td class="t9"><input type="button" value="修改"  class="input1"  onclick="update(this,${list.id})"></td>--%>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <jsp:include page="../common/pages.jsp"></jsp:include>
        <div id="chakan">
            <table id="bg1">
                <tr>
                    <td class="s1">监测站名称</td>
                    <td class="s2" id="ms_name"></td>
                </tr>
                <tr>
                    <td class="s1">监测站编码</td>
                    <td class="s2" id="ms_code"></td>
                </tr>
                <tr>
                    <td class="s1">建设时间</td>
                    <td class="s2" id="ms_date"></td>
                </tr>
                <tr>
                    <td class="s1">建设内容</td>
                    <td class="s2" id="manage_ms_dev"></td>
                </tr>
                <tr>
                    <td class="s1">监测站类型</td>
                    <td class="s2" id="ms_type1"></td>
                </tr>
                <tr>
                    <td class="s1">资金来源</td>
                    <td class="s2" id="ms_fp1"></td>
                </tr>
                <tr>
                    <td class="s1">监测站位置</td>
                    <td class="s2" id="ms_place"></td>
                </tr>
                <tr>
                    <td class="s1">经纬度</td>
                    <td class="s2" id="ms_position"></td>
                </tr>
                <tr>
                    <td class="s1">使用单位，联系人，联系方式</td>
                    <td class="s2" id="ms_user"></td>
                </tr >
                <tr>
                    <td class="s1">施工单位，联系人，联系方式</td>
                    <td class="s2" id="ms_builder"></td>
                </tr>
                <tr>
                    <td class="s1">网络类型</td>
                    <td class="s2" id="ms_net1"></td>
                </tr>
                <tr>
                    <td class="s1">网关类型</td>
                    <td class="s2" id="ms_gate1"></td>
                </tr>
                <tr>
                    <td class="s1">监测站描述</td>
                    <td class="s2" id="ms_desc"></td>
                </tr>
            </table>
            <form>
                <input type="button" value="关闭" onclick="guanbi()" />
            </form>
        </div>
        <%--<div id="peizhi">--%>

            <%--<form method="post" id="update">--%>
                <%--&lt;%&ndash;<span>监测站名称:</span><input type="text" name=""/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<br/>&ndash;%&gt;--%>
                <%--<input type="hidden" id="manageId" name="id">--%>
                <%--<span>监测站编码:</span><input type="text" id="ms_code1" name="ms_code"/>--%>
                <%--<br/>--%>
                <%--<span style="margin-right: 16px;">上传地址:</span><input type="text" id="server_ip" name="server_ip"/>--%>
                <%--<br/>--%>
                <%--<span style="margin-right: 16px;">上传频率:</span><input type="text" id="data_upload" name="data_upload"/>--%>
                <%--<br/>--%>
                <%--<span style="margin-right: 28px;">端口号:</span> <input type="text" id="server_port" name="server_port"/>--%>
                <%--<br/>--%>
                <%--<span style="margin-right: 16px;">存储周期:</span><input type="text" id="data_storage" name="data_storage"/>--%>
                <%--<br/>--%>
                <%--<input class="pz" type="button" value="修改" onclick="updateById()">--%>
                <%--<input  type="button" value="关闭" class="close" onclick="pz()">--%>

            <%--</form>--%>
        <%--</div>--%>

        <div id="peizhi">
            <h5>配置信息</h5>
            <form method="post" id="conf">
                <%--<span>监测站名称:</span><input type="text" name=""/>--%>
                <%--<br/>--%>
                    <input type="hidden" id="manageId" name="id">
                <span>监测站编码:</span><input type="text" id="ms_code1" placeholder="请输入以37开头的8位数字" name="ms_code"/><span id="tip1"></span>
                <br/>
                <span style="margin-right: 16px;">上传地址:</span><input type="text" id="server_ip" placeholder="请输入IP地址" name="server_ip"/><span id="tip2"></span>
                <br/>
                <span style="margin-right: 16px;">上传频率:</span><input type="text" id="data_upload" placeholder="请输入上传频率" name="data_upload"/><span id="tip3"></span>
                <br/>
                <span style="margin-right: 28px;">端口号:</span> <input type="text" id="server_port" placeholder="请输入端口号" name="server_port"/><span id="tip4"></span>
                <br/>
                <span style="margin-right: 16px;">存储周期:</span><input type="text" id="data_storage" placeholder="请输入存储周期" name="data_storage"/><span id="tip5"></span>
                <br/>
                <input id="confSubmit" class="pz" type="button" value="配置" onclick="conf()">
                <input class="pz pz1" type="button" value="关闭" onclick="close1()">
                <%--<input  type="button" value="关闭" class="close" onclick="pz()">--%>
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
    $("#xiugai").children().change(function(){

        var str1=reg1.test($("#text_1").val());
        var str2=reg2.test($("#text_2").val());
        var str3=reg3.test($("#text_3").val());
        var str4=reg4.test($("#text_4").val());
        var str5=reg5.test($("#text_5").val());

        if(str1==true){
            $("#tip_1").html("格式正确")
        }else{
            $("#tip_1").html("请输入2-50位字符");
        }
        if(str2==true){
            $("#tip_2").html("格式正确")
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
    })
    $("#tianjia").children().change(function(){
        var str1=reg1.test($("#tj_text_1").val());
        var str2=reg2.test($("#tj_text_2").val());
        var str3=reg3.test($("#tj_text_3").val());
        var str4=reg4.test($("#tj_text_4").val());
        var str5=reg5.test($("#tj_text_5").val());

        if(str1==true){
            $("#tj_tip_1").html("格式正确")
        }else{
            $("#tj_tip_1").html("请输入2-50位字符");
        }
        if(str2==true){
            $("#tj_tip_2").html("格式正确")
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
    })
    var pat1 = /^(37)\d{6}$/
    var ip= /^(?=(\b|\D))(((\d{1,2})|(1\d{1,2})|(2[0-4]\d)|(25[0-5]))\.){3}((\d{1,2})|(1\d{1,2})|(2[0-4]\d)|(25[0-5]))(?=(\b|\D))$/;
    var port=/(^[0-9]\d{0,3}$)|(^[1-5]\d{4}$)|(^6[0-4]\d{3}$)|(^65[0-4]\d{2}$)|(^655[0-2]\d$)|(^6553[0-5]$)/;
    var reg=/^\d.*$/;
    $("#ms_code1").change(function(){
        var str1=pat1.test($("#ms_code1").val());
        if(str1==true){
            $("#tip1").html("格式正确");
            $("#confSubmit").removeAttr('disabled');
        }else{
            $("#tip1").html("请输入以37开头的8位数字");
            $("#confSubmit").attr("disabled",'disabled');
        }
    })
    $("#server_ip").change(function(){
        var str2=ip.test($("#server_ip").val())
        if(str2==true){
            $("#tip2").html("格式正确");
            $("#confSubmit").removeAttr('disabled');
        }else{
            $("#tip2").html("请输入正确的IP地址");
            $("#confSubmit").attr("disabled",'disabled');
        }
    })
    $("#data_upload").change(function(){
        var str3=reg.test($("#data_upload").val())

        if(str3==true){
            $("#tip3").html("格式正确");
            $("#confSubmit").removeAttr('disabled');
        }else{
            $("#tip3").html("请输入整数");
            $("#confSubmit").attr("disabled",'disabled');
        }
    })
    $("#server_port").change(function(){
        var str3=port.test($("#server_port").val())

        if(str3==true){
            $("#tip4").html("格式正确");
            $("#confSubmit").removeAttr('disabled');
        }else{
            $("#tip4").html("请输入0~65535");
            $("#confSubmit").attr("disabled",'disabled');
        }
    })
    $("#data_storage").change(function(){
        var str3=reg.test($("#data_storage").val())

        if(str3==true){
            $("#tip5").html("格式正确");
            $("#confSubmit").removeAttr('disabled');
        }else{
            $("#tip5").html("请输入整数");
            $("#confSubmit").attr("disabled",'disabled');
        }
    })
</script>

</body>
</html>