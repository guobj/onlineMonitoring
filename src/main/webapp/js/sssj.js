/**
 * Created by DELL on 2018/6/18.
 */
$(function(){
    //日期
    var date=new Date();
    $("#year").html(date.getFullYear());
    $("#month").html(date.getMonth()+1);
    $("#dat").html(date.getDate()+1);

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
    
    //$("#picture .con").children().on("click",".min",function(){
    //    $(this).toggleClass("min");
    //    $(this).toggleClass("max");
    //})
    //
    //$("#picture .con").children().on("click",".max",function(){
    //    $(this).toggleClass("min");
    //    $(this).toggleClass("max");
    //})
    
})
// function preview(img){
//        $("#picture .con img").attr("src",$(img).attr("src"));
//        $("#picture .con img").attr("jqimg",$(img).attr("bimg"));
//        var url=$("#picture .con img").attr("src");
//    }

/*图片弹窗*/
$(function(){
	
	var btn = document.getElementById('open-btn');
	
	var div = document.getElementById('imga');
	
	var close = document.getElementById('close_btn');
	
	btn.onclick = function show() {
		
		div.style.display = "block";
		
	}
	close.onclick = function close() {
		
		div.style.display = "none";
		
	}
	window.onclick = function close(e) {
		
		if (e.target == div) {
			
			div.style.display = "none";
			
		}
	}
})
