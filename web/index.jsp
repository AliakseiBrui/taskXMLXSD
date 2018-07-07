<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 27.05.2018
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="en_US"/>
<fmt:bundle basename="resources.text">
<html>
  <head>
    <title>
        <fmt:message key="title.authorization"/>
    </title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  <fmt:message key="button.sign_in" var="button_sign_in"/>
  <fmt:message key="button.log_in" var="button_log_in"/>
    <div>
      <form action="/XMLServlet" method="get">
          <input type="hidden" name="commandType" value="TO_REGISTRATION_PAGE_COMMAND"/>
          <input type="submit" value="${button_sign_in}" class="nice-button">
      </form>
    </div>
    <div align="center">
      <p class="error-message">
        <c:out value="${errorMessage}"/>
      </p>
      <p class="succeed-message">
        <c:out value="${message}"/>
      </p>
      <form action="/XMLServlet" method="post">
        <input type="hidden" name="commandType" value="AUTHORIZATION_COMMAND"/>
        <label for="login">
            <fmt:message key="label.login"/>
        </label><br/>
        <input type="text" name="login" id="login" class="input-text" maxlength="40"/>
          <br/><br/>
        <label for="password">
            <fmt:message key="label.password"/>
        </label><br/>
        <input type="password" name="password" id="password" class="input-text" maxlength="40"/>
          <br/><br/>
        <input type="submit" value="${button_log_in}" class="nice-button"/>
      </form>
    </div>
  </body>
</html>
</fmt:bundle>