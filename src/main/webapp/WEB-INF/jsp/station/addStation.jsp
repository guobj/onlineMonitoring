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
<title>站点信息新增</title>
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
       
        //添加
        function tj(){
            var form = new FormData(document.getElementById("addForm"));
            $.MsgBox.Confirm("消息", "添加成功");
            $.ajax({
                type:"post",
                url:"station/addStation",
                data:form,
                processData:false,
                contentType:false,
                dataType:'json',
                success:function (data) {
                    if(data.data >= 0){
                         $.MsgBox.Confirm("消息", "添加成功");
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
      
      function realData(ms_code){
          window.location.href="realData/listByMsCode?ms_code="+ms_code;
      }
    </script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>

<div id="content_r">
    <li class="tit"><p class="xx"><img src="img/zb.png">&nbsp;当前位置&nbsp;:&nbsp;<span id="zb1">首页</span> > <span id="zb2">站点管理</span> > <span id="zb3">站点信息新增</span></p></li>
    
        <div id="tianjia" style="display:block;!important;" >
            <form id="addForm">
                <span>监测站名称:</span>
                <input type="text" id="ms_name" name="ms_name">
                <span id="tj_tip_1">
                        请输入2-50位字符
                    </span>
                <br/>
                <span>监测站编码:</span>
                <input type="text" id="ms_code" name="ms_code" maxlength="8">
                <span id="tj_tip_2">
                        请输入8位数字
                    </span>
                <br/>
                <span>建设时间:</span>
                <input type="date" style="margin-left: 2.2%;" name="ms_date1" id="ms_date1" >
    
                <br/>
                <span>监测站位置:</span>
    
                <input type="text" class="lxfs" id="ms_place" name="ms_place">
                 <span id="tj_tip_6">
                        请输入0-100位字符
                    </span>
                <br/>
                <span>使用单位，联系人，联系方式:</span>
    
                <input type="text" class="lxfs" id="ms_user" name="ms_user">
                 <span id="tj_tip_3">
                        请输入0-100位字符 
                    </span>
                <br/>
                <span>施工单位，联系人，联系方式:</span>
                <input type="text" class="lxfs" id="ms_builder" name="ms_builder">
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
                    <c:forEach items="${msDev }" var="dev" varStatus="i">
                       
                        <input type="checkbox" name="ms_dev" class="gn" value="${dev.data_value }">${dev.data_name }
                    </c:forEach>
                </span>
                    
                <br/>
                <span >监测站描述:</span>
                <textarea id="ms_desc" name="ms_desc"></textarea>
                 <span id="tj_tip_5">
                        请输入0-300位字符
                    </span>
                <input class="tj" type="button" value="添加"  onclick="tj()" id="add">
                <!-- <input class="tj" type="button" value="取消"  onclick="tj1()" style="margin-left: 3%;"> -->
            </form>
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
    var x = document.getElementById("ms_date1").value;
    $(function(){
        $("#add").attr("disabled", true);
        $("#ms_name").change(function(){
                    var str1=reg1.test($("#ms_name").val());
                    if(str1==true){
                        $("#tj_tip_1").html("格式正确")
                        temp = 0;
                    }else{
                        $("#tj_tip_1").html("请输入2-50位字符");
                        $("#add").attr("disabled", true);
                        temp = 1;
                    }
                })

        var temp = 0;
        $("#ms_code").change(function(){
            var str2=reg2.test($("#ms_code").val());
            if(str2==true){
                var code = $("#ms_code").val();
                
                $.post("station/permissionMsCode",{ms_code:code},function(data){
                    if (data < 0) {
                        $("#tj_tip_2").html("权限不够，无法添加此编码")
                        temp = 1;
                        $("#add").attr("disabled", true);
                    }else {
                    	$.post("station/existMsCode",{ms_code:code},function(data){
                            if (data > 0) {
                                $("#tj_tip_2").html("编码已重复") 
                                temp = 1;
                                $("#add").attr("disabled", true);
                            }else {
                                $("#tj_tip_2").html("格式正确")
                                temp = 0;
                            }
                            if(temp==0&&reg1.test($("#ms_name").val())==true&&reg2.test($("#ms_code").val())==true&&reg3.test($("#ms_user").val())==true&&reg4.test($("#ms_builder").val())==true&&reg5.test($("#ms_desc").val())==true&&reg6.test($("#ms_place").val())==true&&x!=""){
                                $("#add").removeAttr("disabled", true);
                            }
                        });
                    }
                });
                
            }else{
                $("#tj_tip_2").html("请输入8位数字");
                $("#add").attr("disabled", true);
            }
            
        })
        $("#ms_date1").change(function(){
            $("#ms_date1").attr("value",$(this).val());
        });
        $("#ms_date1").change(function(){
            console.log(document.getElementById("ms_date1").value)
            x=document.getElementById("ms_date1").value;
        })
        $("#ms_user").change(function(){
            var str3 = reg3.test($("#ms_user").val());
                if (str3 == true) {
                    $("#tj_tip_3").html("格式正确")
               
                } else {
                    $("#tj_tip_3").html("请输入0-100位字符");
              
                    $("#add").attr("disabled", true);
                }
            
        })
        $("#ms_builder").change(function(){
                    var str4=reg4.test($("#ms_builder").val());
                    if(str4==true){
                        $("#tj_tip_4").html("格式正确")
                    }else{
                        $("#tj_tip_4").html("请输入0-100位字符"); 
                        $("#add").attr("disabled", true); 

                    }
                    
                })
        $("#ms_desc").change(function() {
                    var str5 = reg5.test($("#ms_desc").val());
                    if (str5 == true) {
                        $("#tj_tip_5").html("格式正确")
                    } else {
                        $("#tj_tip_5").html("请输入0-300位字符");
                        $("#add").attr("disabled", true);

                    }
                    
                }
        )

    $("#ms_place").change(function() {
        var str6 = reg6.test($("#ms_place").val());
        if (str6 == true) {
            $("#tj_tip_6").html("格式正确")
        } else {
            $("#tj_tip_6").html("请输入0-300位字符");
            $("#add").attr("disabled", true);
        }
    })
    $("#tianjia").children().children().change(function(){
        if(temp==0&&reg1.test($("#ms_name").val())==true&&reg2.test($("#ms_code").val())==true&&reg3.test($("#ms_user").val())==true&&reg4.test($("#ms_builder").val())==true&&reg5.test($("#ms_desc").val())==true&&reg6.test($("#ms_place").val())==true&&x!="" ){
            $("#add").removeAttr("disabled", true);
        }

    })

    })


</script>

</body>
</html>