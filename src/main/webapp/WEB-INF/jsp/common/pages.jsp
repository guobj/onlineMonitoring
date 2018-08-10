<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
    function jumpPage(pages) {
        $("#pages").val(pages);
        document.getElementById("pageForm").action="/onlineMonitoring${url}";
        document.getElementById("pageForm").method="post";
        //$("#pageForm")[0].action="/onlineMonitoring${url}";
        //$("#pageForm")[0].method="post";
        document.getElementById("pageForm").submit();
    }
</script>
    <c:if test="${pages != null}">
        <div>
            <div id="Paging" style="margin-right: 10%; float:right; margin-top:15px;">当前第<font color="red">${pages }</font>页，共 <font color="red">${sumPage }</font>页</div>
        </div>
    </c:if>
    <form target="_self" id="pageForm" method="post">
        <input type="hidden" id="pages" name="pages">
        <%--站点配置管理--%>
        <c:if test="${manage.ms_code!=null and manage.ms_code.trim()!='' }">
            <input type="hidden"  name="ms_code" value="${manage.ms_code }">
        </c:if>
        <c:if test="${manage.station != null and manage.station.ms_type != null }">
            <input type="hidden"  name="station.ms_type" value="${manage.station.ms_type}">
        </c:if>
        <c:if test="${manage.station != null and manage.station.ms_fp != null }">
            <input type="hidden"  name="station.ms_fp" value="${manage.station.ms_fp }">
        </c:if>
        <c:if test="${manage.station != null and manage.station.ms_net != null }">
            <input type="hidden"  name="station.ms_net" value="${manage.station.ms_net }">
        </c:if>
        <c:if test="${manage.station != null and manage.station.date_begin != null }">
            <input type="hidden"  name="date_begin1" value="${manage.station.date_begin }">
        </c:if>
        <c:if test="${manage.station != null and manage.station.date_end != null }">
            <input type="hidden"  name="date_end1" value="${manage.station.date_end }">
        </c:if>
        <!--  监测的查询 -->
        <c:if test="${station != null and station.ms_name != null }">
            <input type="hidden"  name="ms_name" value="${station.ms_name }">
        </c:if>
        <c:if test="${station != null and station.ms_fp != null }">
            <input type="hidden"  name="ms_fp" value="${station.ms_fp }">
        </c:if>
        <c:if test="${station != null and station.ms_type != null }">
            <input type="hidden"  name="ms_type" value="${station.ms_type }">
        </c:if>
        <c:if test="${station != null and station.ms_code != null }">
            <input type="hidden"  name="ms_code" value="${station.ms_code }">
        </c:if>
        <c:if test="${station != null and station.ms_gate != null }">
            <input type="hidden"  name="ms_gate" value="${station.ms_gate }">
        </c:if>
        <c:if test="${station != null and station.date_begin != null }">
            <input type="hidden"  name="date_begin1" value="${station.date_begin }">
        </c:if>
        <c:if test="${station != null and station.date_end != null }">
            <input type="hidden"  name="date_end1" value="${station.date_end }">
        </c:if>
       <!--  实时数据查询 -->
       <c:if test="${realdata != null and realdata.device_type != null }">
            <input type=hidden  name="device_type" value="${realdata.device_type }">
        </c:if>
        <c:if test="${realdata != null and realdata.dev_status != null }">
            <input type="hidden"  name="dev_status" value="${realdata.dev_status }">
        </c:if>
        <c:if test="${realdata != null and realdata.device_object != null }">
            <input type="hidden"  name="device_object" value="${realdata.device_object }">
        </c:if>
        <c:if test="${realdata != null and realdata.dev_code != null }">
            <input type="hidden"  name="dev_code" value="${realdata.dev_code }">
        </c:if>
       <c:if test="${realdata != null and realdata.ms_code != null }">
            <input type="hidden"  name="ms_code" value="${realdata.ms_code }">
        </c:if>
       <!--  历史数据查询 -->
        <c:if test="${hisdata != null and hisdata.device_type != null }">
            <input type="hidden"  name="device_type" value="${hisdata.device_type }">
        </c:if> 
        <c:if test="${hisdata != null and hisdata.dev_status != null }">
            <input type="hidden"  name="dev_status" value="${hisdata.dev_status }">
        </c:if>
        <c:if test="${hisdata != null and hisdata.dev_code != null }">
            <input type="hidden"  name="dev_code" value="${hisdata.dev_code }">
        </c:if>
        <c:if test="${hisdata != null and hisdata.device_object != null }">
            <input type="hidden"  name="device_object" value="${hisdata.device_object }">
        </c:if>
        <c:if test="${hisdata != null and hisdata.ms_code != null }">
            <input type="hidden"  name="ms_code" value="${hisdata.ms_code }">
        </c:if>
        <c:if test="${hisdata != null and hisdata.data_time_begin != null }">
            <input type="hidden"  name="data_time_begin1" value="${hisdata.data_time_begin }">
        </c:if>
        <c:if test="${hisdata != null and hisdata.data_time_end != null }">
            <input type="hidden"  name="data_time_end1" value="${hisdata.data_time_end }">
        </c:if>
        <%--设备信息查询--%>
        <c:if test="${device.ms_code!=null and device.ms_code!=''}">
            <input type="hidden"  name="ms_code" value="${device.ms_code }">
        </c:if>
        <c:if test="${device.dev_code!=null and device.dev_code!=''}">
            <input type="hidden"  name="dev_code" value="${device.dev_code }">
        </c:if>
        <c:if test="${device.dev_type!=null and device.dev_type!=''}">
            <input type="hidden"  name="dev_type" value="${device.dev_type }">
        </c:if>
        <c:if test="${device.controller_ms_code!=null and device.controller_ms_code!=''}">
            <input type="hidden"  name="controller_ms_code" value="${device.controller_ms_code }">
        </c:if>
        <%--设备状态查看--%>
        <c:if test="${vdevstatus.ms_code!=null and vdevstatus.ms_code!=''}">
            <input type="hidden"  name="ms_code" value="${vdevstatus.ms_code }">
        </c:if>
        <c:if test="${vdevstatus.dev_code!=null and vdevstatus.dev_code!=''}">
            <input type="hidden"  name="dev_code" value="${vdevstatus.dev_code }">
        </c:if>
        <c:if test="${vdevstatus.dev_type!=null and vdevstatus.dev_type!=''}">
            <input type="hidden"  name="dev_type" value="${vdevstatus.dev_type }">
        </c:if>
        <c:if test="${vdevstatus.controller_ms_code!=null and vdevstatus.controller_ms_code!=''}">
            <input type="hidden"  name="controller_ms_code" value="${vdevstatus.controller_ms_code }">
        </c:if>
            <div id="page">
                <input onclick="jumpPage(1)" type="button" value="首页"/>
                <input onclick="jumpPage(${(pages-1<1)?1:(pages-1)})" type="button" value="上一页"/>
                <input onclick="jumpPage(${(pages+1>sumPage)?sumPage:(pages+1)})" type="button" value="下一页"/>
                <input onclick="jumpPage(${sumPage})" type="button" value="末页"/>
            </div>
    </form>
