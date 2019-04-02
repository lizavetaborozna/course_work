<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Главная</title>
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
            <a class="nav-link" href="${pageContext.request.contextPath}/user">Каталог <span
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
        <li >
            <a class="nav-link" href="${pageContext.request.contextPath}/logOut">Выход
                <p>(${sessionScope.user})</p>
            </a>
        </li>
    </ul>
</nav>
<form action="${pageContext.request.contextPath}/user" method="post" class="frm">
    <c:forEach items="${listBooks}" var="book">
        <div class="row">
            <div>
                <div class="col-lg-6">
                        <%--                    <img src="<%@include  file="/WEB-INF/pages/images"%>/${book.imageName}.jpg" alt=${book.name} class="im"/>--%>
                    <img src="${book.imageName}" alt=${book.name} class="im"/>
                    <div class="caption">
                        <span class="title">${book.name}</span>
                        <span class="info">Автор: ${book.author}<br/>
                        Жанр: ${book.genre}<br/>Цена: ${book.price} BYN</span>
                    </div>
                </div>
                <br/>
                <div class="test">
                    <button type="submit" class="btn btn-default" name="bookImageName" value="${book.imageName}">
                        Добавить в корзину
                    </button>
                </div>
            </div>
        </div>
    </c:forEach>
</form>
</body>
</html>
