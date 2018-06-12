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
    <script src="js/jquery.js"></script>
    <script src="js/delete.js"></script>
    <script src="js/sbgl.js" ></script>
    <script type="text/javascript">
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
                    <td class="t2">${list.station.ms_name}</td>
                    <td class="t3" >${list.ms_code}</td>
                    <td class="t4">${list.dev_code}</td>
                    <td class="t5">${list.dataProtocol.data_name}</td>
                    <td class="t6">${list.dataInterface.data_name}</td>
                    <td class="t7">${list.dev_regad}</td>
                    <td class="t8">${list.dev_ip}</td>
                    <td><input type="button" onclick="moreInfo(${list.id})" value="更多"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
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
    <li>济南农智信息科技有限公司所有&copy; &nbsp;电话：12345677  &nbsp;<a href="#">关于我们</a> &nbsp;<a href="#">售后服务</a></li>
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