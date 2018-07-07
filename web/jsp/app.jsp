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
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:bundle basename="resources.text">
<html>
<head>
    <title>
        <fmt:message key="title.app"/>
    </title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">


</head>
<body>
    <fmt:message key="button.log_out" var="button_log_out"/>
    <fmt:message key="button.parse" var="button_parse"/>
    <fmt:message key="button.change" var="button_change"/>
<div align="right">
    <p class="login-text">
        <fmt:message key="label.login"/>
        <c:out value=": ${sessionScope.get('login')}"/>
    </p>
    <form action="/XMLServlet" method="get">
        <input type="hidden" name="commandType" value="LOG_OUT_COMMAND"/>
        <input type="submit" name="log_out_button" value="${button_log_out}" class="nice-button">
    </form>
    <form action="/XMLServlet" method="get">
        <input type="hidden" name="commandType" value="TO_XML_PAGE_COMMAND"/>
        <input type="submit" name="to_xml_page_button" value="XML" class="nice-button"/>
    </form>
</div>
    <div align="left">
        <form id="langForm" action="/XMLServlet" method="get">
            <input type="hidden" name="commandType" value="CHANGE_LOCALE_COMMAND"/>
            <select name="lang" class="index-text" oninput="document.getElementById('langForm').submit()">
                <option hidden="hidden">
                    <fmt:message key="label.lang"/>
                </option>
                <option>RUS</option>
                <option>ENG</option>
            </select>

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
        <input type="submit" name="parse_button" value="${button_parse}" class="nice-button"/>
    </form>
</div>
</body>
</html>
</fmt:bundle>