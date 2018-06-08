/**
 * Created by DELL on 2018/6/2.
 */
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

$(function(){
    $("#chakan").hide();
    $("#bg tbody tr").mouseover(function(){
        $(this).css("background","#ffff66");
    })
    $("#bg tbody tr").mouseout(function(){
        $(this).css("background","#EEF4F9");
    })
    $("#bg1 tr").mouseover(function(){
        $(this).css("background","#ffff66");
    })
    $("#bg1 tr").mouseout(function(){
        $(this).css("background","#EEF4F9");
    })
    $("#xiugai").hide();
    $("#tianjia").hide()
    $("#peizhi").hide();
})
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
function xiugai(obj){
    $(obj).parent().parent().parent().parent().hide();
    $("#xiugai").show();
    $("h4").html("修改信息");
    $("#caidan").css("height",height)
    $("#page").hide();
    $("#caidan").css("height","840px");
}

function guanbi(){
   $("#chakan").hide();
    $("#bg").show();
    $("#page").show();

}
function xg(){
    alert("修改成功")
    $("#xiugai").hide();
    $("#bg").show();
    $("h4").html("数据列表");
    $("#caidan").css("height",height);
    $("#page").show();
    $("#caidan").css("height","1020px");
}

function tianjia(){
    $("#bg").hide();
    $("#tianjia").show();
    $("h4").html("添加监测站");
    $("#page").hide();

}
function tj(){
    $("#tianjia").hide();
    $("#bg").show();
    $("#page").show();

}
function peizhi(obj){
    $(obj).parent().parent().parent().parent().hide();
    $("#peizhi").show();
    $("h4").html("配置");
    $("#caidan").css("height","700px");
    $("#page").hide();

}
function pz(){
    $("#peizhi").hide();
    $("#bg").show();
    $("h4").html("数据列表");
    $("#page").show();
    $("#caidan").css("height","1020px");

}
$(function(){
    $("#page").Page({
        totalPages: 10,//分页总数
        liNums: 7,//分页的数字按钮数(建议取奇数)
        activeClass: 'activP', //active 类样式定义
        callBack : function(page){
            //console.log(page)
        }
    });
})
// function del(){
//     $.MsgBox.Confirm("温馨提示", "执行删除后将无法恢复，确定继续吗？温馨提示", function () { alert("删除成功"); });
// }