<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript">
        function jumpPage(pages) {
            var pageForm = document.getElementById("pageForm");
            document.getElementById("pages").value=pages;
            pageForm.action="${url}";
            pageForm.method="post";
            pageForm.submit();
        }
</script>
<form target="_self" id="pageForm">
    <input type="hidden" name="pages" id="pages">
    <%-- <c:if test="${user.user_name!=null }">
        <input type="hidden" name="user_name" value="${user.user_name }">
    </c:if>
    <c:if test="${user.user_account!=null }">
        <input type="hidden" name="user_account" value="${user.user_account }">
    </c:if>
     <c:if test="${role.role_name!=null }">
        <input type="hidden" name="role_name" value="${role.role_name }">
    </c:if>
    <c:if test="${client.cli_name!=null }">
        <input type="hidden" name="cli_name" value="${client.cli_name }">
    </c:if>
    <c:if test="${client.user_name!=null }">
        <input type="hidden" name="user_name" value="${client.user_name }">
    </c:if>
    <c:if test="${goods.goods_name!=null }">
        <input type="hidden" name="goods_name" value="${goods.goods_name }">
    </c:if>
    <c:if test="${indent.indent_name!=null }">
        <input type="hidden" name="indent_name" value="${indent.indent_name }">
    </c:if>
    <c:if test="${contract.client_name!=null }">
        <input type="hidden" name="client_name" value="${contract.client_name }">
    </c:if>
    <c:if test="${client_message.client_name!=null }">
        <input type="hidden" name="client_name" value="${client_message.client_name }">
    </c:if>
     --%>
</form>
<div id="page">
        <input type="button" value="首页" onclick="jumpPage(1)" class="butt"/>
        <input type="button" value="上一页" onclick="jumpPage(${(pages-1<1)?1:(pages-1)})"/>
        <input type="button" value="下一页" onclick="jumpPage(${(pages+1>sumPage)?sumPage:(pages+1)})"/>
        <input type="button" value="末页" onclick="jumpPage(${sumPage})"/>
    </div>