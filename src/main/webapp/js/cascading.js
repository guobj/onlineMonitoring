$(function () {
    var res = parseInt(account / 100);
    console.log("account1:"+account);
    // 判断是否为省级账号
    if(!account.toString().endsWith("0000")){
        // 判断是否为市级账号
        if(account.toString().endsWith("00")){
            $.post(
                "data/listCity",
                function(data) {
                    for(var city in data){
                        $("#s_city").append("<option value="+data[city].data_value+">"+data[city].data_name+"</option>");
                        if(res == data[city].data_value){
                            $("#s_city option[value='"+res+"']").attr("selected",true);
                            $("#s_city").attr("disabled", 'disabled');
                        }
                    }
                },
                "json"
            )
            $.post(
                "data/listArea",
                {city_id:res},
                function(data) {
                    for(var area in data){
                        $("#s_area").append("<option value="+data[area].data_value+">"+data[area].data_name+"</option>");
                    }

                },
                "json"
            )
        }else{
            console.log("account2:"+account);
            $.post(
                "data/listCity",
                function(data) {
                    console.log(data);
                    for(var city in data){
                        debugger
                        $("#s_city").append("<option value="+data[city].data_value+">"+data[city].data_name+"</option>");
                        if(res == data[city].data_value){
                            console.log(data[city].data_value);
                            $("#s_city option[value='"+res+"']").attr("selected",true);
                            $("#s_city").attr("disabled", 'disabled');
                        }
                    }
                },
                "json"
            )
            $.post(
                "data/listArea",
                {city_id:res},
                function(data) {
                    for(var area in data){
                        $("#s_area").append("<option value="+data[area].data_value+">"+data[area].data_name+"</option>");
                        if(account == data[area].data_value){
                            $("#s_area option[value='"+account+"']").attr("selected",true);
                            $("#s_area").attr("disabled", 'disabled');
                        }
                    }

                },
                "json"
            )
        }
    }else{
        $.post(
            "data/listCity",
            function(data) {
                for(var city in data){
                    console.log(data[city].data_value);
                    $("#s_city").append("<option value="+data[city].data_value+">"+data[city].data_name+"</option>");
                }
            },
            "json"
        )
        $("#s_city").change(function() {
            $("#s_area").html('<option value="">不限</option>');
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
    }
})