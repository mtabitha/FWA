<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="edu.school21.cinema.services.AuthService" %>
<%@ page import="edu.school21.cinema.services.ImageService" %><%--
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
    <style>
        body {
            font: 11pt Arial, Helvetica, sans-serif; /* Рубленый шрифт текста */
            margin: 0; /* Отступы на странице */
        }
        h1 {
            font-size: 36px; /* Размер шрифта */
            margin: 0; /* Убираем отступы */
            color: #fc6; /* Цвет текста */
        }
        h2 {
            margin-top: 0; /* Убираем отступ сверху */
        }
        #container {
            width: 1000px; /* Ширина слоя */
            margin: 0 auto; /* Выравнивание по центру */
        }
        #sidebar { /* Левая колонка */
            float: left; /* Обтекание справа */
            width: 300px; /* Ширина колонки */
            padding: 5px; /* Поля вокруг текста */
        }
        #content { /* Правая колонка */
            margin: 10px 5px 20px 310px; /* Значения отступов */
            padding: 5px; /* Поля вокруг текста */
        }
        #footer { /* Нижний блок */
            width: 1000px; /* Ширина слоя */
        }
    </style>
</head>
<body>
<%
    ApplicationContext  springContext   = (ApplicationContext)(request.getServletContext().getAttribute("springContext"));
    AuthService         authService     = (AuthService) springContext.getBean("authService");
    ImageService        imageService    = (ImageService) springContext.getBean("imageService");
    request.setAttribute("auths", authService.getAuthentications());
    request.setAttribute("images", imageService.getImages());
    request.setAttribute("user", request.getSession().getAttribute("user"));
%>
<div id="container">
<div id="sidebar">
    <img src="http://localhost:8080/images/${images.get(images.size() - 1).name}" width="300" height="300">
    <p><br/></p>
    <form action="/images" method="POST" enctype = "multipart/form-data" id="upload">
        <input type="file" name="file" value="Upload" onchange="document.getElementById('upload').submit()"/>
    </form>
</div>
<div id="content">
    <p> <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/> </p>
    <p> <c:out value="${user.email}"/> </p>
<table width="680" border="1" cellpadding="4" cellspacing="0">
    <thead bgcolor="#C0C0C0">
    <tr>
        <th>Date</th>
        <th>Time</th>
        <th>IP</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="auth" items="${auths}">
        <tr >
            <td width="45%" align="center"><c:out value="${auth.date}"/></td>
            <td width="10%" align="center"><c:out value="${auth.time}"/></td>
            <td width="45%" align="center"><c:out value="${auth.ip}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<div id="footer">
<table width="1000" border="1" cellpadding="4" cellspacing="0">
    <thead bgcolor="#C0C0C0">
    <tr>
        <th>File name</th>
        <th>Size</th>
        <th>MIME</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="image" items="${images}">
       <tr >
           <td width="50%" align="center"><a href="/images/${image.name}"><c:out value="${image.name}"/></a></td>
           <td width="15%" align="center"><c:out value="${image.size}"/></td>
           <td width="35%" align="center"><c:out value="${image.path}"/></td>
       </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</div>
</body>
</html>
