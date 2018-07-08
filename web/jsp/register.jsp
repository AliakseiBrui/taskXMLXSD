<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13.06.2018
  Time: 23:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="resources.text">
<html>
<head>
    <title>
        <fmt:message key="title.registration"/>
    </title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:set var="pagePath" value="jsp/register.jsp"/>
<%@include file="parts/header.jsp" %>
<fmt:message key="button.log_in" var="button_log_in"/>
<fmt:message key="button.sign_in" var="button_sign_in"/>
    <div align="left">
        <form action="/XMLServlet" method="get">
            <input type="hidden" name="commandType" value="TO_AUTHORIZATION_PAGE_COMMAND"/>
            <input type="submit" " value="${button_log_in}" class="nice-button"/>
        </form>
    </div>
    <div align="center">
        <form action="/XMLServlet" method="post">
            <input type="hidden" name="commandType" value="REGISTRATION_COMMAND"/>
            <label for="login">
                <fmt:message key="label.login"/>
            </label><br/>
            <input type="text" name="login" id="login" class="input-text" maxlength="40"/>
            <br/><br/>
            <label for="password">
                <fmt:message key="label.password"/>
            </label><br/>
            <input type="password" name="password" id="password" class="input-text" maxlength="40">
            <br/><br/>
            <input type="submit"  value="${button_sign_in}" class="nice-button"/>
        </form>
        <p class="error-message">
            <c:out value="${errorMessage}"/>
        </p>

    </div>
</body>
</html>
</fmt:bundle>