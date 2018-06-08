$(function() {
    $.post(
        "data/listCity",
        function(data) {
            for(var city in data){
                $("#s_city").append("<option value="+data[city].data_value+">"+data[city].data_name+"</option>");
            }
        },
        "json"
    )
    $("#s_city").change(function() {
        // $("#city").html("<option value=''>市区</option>");
        $.post(
            "data/listArea",
            {city_id:$("#s_city").val()},
            function(data) {
                for(var area in data){
                    $("#s_area").append("<option value="+data[area].data_value+">"+data[area].data_name+"</option>");
                }

            },
            "json"
        )
    })
});