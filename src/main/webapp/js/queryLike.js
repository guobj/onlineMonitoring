$(function () {
    $.ajax({
        type:"post",
        url:"data/queryTypeAndFpAndGate",
        dataType: "JSON",
        success:function (data) {
            for(var type in data.data.msTypes){
                $("#ms_type").append("<option value="+data.data.msTypes[type].data_value+">"+data.data.msTypes[type].data_name+"</option>");
            }
            for(var fp in data.data.msFps){
                $("#ms_fp").append("<option value="+data.data.msFps[fp].data_value+">"+data.data.msFps[fp].data_name+"</option>");
            }
            for(var gate in data.data.msGates){
                $("#ms_gate").append("<option value="+data.data.msGates[gate].data_value+">"+data.data.msGates[gate].data_name+"</option>");
            }
        }
    });
})