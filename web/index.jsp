<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 27.05.2018
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Authorization</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    <div>
      <form action="XMLServlet" method="get">
          <input type="hidden" name="commandType" value="TO_REGISTRATION_PAGE_COMMAND"/>
          <input type="submit" name="to_register" value="Sign in">
      </form>
    </div>
    <div align="center">
        <c:out value="${mainPageMessage}"/>
        <br/>
      <form action="XMLServlet" method="post">
        <input type="hidden" name="commandType" value="AUTHORIZATION_COMMAND"/>
        <label for="login">Login</label>
        <input type="text" name="login" id="login"/>
          <br/><br/>
        <label for="password">Password</label>
        <input type="password" name="password" id="password">
          <br/><br/>
        <input type="submit" name="log_in_button" value="Log in"/>
      </form>
    </div>
  </body>
</html>
