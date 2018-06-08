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
function chakan(obj){
    $(obj).parent().parent().parent().parent().hide();
    $("#chakan").show();
    $("#page").hide();

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