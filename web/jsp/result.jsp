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
    <h1>
        <c:out value="${parserType}"/>
    </h1>
    <table border="2px" width="100% ">
        <caption align="center" class="parser-selector">
            PC Component Table
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
                <th>Component Group</th>
                <th>Port</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="pcComponent" items="${pcComponentSet}">
                <tr>
                    <td><c:out value="${pcComponent.getDeviceId()}"/></td>
                    <td><c:out value="${pcComponent.getDeviceName()}"/></td>
                    <td><c:out value="${pcComponent.getOriginCountry()}"/></td>
                    <td><c:out value="${pcComponent.isCritical()}"/></td>
                    <td><c:out value="${pcComponent.getComponentType().isPeripheral()}"/></td>
                    <td><c:out value="${pcComponent.getComponentType().getEnergyConsumption()}"/></td>
                    <td><c:out value="${pcComponent.getComponentType().isHasCooler()}"/></td>
                    <td><c:out value="${pcComponent.getComponentType().getComponentGroup()}"/></td>
                    <td><c:out value="${pcComponent.getComponentType().getPort()}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br/>
    <table border="2px" width="100% ">
        <caption align="center" class="parser-selector">
            Phone Table
        </caption>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Origin Country</th>
            <th>RAM</th>
            <th>Build Date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="phone" items="${phoneSet}">
            <tr>
                <td><c:out value="${phone.getDeviceId()}"/></td>
                <td><c:out value="${phone.getDeviceName()}"/></td>
                <td><c:out value="${phone.getOriginCountry()}"/></td>
                <td><c:out value="${phone.getRam()}"/></td>
                <td><c:out value="${phone.getBuildDate()}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br/>
    <form action="XMLServlet" method="get">
        <input type="hidden" name="commandType" value="TO_MAIN_PAGE_COMMAND"/>
        <input type="submit" id="back_button" value="Back" class="device-button"/>
    </form>
</div>
</body>
</html>
