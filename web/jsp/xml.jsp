<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 19.06.2018
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="resources.text">
<html>
<head>
    <title>
        <fmt:message key="title.xml"/>
    </title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:set var="pagePath" value="jsp/xml.jsp"/>
<fmt:message key="button.main_page" var="button_main_page"/>
<%@include file="parts/header.jsp" %>
<div align="right">
    <form action="/XMLServlet" method="get">
        <input type="hidden" name="commandType" value="TO_MAIN_PAGE_COMMAND"/>
        <input type="submit" value="${button_main_page}" class="nice-button"/>
    </form>
</div>
<div align="center" style="height: 100%; align-content: center">
    <embed src="${pageContext.request.contextPath}/res/someDevices.xml" width="100%" style="height: 100%;"/>
</div>
</body>
</html>
</fmt:bundle>