// JavaScript Document
$(function(){
    //����
    var date=new Date();
    $("#year").html(date.getFullYear());
    $("#month").html(date.getMonth()+1);
    $("#dat").html(date.getDate()+1);

    var day=date.getDay();

    if(day=="1"){
        $("#day").html("һ");
    }else if((day=="2")){
        $("#day").html("��");
    }else if(day=="3"){
        $("#day").html("��");
    }
    else if(day=="4"){
        $("#day").html("��");
    }
    else if(day=="5"){
        $("#day").html("��");
    }
    else if(day=="6"){
        $("#day").html("��");
    }
    else if(day=="0"){
        $("#day").html("��");
    }
    //�˵�
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
$(function(){
    $("#page").Page({
        totalPages: 10,//��ҳ����
        liNums: 7,//��ҳ�����ְ�ť��(����ȡ����)
        activeClass: 'activP', //active ����ʽ����
        callBack : function(page){
            //console.log(page)
        }
    });
})
function look(){
    $("#img").show()
}
function clos(){
    $("#img").hide()
}