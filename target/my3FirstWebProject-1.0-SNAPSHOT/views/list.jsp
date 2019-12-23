<%@ page import="java.util.List" %>
<%@ page import="model.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>List of all user</title>
</head>
<body>

<table border="2">
<tr>
    <th>ID</th>
    <th>Логин</th>
    <th>Возраст</th>
<%--   <th>password</th>
--%>   <th>Действие</th>
</tr>

<%--@elvariable id="users" type="java.util.List"--%>
<c:forEach items="${users}" var="user">
    <tr>
        <td>${user.getId()}</td>
        <td>${user.getLogin()}</td>
        <td>${user.getAge()}</td>
<%--        <td>${user.getPassword()}</td>
--%>
        <td>
                    <a href="/edit?id=${user.getId()}" >
                        <input type="submit" value="Измениить">
                        </a>

            <form action="/delete?id=${user.getId()}" method="get">
                <input type="hidden" name="id" value="${user.getId()}">
               <input type="submit" value="Удалить" style="float:right">
            </form>
        </td>
    </tr>
</c:forEach>
    <div>

    </div>
</table>
<form action = "add">
<input type="submit" value="Добавить пользователя">
</form>
</body>
</html>
