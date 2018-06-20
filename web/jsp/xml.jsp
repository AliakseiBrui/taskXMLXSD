<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 19.06.2018
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>XML</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div align="right">
    <p class="login-text">
        <c:out value="Login: ${sessionScope.get('login')}"/>
    </p>
    <form action="/XMLServlet" method="get">
        <input type="hidden" name="commandType" value="TO_MAIN_PAGE_COMMAND"/>
        <input type="submit" name="to_main_page_button" value="Main page" class="nice-button"/>
    </form>
</div>
<div align="center" style="height: 100%; align-content: center">
    <embed src="${pageContext.request.contextPath}/res/someDevices.xml" width="100%" style="height: 100%;"/>
</div>
</body>
</html>
