<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Добавление карты</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        <%@include  file="/resources/css/bootstrap.css"%>
        <%@include  file="/resources/css/mainBook.css"%>
    </style>
</head>
<body>
<header class="head">
   </header>
<form action="${pageContext.request.contextPath}/user/card/add" method="post" class="frm">
    <br/>
    <div class="form-group">
        <label for="num">Номер карты:</label>
        <input type="text" class="form-control" id="num" name="numberCard" placeholder="Введите номер карты:">
    </div>
    <div class="form-group">
        <label for="month">Месяц:</label>
        <input type="text" class="form-control" id="month" name="month" placeholder="Введите месяц:">
    </div>
    <div class="form-group">
        <label for="year">Год:</label>
        <input type="text" class="form-control" id="year" name="year" placeholder="Введите год:">
    </div>
    <div class="form-group">
        <label for="description">Описание:</label>
        <input type="text" class="form-control" id="description" name="description" placeholder="Введите описание:">
    </div>
    <button type="submit" id="popover" class="btn btn-success">Добавить</button>
</form>
</body>
</html>
