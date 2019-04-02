<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Заказы</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        <%@include  file="/resources/css/bootstrap.css"%>
        <%@include  file="/resources/css/mainBook.css"%>
    </style>
</head>
<body>
<header class="head">
    <p class="user">${sessionScope.user} (${sessionScope.role})</p>
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
    <c:when test="${sizeListOrders!=0}">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>№</th>
                <th>Название</th>
                <th>Цена</th>
                <th>Стоимость заказа</th>
                <th>Статус</th>
                <th>Удалить</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listOrders}" var="order" varStatus="i">
                <tr>
                    <th scope="row">${i.count}</th>
                    <td><c:forEach items="${order.books}" var="book">
                        ${book.name}<br/>
                    </c:forEach></td>
                    <td><c:forEach items="${order.books}" var="book">
                        ${book.price} BYN<br/>
                    </c:forEach></td>
                    <td>${order.price} BYN</td>
                    <td>${order.status}</td>
                    <form action="${pageContext.request.contextPath}/user/order/delete" method="post" class="frm">
                        <input type="hidden" value="${order.id}" name="idForDelete">
                        <td>
                            <c:choose>
                                <c:when test="${order.status=='Waiting'}">
                                    <button type="submit" class="btn btn-danger">Удалить</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-danger" disabled>Удалить</button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </form>
                </tr>
                <tr></tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <h3 class="display-3" style="text-align: center">Список заказов пуст</h3>
    </c:otherwise>
</c:choose>
</body>
</html>
