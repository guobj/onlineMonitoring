<%--
  Created by IntelliJ IDEA.
  User: guobj
  Date: 2018/6/8
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit">
    <p>山东省病虫害物联网监测系统</p>
    <ul>
        <li style="width:8.7%;"><img src="img/yh.png"></li>
        <li>欢迎您，Admin. 今天是<span id="year"></span>年<span id="month"></span>月<span id="dat"></span>日，星期<span id="day"></span>.</li>
        <li style=" margin-top:11px; width:100%; background:url(img/sbg.png) no-repeat; height:32px;">
            <ul class="three">
                <a href="station/listStation"><img src="img/fz.png">&nbsp;返回首页</a>
                <a href="#"><img src="img/bg2.png">&nbsp;个人信息</a>
                <a href="loginOut"><img src="img/gb.png" style="margin-top:7px;">&nbsp;退出系统</a>
            </ul>
        </li>
    </ul>
</div>
<div id="caidan">
    <ul>
        <li class="tit">
            <img src="img/gl1.png">
            &nbsp;管理菜单
        </li>
        <li class="cdq">
            <img src="img/bf.png">
            <a>功能菜单区</a>
        </li>
    <div class="cd_4">
        <ul id="ceNav">
            <li>
                <a href="javascript:;" class="a_1"> <img src="img/xt3.png">&nbsp;站点管理</a>
                <ul class="zdgl" id="c1">
                    <li><a href="station/listStation" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;监测站点查询</a>
                    </li>
                    <li><a href="manage/queryStationInfo" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;站点信息配置</a></li>
                    <li><a href="station/addStation" class="a_2"><img src="img/xxtb.jpg">&nbsp;站点信息新增</a></li>
                </ul>
            </li>
            <li>
                <a href="javascript:;" class="a_1"><img src="img/xt3.png">&nbsp;监测设备管理</a>
                <ul class="zdgl" id="c2">
                    <li><a href="device/queryDeviceInfo" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;设备信息管理</a></li>
                    <li><a href="vDevStatus/devcieStatusList" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;设备状态查询</a></li>
                    <li><a href="device/queryDeviceInfoList" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;设备信息查询</a></li>
                </ul>
            </li>
            <li>
                <a href="javascript:;" class="a_1"><img src="img/xt3.png">&nbsp;监测数据管理</a>
                <ul class="zdgl" id="c3">
                    <li><a href="realData/listRealData" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;实时数据查询</a></li>
                    <li><a href="hisData/listHisData" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;历史数据查询</a></li>
                </ul>
            </li>
        </ul>
    </div>
    </ul>
</div>