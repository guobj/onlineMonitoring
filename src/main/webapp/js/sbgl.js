/**
 * Created by DELL on 2018/6/10.
 */
/**
 * Created by DELL on 2018/6/4.
 */
$(function(){
    var height=$("#content_r").height();
    //����
    var date=new Date();
    $("#year").html(date.getFullYear());
    $("#month").html(date.getMonth()+1);
    $("#dat").html(date.getDate());

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
    $(" tbody tr").mouseover(function(){
        $(this).css("background","#fff4e5");
    })
    $(" tbody tr").mouseout(function(){
        $(this).css("background","#EEF4F9");
    })
})

