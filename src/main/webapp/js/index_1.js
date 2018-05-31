// JavaScript Document
$(function(){
    //时间
    var date=new Date();
    $("#year").html(date.getFullYear())
    $("#month").html(date.getMonth()+1)
    $("#dat").html(date.getDate()+1)
    $("#day").html(date.getDay())
    //菜单收起
    $("#a_1").click(function(){
        $("#c1").toggle()
    })
    $("#a_2").click(function(){
        $("#c2").toggle()
    })
    $("#a_3").click(function(){
        $("#c3").toggle()
    })
})


