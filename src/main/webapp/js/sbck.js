﻿/**
 * Created by DELL on 2018/6/4.
 */
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
    $("#img").hide();
});
function check(obj){
    $("#img").show();
}
function clos(){
    $("#img").hide();
}

$(function(){
	$("tbody tr:even").css("background","#fff4e5");
	$("tbody tr:odd").css("background","#EEF4F9");
})




$(function () {
    // $("#imga").children().click(function(){
    //     console.log("aaa");
    //     $(this).toggleClass("min");
    //     $(this).toggleClass("max");
    // })
    $("#imga").on("click",".min",function () {
        $(this).toggleClass("min");
        $(this).toggleClass("max");
    })
    $("#imga").on("click",".max",function () {
        $(this).toggleClass("min");
        $(this).toggleClass("max");
    })
})