<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="edu.school21.cinema.services.AuthService" %><%--
  Created by IntelliJ IDEA.
  User: mtabitha
  Date: 3/22/22
  Time: 8:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<%
    ApplicationContext springContext   = (ApplicationContext)(request.getServletContext().getAttribute("springContext"));
    AuthService        authService     = (AuthService) springContext.getBean("authService");
    request.setAttribute("auths", authService.getAuthentications());
%>
<table>
    <tr>
        <th>Date</th>
        <th>Time</th>
        <th>IP</th>
    </tr>
    <c:forEach var="auth" items="${auths}">
       <tr>
           <td><c:out value="${auth.date}"/></td>
           <td><c:out value="${auth.time}"/></td>
           <td><c:out value="${auth.ip}"/></td>
       </tr>
    </c:forEach>
</table>
<br/>

<form action="/image" method="POST" enctype = "multipart/form-data">
    <input type="file" name="file" placeholder="Filename: "/>
    <input type = "submit" value = "Upload File" />
</form>

</body>
</html>
