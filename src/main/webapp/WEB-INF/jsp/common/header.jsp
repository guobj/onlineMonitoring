<%--
  Created by IntelliJ IDEA.
  User: guobj
  Date: 2018/6/8
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit">
    <p>山东省病虫害物联网监测系统</p>
    <ul>
        <li style="width:8.7%;"><img src="img/yh.png"></li>
        <li>欢迎您，Admin. 今天是<span id="year"></span>年<span id="month"></span>月<span id="dat"></span>日，星期<span id="day"></span>.</li>
        <li style=" margin-top:11px; width:100%; background:url(img/sbg.png) no-repeat; height:32px;">
            <ul class="three">
                <a href="station/listStation"><img src="img/fz.png">&nbsp;返回首页</a>
                <a id="change-pwd"><img src="img/bg2.png">&nbsp;修改密码</a>
                <a href="loginOut"><img src="img/gb.png" style="margin-top:7px;">&nbsp;退出系统</a>
            </ul>
        </li>
    </ul>
</div>
<div id="caidan">
    <ul>
        <li class="tit">
            <img src="img/gl1.png">
            &nbsp;管理菜单
        </li>
        <li class="cdq">
            <img src="img/bf.png">
            <a>功能菜单区</a>
        </li>
    <div class="cd_4">
        <ul id="ceNav">
            <li>
                <a href="javascript:;" class="a_1"> <img src="img/xt3.png">&nbsp;站点管理</a>
                <ul class="zdgl" id="c1">
                    <li><a href="station/listStation" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;监测站点查询</a>
                    </li>
                    <li><a href="manage/queryStationInfo" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;站点信息配置</a></li>
                    <li><a href="station/addStation" class="a_2"><img src="img/xxtb.jpg">&nbsp;站点信息新增</a></li>
                </ul>
            </li>
            <li>
                <a href="javascript:;" class="a_1"><img src="img/xt3.png">&nbsp;监测设备管理</a>
                <ul class="zdgl" id="c2">
                    <li><a href="device/queryDeviceInfo" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;设备信息管理</a></li>
                    <li><a href="vDevStatus/devcieStatusList" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;设备状态查询</a></li>
                    <li><a href="device/queryDeviceInfoList" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;设备信息查询</a></li>
                </ul>
            </li>
            <li>
                <a href="javascript:;" class="a_1"><img src="img/xt3.png">&nbsp;监测数据管理</a>
                <ul class="zdgl" id="c3">
                    <li><a href="realData/listRealData" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;实时数据查询</a></li>
                    <li><a href="hisData/listHisData" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;历史数据查询</a></li>
                </ul>
            </li>
        </ul>
    </div>
    </ul>
</div>
<div id="change_psw">
		<div class="title"><h3>修改密码</h3></div>
		<form id="mdiPassword">
			<label>请输入原密码：</label><input type="password" name="old_password" id="old_password"><span style="color:red; font-size:12px;" id="pwd_tip1">与原密码不一致</span><br/>
			<label>请输入新密码：</label><input type="password" id="new_password1"><span style="color:red; font-size:10px;" id="pwd_tip3">请输入6-16位数字或字母</span><br/>
			<label>请确认新密码：</label><input type="password" name="password" id="new_password2"><span style="color:red; font-size:12px;" id="pwd_tip2">两次密码不一致</span><br/>
			<input type="button" onclick="mdiPassword()" value="确定" class="sure" id="add"><input type="button" value="取消" class="cancel" id="cancel">
		</form>
</div>
<script type="text/javascript">
var psw="${sessionScope.user.password}";

$(function(){

	$("#add").attr("disabled", true);
	var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/;
	var temp=0;
	
	$("#old_password").change(function(){
		var old_password=document.getElementById('old_password').value;
	
		if(old_password!=psw){
			$("#pwd_tip1").html("请重新输入旧密码")
			temp=1;
		}else{
			$("#pwd_tip1").html("原密码正确")
			temp=0;
		}
	})
	$("#new_password1").change(function(){
		var new_password1=document.getElementById('new_password1').value;
		console.log(reg.test(new_password1))
		if(reg.test(new_password1)==true&& new_password1 != psw)
		{

			$("#pwd_tip3").html("格式正确");
			temp=0
		}else if(new_password1 == psw){
			$("#pwd_tip3").html("与原密码一致,请重新输入");
			temp=1;
		}else{
			$("#pwd_tip3").html("请输入6-16位数字或字母");
			temp=1
			};
			
		var new_password2=document.getElementById('new_password2').value;
		if(new_password1 !=new_password2){
			$("#pwd_tip2").html("两次密码不一致")
			temp=1;
		}else{
			$("#pwd_tip2").html("两次密码一致")
			temp=0;
		}
		var old_password=document.getElementById('old_password').value;
	})
	$("#new_password2").change(function(){
		var new_password1=document.getElementById('new_password1').value;
		var new_password2=document.getElementById('new_password2').value;
		if(new_password1 !=new_password2){
			$("#pwd_tip2").html("两次密码不一致")
			temp=1;
		}else{
			$("#pwd_tip2").html("两次密码一致")
			temp=0;
		}
	})

	$("#mdiPassword").children("input").change(function(){

		var new_password1=document.getElementById('new_password1').value;
		if(temp==0&&reg.test(new_password1)==true){

			 $("#add").removeAttr("disabled", true);
		
		}
	})
})

//修改
        function mdiPassword(){
            var form = new FormData(document.getElementById("mdiPassword"));
            $.MsgBox.Confirm("温馨提示", "确认修改？温馨提示", function () {  
	            $.ajax({
	                type:"post",
	                url:"mdiPassword",
	                data:form,
	                processData:false,
	                contentType:false,
	                dataType:'json',
	                success:function (data) {
	                	$.MsgBox.Alert("消息", data.data);
	                	 var div = document.getElementById("change_psw");
	                	 div.style.display = "none";
	                }
	            });
            });
            
        }
    


</script>