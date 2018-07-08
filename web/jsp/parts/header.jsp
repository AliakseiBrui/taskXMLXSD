<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 07.07.2018
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="resources.text">
<div class="header">
    <div class="header-left">
        <form id="langForm" action="/XMLServlet" method="get">
            <input type="hidden" name="commandType" value="CHANGE_LOCALE_COMMAND"/>
            <input type="hidden" name="pagePath" value="${pagePath}"/>
            <select name="lang" class="custom-select" oninput="document.getElementById('langForm').submit()">
                <option hidden="hidden">
                    <fmt:message key="label.lang"/>
                </option>
                <option>RUS</option>
                <option>ENG</option>
            </select>
        </form>
    </div>
    <div class="header-right">
        <c:if test="${not empty login}">
            <div class="header-panel-login">
                <p class="header-text">
                    <fmt:message key="label.login"/>
                    <c:out value=": ${sessionScope.login}"/>
                </p>
            </div>
        </c:if>
    </div>
</div>
<br/>
</fmt:bundle>