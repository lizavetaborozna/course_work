<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Авторизация</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        <%@include  file="/resources/css/bootstrap.css"%>
        <%@include  file="/resources/css/authorization.css"%>
    </style>
</head>
<body style="background-color: #009fae">
<header class="head" style="background-color: #cbedf2">
    <h1>MyBook.by </h1>
</header>
<form action="${pageContext.request.contextPath}/" method="post" class="frm" style="margin: 0 auto">
    <br/>
    <div class="form-group">
        <label for="usr">Логин:</label>
        <input type="text" class="form-control" id="usr" name="username" placeholder="Введите логин">
    </div>
    <div class="form-group">
        <label for="psw">Пароль:</label>
        <input type="password" class="form-control" id="psw" name="password" placeholder="Введите пароль">
    </div>
    <button type="submit" id="popover" class="btn btn-default">Войти</button>
</form>
</body>
</html>
