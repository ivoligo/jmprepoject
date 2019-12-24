
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление нового пользователя</title>
</head>
<body>

<form method="post">
    <input required type="text" name="login" placeholder="Логин">
    <input required type="number" name="age" placeholder="Возраст">
    <input required type="password" name="password" placeholder="Пароьлька">
    <input type="submit" value="Схоранить">
</form>
<form action="/list" >
    <input type="submit" value="Список пользователей">
</form>

</body>
</html>
