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
</head>
<body>
    <div align="center">
        <form action="XMLServlet" method="post">
            <input type="hidden" name="commandType" value="REGISTRATION_COMMAND"/>
            <label for="login">Login</label>
            <input type="text" name="login" id="login"/>
            <label for="password">Password</label>
            <input type="password" name="password" id="password">
            <input type="submit" name="sign_in_button" value="Sign in"/>
        </form>
        <c:out value="${errorMessage}"/>
    </div>
</body>
</html>
