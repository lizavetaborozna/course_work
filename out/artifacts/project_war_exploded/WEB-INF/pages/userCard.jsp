<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Платежные карты</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        <%@include  file="/resources/css/bootstrap.css"%>
        <%@include  file="/resources/css/mainBook.css"%>
    </style>
</head>
<body>
<header class="head">
</header>
<nav class="navbar navbar-light  bg-faded" style="background-color: #cbedf2;">
    <ul class="nav navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/user">Каталог<span
                    class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/user/cart">Корзина ${sessionScope.number}</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/user/order">Заказы</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/user/card">Платежные карты</a>
        </li>
        <li>
            <a class="nav-link" href="${pageContext.request.contextPath}/logOut">Выход
                <p>(${sessionScope.user})</p>
            </a>
        </li>
    </ul>
</nav>
<c:choose>
    <c:when test="${sizeListCards!=0}">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>№</th>
                <th>Номер карты</th>
                <th>Месяц</th>
                <th>Год</th>
                <th>Описание</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listCards}" var="card" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${card.number}</td>
                    <td>${card.month}</td>
                    <td>${card.year}</td>
                    <td>${card.description}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <h3 class="display-3" style="text-align: center">Список карт пуст</h3>
    </c:otherwise>
</c:choose>
<button type="button" class="btn btn-success col-md-offset-10"
        onclick="location.href='${pageContext.request.contextPath}/user/card/add'">Add
</button>
</body>
</html>
