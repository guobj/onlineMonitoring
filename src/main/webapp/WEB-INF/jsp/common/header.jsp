<%--
  Created by IntelliJ IDEA.
  User: guobj
  Date: 2018/6/8
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <li><a href="station/listStation" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;站点信息查询</a></li>
                    <li><a href="manage/queryStationInfo" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;站点配置管理</a></li>
                </ul>
            </li>
            <li>
                <a href="javascript:;" class="a_1"><img src="img/xt3.png">&nbsp;监测设备管理</a>
                <ul class="zdgl" id="c2">
                    <li><a href="device/queryDeviceInfo" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;设备信息管理</a></li>
                    <li><a href="device/queryDeviceInfo" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;设备状态查看</a></li>
                    <li><a href="device/queryDeviceInfoList" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;设备信息查询</a></li>
                </ul>
            </li>
            <li>
                <a href="javascript:;" class="a_1"><img src="img/xt3.png">&nbsp;监测数据管理</a>
                <ul class="zdgl" id="c3">
                    <li><a href="realData/listRealData" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;实时数据查看</a></li>
                    <li><a href="#" target="_self" class="a_2"><img src="img/xxtb.jpg">&nbsp;历史数据查询功能</a></li>
                </ul>
            </li>
        </ul>
    </div>
    </ul>
</div>