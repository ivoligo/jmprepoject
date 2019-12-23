
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<html>
<head>
    <title>Изменение данные пользователя</title>
</head>
<body>

<c:set var="user" value="${requestScope.user}">
</c:set>

Редактирование пользователя id = ${param.id}

<form action="edit?id=${user.id}" method="post">
    <input type="hidden" name="id" value="${user.id}" >
    <input required type="text" name="login" value="${user.login}" placeholder="Login">
    <input required type="text" name="age" value="${user.age}" placeholder="Возраст">
    <input required type="password" name="password" value="${user.password}" placeholder="Паролька">
    <input type="submit" value="Применить">
</form>

<form action="list" >
    <input type="submit" value="Список пользователей">
</form>
<%
    if (request.getAttribute("userLog") != null){
        out.println("Пользователь с логином " + request.getAttribute("userLog") + " существует.");
    }
    if(request.getAttribute("userUpdate") != null){
        out.print("Данные пользователя изменены");
    }
%>
</body>
</html>
