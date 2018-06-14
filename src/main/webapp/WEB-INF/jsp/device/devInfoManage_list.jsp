<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    pageContext.setAttribute("path", path);
    pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE HTML>
<html>
<head lang="en">
    <base href="${basePath }">
    <meta charset="UTF-8">
    <title></title>
    <link href="css/sbxxgl.css" rel="stylesheet" type="text/css">
    <link href="css/page.css" rel="stylesheet" type="text/css">
    <script src="js/jquery.js"></script>
    <script src="js/delete.js"></script>
    <script src="js/sbgl.js" ></script>
    <script src="js/cascading.js" ></script>
    <script type="text/javascript">
        var account = ${sessionScope.user.account};
        function moreInfo(id) {
            $.ajax({
                type:"post",
                url:"device/deviceLoad",
                data:{id:id},
                dataType: "JSON",
                success:function (data) {
                    $("#manageId").val(data.data.id);
                    if(data.data.station != null){
                        $("#ms_name").html(data.data.station.ms_name);
                    }
                    $("#ms_code").html(data.data.ms_code);
                    $("#dev_code").html(data.data.dev_code);
                    if(data.data.dataProtocol != null){
                        $("#dev_protocol").html(data.data.dataProtocol.data_name);
                    }
                    if(data.data.dataInterface != null){
                        $("#dev_interface").html(data.data.dataInterface.data_name);
                    }
                    $("#dev_regad").html(data.data.dev_regad);
                    $("#dev_ip").html(data.data.dev_ip);
                    $("#dev_port").html(data.data.dev_port);
                    $("#zbqx").html(data.data.dev_warraty);
                    $("#sbcj").html(data.data.dev_mfrs);
                    $("#sbms").html(data.data.dev_desc);
                }
            });
        }
        
        //修改质保期限，设备厂家，设备描述
        function update() {
            $.ajax({
                type:"post",
                url:"device/updateById",
                data:{"id":$("#manageId").val(),
                    "dev_warraty":$("#in").val(),
                    "dev_mfrs":$("#in1").val(),
                    "dev_desc":$("#in2").val()
                },
                dataType: "JSON",
                success:function (data) {
                }
            });
        }
        $(function () {
            $.ajax({
                type:"post",
                url:"data/queryDevType",
                dataType: "JSON",
                success:function (data) {
                    for(var o in data.data.devObject){
                        $("#dev_object").append("<option value="+data.data.devObject[o].data_value+">"+data.data.devObject[o].data_name+"</option>");
                    }
                    for(var type in data.data.devType){
                        $("#dev_type").append("<option value="+data.data.devType[type].data_value+">"+data.data.devType[type].data_name+"</option>");
                    }
                }
            });
        });
    </script>
</head>

<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<div id="content_r">
    <li class="tit"><p class="xx"><img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span> > <span id="zb2">监测设备管理</span> > <span id="zb3">设备信息管理</span></p></li>
    <div class="menu">
        <form>
            <%--<span>监测站名称：<input name="ms_name" type="text"/></span>--%>
            <span>设备类型：
                <select class="select1" name="dev_type" id="dev_type">
                    <option value="">不限</option>
                </select></span>
            <span>监测站编码：
                <select id="s_city" name="city">
                    <option value="">市区</option>
                </select>
                <select id="s_area" name="city" >
                    <option value="">区县</option>
                </select>
            </span>
            <span>监测对象：
                <select class="select1" name="dev_object" id="dev_object">
                    <option value="">不限</option>
                </select>
            </span>
            <input type="button" value="查找" class="search"><input type="reset" value="重置" class="reset">

        </form>
    </div>
    <h4>信息列表</h4>
    <br/>
    <table id="xx">
        <thead>
            <tr>
                <td>监测站名称</td>
                <td>监测站编码</td>
                <td>设备编码</td>
                <td>通信协议</td>
                <td>通讯接口</td>
                <td>从机地址</td>
                <td>IP地址</td>
                <td>更多</td>
            </tr>
        </thead>
        <tbody>
            <c:if test="${list eq null}">
                <tr><td colspan="11" style="text-align: center;"><font color="red" size="4">${message }</font> </td></tr>
            </c:if>
            <c:forEach var="list" items="${list}">
                <tr>
                    <td>${list.station.ms_name}</td>
                    <td>${list.ms_code}</td>
                    <td>${list.dev_code}</td>
                    <td>${list.dataProtocol.data_name}</td>
                    <td>${list.dataInterface.data_name}</td>
                    <td>${list.dev_regad}</td>
                    <td>${list.dev_ip}</td>
                    <td><input type="button" onclick="moreInfo(${list.id})" value="更多"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <jsp:include page="../common/pages.jsp"></jsp:include>
    <div id="detail">
        <table id="bg1">
            <<input type="hidden" id="manageId">
            <tr>
                <td class="s1">监测站名称</td>
                <td class="s2" id="ms_name"></td>
            </tr>
            <tr>
                <td class="s1">监测站编码</td>
                <td class="s2" id="ms_code"></td>
            </tr>
            <tr>
                <td class="s1">设备编码</td>
                <td class="s2" id="dev_code"></td>
            </tr>
            <tr>
                <td class="s1">通信协议</td>
                <td class="s2" id="dev_protocol"></td>
            </tr>
            <tr>
                <td class="s1">通讯接口</td>
                <td class="s2" id="dev_interface"></td>
            </tr>
            <tr>
                <td class="s1">从机地址</td>
                <td class="s2" id="dev_regad"></td>
            </tr>
            <tr>
                <td class="s1">IP地址</td>
                <td class="s2" id="dev_ip"></td>
            </tr>
            <tr>
                <td class="s1">通信端口</td>
                <td class="s2" id="dev_port"></td>
            </tr>
            <tr>
                <td class="s1">质保期限</td>
                <td class="s2" id="zbqx"></td>
            </tr>
            <tr>
                <td class="s1">设备厂家、品牌、服务型号</td>
                <td class="s2" id="sbcj"></td>
            </tr>
            <tr>
                <td class="s1">设备描述</td>
                <td class="s2" id="sbms"></td>
            </tr>
        </table>
        <form action="" method="post">
            <input type="button" onclick="" value="修改" id="update"/>
            <input type="button" value="关闭" id="cl"/>
            <input type="button" value="保存" id="save" style="display: none"/>
            <input type="button" value="取消" id="esc" style="display: none"/>
        </form>

    </div>
