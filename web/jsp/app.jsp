<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13.06.2018
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Application</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div align="right">
    <c:out value="Login: ${sessionScope.get('login')}"></c:out>
    <form action="XMLServlet" method="get">
        <input type="hidden" name="commandType" value="LOG_OUT_COMMAND"/>
        <input type="submit" name="log_out_button" value="Log out">
    </form>
</div>
<div align="center">
    <form action="XMLServlet" method="get">
        <input type="hidden" name="commandType" value="PARSE_COMMAND"/>
        <label for="parser type" class="index-text">Select XML parser</label>
        <select id="parser type" name="parserType" class="parser-selector">
            <option>DOM</option>
            <option>SAX</option>
            <option>StAX</option>
        </select>
        <br/><br/>
        <input type="submit" name="parse_button" value="Parse" class="device-button"/>
    </form>
</div>
</body>
</html>