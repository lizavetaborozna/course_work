<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Заказы для администратора</title>
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
        <th>Покупатель</th>
        <th>Названия</th>
        <th>Цена</th>
        <th>Цена заказа</th>
        <th>Статус</th>
        <th>Выполнить заказ</th>
        <th>Удалить</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listAllOrder}" var="order" varStatus="i">
        <tr>
            <th scope="row">${i.count}</th>
            <td>${order.user}</td>
            <td><c:forEach items="${order.books}" var="book">
                ${book.name}<br/>
            </c:forEach></td>
            <td><c:forEach items="${order.books}" var="book">
                ${book.price} BYN<br/>
            </c:forEach></td>
            <td>${order.price} BYN</td>
            <td>${order.status}</td>
            <form action="${pageContext.request.contextPath}/admin/change" method="post" class="frm">
                <input type="hidden" value="${order.id}" name="change">
                <td>
                    <c:choose>
                        <c:when test="${order.status==sessionScope.orderStatus}">
                            <button type="submit" class="btn btn-warning">Выполнить</button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" class="btn btn-warning" disabled>Выполнить</button>
                        </c:otherwise>
                    </c:choose>
                </td>
            </form>
            <form action="${pageContext.request.contextPath}/admin/delete" method="post" class="frm">
                <td>
                    <input type="hidden" value="${order.id}" name="idForDelete">
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