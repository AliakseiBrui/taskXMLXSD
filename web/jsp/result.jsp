<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 27.05.2018
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Parsed devices</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div align="center">
    <table border="2px" width="100% ">
        <caption align="center" class="parser-selector">
            <c:out value="${parserType}"/>
        </caption>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Origin Country</th>
                <th>isCritical</th>
                <th>isPeripheral</th>
                <th>Energy Consumption</th>
                <th>Has Cooler</th>
                <th>Device Group</th>
                <th>Port</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="device" items="${deviceSet}">
                <tr>
                    <td><c:out value="${device.getDeviceId()}"/></td>
                    <td><c:out value="${device.getDeviceName()}"/></td>
                    <td><c:out value="${device.getOriginCountry()}"/></td>
                    <td><c:out value="${device.isCritical()}"/></td>
                    <td><c:out value="${device.getDeviceType().isPeripheral()}"/></td>
                    <td><c:out value="${device.getDeviceType().getEnergyConsumption()}"/></td>
                    <td><c:out value="${device.getDeviceType().isHasCooler()}"/></td>
                    <td><c:out value="${device.getDeviceType().getDeviceGroup()}"/></td>
                    <td><c:out value="${device.getDeviceType().getPort()}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br/>
    <form action="XMLServlet" method="get">
        <input type="hidden" name="requestType" value="TO_MAIN_PAGE"/>
        <input type="submit" id="back_button" value="Back" class="device-button"/>
    </form>
</div>
</body>
</html>
