<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13.06.2018
  Time: 23:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div align="left">
        <form action="/XMLServlet" method="get">
            <input type="hidden" name="commandType" value="TO_AUTHORIZATION_PAGE_COMMAND"/>
            <input type="submit" name="to_auth_page_button" value="Log in"/>
        </form>
    </div>
    <div align="center">
        <form action="/XMLServlet" method="post">
            <input type="hidden" name="commandType" value="REGISTRATION_COMMAND"/>
            <label for="login">Login</label>
            <input type="text" name="login" id="login"/>
            <label for="password">Password</label>
            <input type="password" name="password" id="password">
            <input type="submit" name="sign_in_button" value="Sign in"/>
        </form>
        <p class="error-message">
            <c:out value="${errorMessage}"/>
        </p>

    </div>
</body>
</html>