</div>

<div id="footer">
    <p>山东省植物保护总站</p>
</div>
<script>
    $(function(){

        $("#xx input[type='button']").click(function(){
            $(this).parent().parent().parent().parent().hide();
            $("#detail").show();
        })
        $("#cl").click(function(){
            $("#detail").hide();
            $("#xx").show();
        })
    })
    $(function(){

        $("#xx input[type='button']").click(function(){
            $(this).parent().parent().parent().parent().hide();
            $("#detail").show();
        })
        $("#cl").click(function(){
            $("#detail").hide();
            $("#xx").show();
        })
        $("#update").click(function(){
            var tr=document.getElementById("zbqx")
            con=tr.innerHTML;
            con="<input type='text' autofocus id='in' value='"+con+"' class='s3'>";
            tr.innerHTML=con;
            var tr1=document.getElementById("sbcj")
            con1=tr1.innerHTML;
            con1="<input type='text' id='in1' value='"+con1+"' class='s3'>";
            tr1.innerHTML=con1;
            var tr2=document.getElementById("sbms");
            con2=tr2.innerHTML;
            con2="<input type='text' id='in2' value='"+con2+"' class='s3'>";
            tr2.innerHTML=con2;
            $("#update").hide()
            $("#cl").hide()
            $("#save").show()
            $("#esc").show()

        })
        $("#save").click(function(){
            var inp=document.getElementById("in").value;
            var p=document.getElementById("zbqx");
            p.innerHTML=inp;
            var inp1=document.getElementById("in1").value;
            var p1=document.getElementById("sbcj");
            p1.innerHTML=inp1;
            var inp2=document.getElementById("in2").value;
            var p2=document.getElementById("sbms");
            p2.innerHTML=inp2;
            $.MsgBox.Confirm("温馨提示", "确认保存？温馨提示", function () {
                console.log("设备："+$("#in").val()+$("#in1").val()+$("#in2").val())
                $.ajax({
                    type:"post",
                    url:"device/updateById",
                    data:{"id":$("#manageId").val(),
                        "dev_warraty":inp,
                        "dev_mfrs":inp1,
                        "dev_desc":inp2
                    },
                    dataType: "JSON",
                    success:function (data) {
                    }
                });
                $.MsgBox.Alert("消息", "保存成功");});
            $("#update").show()
            $("#cl").show()
            $("#save").hide()
            $("#esc").hide()
        })
        $("#esc").click(function(){
            var inp=document.getElementById("in").value;
            var p=document.getElementById("zbqx");
            p.innerHTML=inp;
            var inp1=document.getElementById("in1").value;
            var p1=document.getElementById("sbcj");
            p1.innerHTML=inp1;
            var inp2=document.getElementById("in2").value;
            var p2=document.getElementById("sbms");
            p2.innerHTML=inp2;
            $("#update").show()
            $("#cl").show()
            $("#save").hide()
            $("#esc").hide()

        })

    })

</script>
</body>
</html>