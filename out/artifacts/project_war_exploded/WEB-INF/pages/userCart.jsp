<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Корзина</title>

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
    <c:when test="${sizeListCart!=0}">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>№</th>
                <th>Название</th>
                <th>Цена</th>
                <th>Добавить в заказы</th>
            </tr>
            </thead>
            <tbody>
            <form action="${pageContext.request.contextPath}/user/order/add" method="post" name="cart"
                  onsubmit="return validate();">
                <c:forEach items="${listCart}" var="book" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${book.name}</td>
                    <td>${book.price} BYN</td>
                    <td>
                        <input type="checkbox" value="${book.idbook}" name="idBook">
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <input type="hidden" value="${sessionScope.user}" name="userName">
        <button type="submit" class="btn btn-success col-md-offset-10">К заказам</button>
        </form>
    </c:when>
    <c:otherwise>
        <h3 class="display-3" style="text-align: center">Корзина пуста</h3>
    </c:otherwise>
</c:choose>
</body>
</html>
