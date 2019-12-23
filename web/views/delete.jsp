
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Remove user</title>
</head>
<body>

Действительно удалить пользователя ${param.id}?

<form   method="post">
<%--    <input type="hidden" name="id" value="${param.id}">
--%>    <input type="submit" value="Да, удалить" >
</form>
<form action="list">
    <input type="submit" value="Отмена" >
</form>

</body>
</html>
