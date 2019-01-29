<%--
  Created by IntelliJ IDEA.
  User: ssdeepin
  Date: 19-1-28
  Time: 下午10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>首页</title>
  </head>
  <body>
  <c:if test="${empty userBean}">
    <a href="login.jsp">请登入</a>
  </c:if>


  <c:if test="${not empty userBean}">
    <h2>欢迎你 ${userBean.username}</h2>
  </c:if>
  </body>
</html>
