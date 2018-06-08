$(function(){
    city();
    $("#s_city").change(function city(){
        var city_id = $(this).val();
        $("#s_area").html("<option value=''>地级市</option>");
        $.post("data/listArea",{city_id:city_id},function(data){
            if(data!=null&&data.length>0){
                for(var i=0;i<data.length;i++){
                    $("#s_area").append("<option value="+data[i].data_value+">"+data[i].data_name+"</option>");
                }
            }
        });
    });
})
function city(){
    $.post("data/listCity",function(data){
        if(data!=null&&data.length>0){
            for(var i=0;i<data.length;i++){
                $("#s_city").append("<option value="+data[i].data_value+">"+data[i].data_name+"</option>");
            }
        }
    })
}