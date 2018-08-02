/**
 * Created by DELL on 2018/6/2.
 */


$(function(){

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
        $(this).css("background","#fff4e5");
    })
    $("#bg tbody tr").mouseout(function(){
        $(this).css("background","#EEF4F9");
    })
    $("#bg1 tr").mouseover(function(){
        $(this).css("background","#fff4e5");
    })
    $("#bg1 tr").mouseout(function(){
        $(this).css("background","#EEF4F9");
    })
    $("#xiugai").hide();

    $("#peizhi").hide();
})
$(function(){
    $("#chakan").hide();
    $("#bg tbody tr").mouseover(function(){
        $(this).css("background","#fff4e5");
    })
    $("#bg tbody tr").mouseout(function(){
        $(this).css("background","#EEF4F9");
    })
    $("#bg1 tr").mouseover(function(){
        $(this).css("background","#fff4e5");
    })
    $("#bg1 tr").mouseout(function(){
        $(this).css("background","#EEF4F9");
    })
    $("#xiugai").hide();

    $("#peizhi").hide();
})
function chakan(obj){
    $(obj).parent().parent().parent().parent().hide();
    $("#chakan").show();
    $("h4").html("");
    $("#page").hide();
    $("#Paging").hide();

}
function xiugai(obj){
    $(obj).parent().parent().parent().parent().hide();
    $("#xiugai").show();
    $("h4").html("");
    $("#caidan").css("height",height)
    $("#page").hide();
}

function guanbi(){
   $("#chakan").hide();
    $("#bg").show();
    $("#page").show();

}
function xg(){
	 $.MsgBox.Confirm("温馨提示", "确认修改？温馨提示", function () {  $.MsgBox.Alert("消息", "修改成功");});
    $("#xiugai").hide();
    $("#bg").show();
    $("h4").html("数据列表");
    $("#caidan").css("height",height);
    $("#page").show();
}


function peizhi(obj,id){
    $(obj).parent().parent().parent().parent().hide();
    $("#manageId").val(id);
    $("#peizhi").show();
    $("#Paging").hide();
    $("h4").html("");
    $("#page").hide();

}
function pz(){
    $("#peizhi").hide();
    $("#bg").show();
    $("h4").html("");
    $("#page").show();

}
function del(){
    $.MsgBox.Confirm("温馨提示", "执行删除后将无法恢复，确定继续吗？温馨提示",function () {   $.MsgBox.Alert("消息", "删除成功");});
}

function close1()
{
    $("#peizhi").hide();
    $("#bg").show();
    $("#page").show();
}