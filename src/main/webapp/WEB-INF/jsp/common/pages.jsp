<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
    function jumpPage(pages) {
        $("#pages").val(pages);
        //var pageForm = ;
        $("#pageForm").action="${url}";
        $("#pageForm").method="post";
        $("#pageForm").submit();
    }
</script>
    <c:if test="${pages != null}">
        <div>
            <div>当前第<font color="red">${pages }</font>页，共 <font color="red">${sumPage }</font>页</div>
        </div>
    </c:if>
    <form target="_self" id="pageForm">
        <input type="hidden" id="pages" name="pages">
        
            <div id="page">
                <input onclick="jumpPage(1)" type="button" value="首页"/>
                <input onclick="jumpPage(${(pages-1<1)?1:(pages-1)})" type="button" value="上一页"/>
                <input onclick="jumpPage(${(pages+1>sumPage)?sumPage:(pages+1)})" type="button" value="下一页"/>
                <input onclick="jumpPage(${sumPage})" type="button" value="末页"/>
            </div>
    </form>
