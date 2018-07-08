<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13.06.2018
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="resources.text">
<html>
<head>
    <title>
        <fmt:message key="title.app"/>
    </title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">


</head>
<body>
<c:set var="pagePath" value="jsp/app.jsp"/>
<%@include file="parts/header.jsp" %>
    <fmt:message key="button.log_out" var="button_log_out"/>
    <fmt:message key="button.parse" var="button_parse"/>
<div align="right">
    <form action="/XMLServlet" method="get">
        <input type="hidden" name="commandType" value="LOG_OUT_COMMAND"/>
        <input type="submit"  value="${button_log_out}" class="nice-button">
    </form>
    <form action="/XMLServlet" method="get">
        <input type="hidden" name="commandType" value="TO_XML_PAGE_COMMAND"/>
        <input type="submit"  value="XML" class="nice-button"/>
    </form>
</div>

<div align="center">
    <form action="/XMLServlet" method="get">
        <input type="hidden" name="commandType" value="PARSE_COMMAND"/>
        <label for="parser type" class="index-text">
            <fmt:message key="label.select_parser"/>
         </label>
        <select id="parser type" name="parserType" class="parser-selector">
            <option>DOM</option>
            <option>SAX</option>
            <option>StAX</option>
        </select>
        <br/><br/>
        <input type="submit" value="${button_parse}" class="nice-button"/>
    </form>
</div>
</body>
</html>
</fmt:bundle>