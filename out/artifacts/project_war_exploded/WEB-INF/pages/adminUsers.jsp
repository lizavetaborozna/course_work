<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список пользователей</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        <%@include  file="/resources/css/bootstrap.css"%>
        <%@include  file="/resources/css/mainBook.css"%>
    </style>
</head>
<body>
<header class="head">
</header>
<nav class="navbar navbar-light bg-faded" style="background-color: #cbedf2;">
    <ul class="nav navbar-nav">
        <li class="nav-item active">
            <a class="navbar-brand" href="/admin">Заказы</a>
        </li>
        <li>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/users">Пользователи</a>

        </li>
        <li>
            <a class="nav-link" href="${pageContext.request.contextPath}/logOut">Выход
                <p>(${sessionScope.user})</p>
            </a>
        </li>
    </ul>
</nav>

<table class="table table-striped">
    <thead>
    <tr>
        <th>№</th>
        <th> Логин</th>
        <th>Статус</th>
        <th>Удалить</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listAllUsers}" var="user" varStatus="i">
        <tr>
            <th scope="row">${i.count}</th>
            <td>${user.login}</td>
            <td>${user.role} </td>
            <form action="${pageContext.request.contextPath}/admin/users/delete" method="post" class="frm">
                <td>
                    <input type="hidden" value="${user.id}" name="idForDelete">
                    <button type="submit" class="btn btn-danger">Удалить</button>
                </td>
            </form>
        </tr>
        <tr></tr>
    </c:forEach>
    </tbody>
</table>
</form>
</body>
</html>