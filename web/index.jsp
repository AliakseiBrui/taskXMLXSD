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
    <title></title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    <div align="center">
      <form action="XMLServlet" method="get">
        <input type="hidden" name="requestType" value="PARSE"/>
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
