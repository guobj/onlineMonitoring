// JavaScript Document
$(function(){
    //日期
    var date=new Date();
    $("#year").html(date.getFullYear());
    $("#month").html(date.getMonth()+1);
    $("#dat").html(date.getDate()+1);
    $("#day").html(date.getDay());
    if($("#day").html("1")){
        $("#day").html("一");
    }else if(($("#day").html("2"))){
        $("#day").html("二");
    }else if(($("#day").html("3"))){
        $("#day").html("三");
    }
    else if(($("#day").html("4"))){
        $("#day").html("四");
    }
    else if(($("#day").html("5"))){
        $("#day").html("五");
    }
    else if(($("#day").html("6"))){
        $("#day").html("六");
    }
    else if(($("#day").html("0"))){
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

    function Dsy(){
  this.Items = {};
}
Dsy.prototype.add = function(id,iArray){
  this.Items[id] = iArray;
}
Dsy.prototype.Exists = function(id){
  if(typeof(this.Items[id]) == "undefined") return false;
  return true;
}

function change(v){
  var str="0";
  for(i=0;i<v;i++){
    str+=("_"+(document.getElementById(s[i]).selectedIndex-1));
  };
  var ss=document.getElementById(s[v]);
  with(ss){
    length = 0;
    options[0]=new Option(opt0[v],opt0[v]);
    if(v && document.getElementById(s[v-1]).selectedIndex>0 || !v){
      if(dsy.Exists(str)){
        ar = dsy.Items[str];
        for(i=0;i<ar.length;i++){
          options[length]=new Option(ar[i],ar[i]);
        }//end for
        if(v){ options[0].selected = true; }
      }
    }//end if v
    if(++v<s.length){change(v);}
  }//End with
}
//级联操作
var dsy = new Dsy();

dsy.add("0",["济南市","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","莱芜市","临沂市","德州市","聊城市","滨州市","菏泽市"]);
dsy.add("0_0",["市辖区","历下区","市中区","槐荫区","天桥区","历城区","长清区","平阴县","济阳县","商河县","章丘区"]);
dsy.add("0_1",["市辖区","市南区","市北区","黄岛区","崂山区","李沧区","城阳区","胶州市","即墨市","平度市","莱西市"]);
dsy.add("0_2",["市辖区","淄川区","张店区","博山区","临淄区","周村区","桓台县","高青县","沂源县"]);
dsy.add("0_3",["市辖区","市中区","薛城区","峄城区","台儿庄区","山亭区","滕州市"]);
dsy.add("0_4",["市辖区","东营区","河口区","垦利区","利津县","广饶县"]);
dsy.add("0_5",["市辖区","芝罘区","福山区","牟平区","莱山区","长岛县","龙口市","莱阳市","莱州市","蓬莱市","招远市","栖霞市","海阳市"]);
dsy.add("0_6",["市辖区","潍城区","寒亭区","坊子区","奎文区","临朐县","昌乐县","青州市","诸城市","寿光市","安丘市","高密市","昌邑市"]);
dsy.add("0_7",["市辖区","任城区","兖州区","微山县","鱼台县","金乡县","嘉祥县","汶上县","泗水县","梁山县","曲阜市","邹城市"]);
dsy.add("0_8",["市辖区","泰山区","岱岳区","宁阳县","东平县","新泰市","肥城市"]);
dsy.add("0_9",["市辖区","环翠区","文登区","荣成市","乳山市"]);
dsy.add("0_10",["市辖区","东港区","岚山区","五莲县","莒  县"]);
dsy.add("0_11",["市辖区","莱城区","钢城区"]);
dsy.add("0_12",["市辖区","兰山区","罗庄区","河东区","沂南县","郯城县","沂水县","兰陵县","费  县","平邑县","莒南县","蒙阴县","临沭县"]);
dsy.add("0_13",["市辖区","德城区","陵城区","宁津县","庆云县","临邑县","齐河县","平原县","夏津县","武城县","乐陵市","禹城市"]);
dsy.add("0_14",["市辖区","东昌府区","阳谷县","莘  县","茌平县","东阿县","冠  县","高唐县","临清市"]);
dsy.add("0_15",["市辖区","滨城区","沾化区","惠民县","阳信县","无棣县","博兴县","邹平县"]);
dsy.add("0_16",["市辖区","牡丹区","定陶区","曹  县","单  县","成武县","巨野县","郓城县","鄄城县","东明县"]);
dsy.add("0",["济南市","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","莱芜市","临沂市","德州市","聊城市","滨州市","菏泽市"]);


var s=["s_province","s_city"];//三个select的name
var opt0 = ["市区","地级市"];//初始值
function _init_area(){ //初始化函数
  for(i=0;i<s.length-1;i++){
   document.getElementById(s[i]).onchange=new Function("change("+(i+1)+")");
  }
  change(0);
}
