<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/6/2022
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="assets/css/main.css"/>
    <noscript>
        <link rel="stylesheet" href="assets/css/noscript.css"/>
    </noscript>
</head>
<body>
<div class="container">
    <div class="row">
        <!--    main  -->
        <div class="col-12">
            <form method="get">
                <a href="/products" class="col-3"><h1 style="display: inline">Tất cả sản phẩm</h1></a>
                <input type="submit" value="search" name="action" style="display: inline; float: right">
                <input type="text" name="name"
                       style="display: inline; float: right; width: 30%" placeholder="Nhập tên sản phẩm bạn muốn tìm">
            </form>
            <form method="get">
                <input type="number" name="p1" placeholder="Nhập giá bắt đầu">
                <input type="number" name="p2" placeholder="Nhập giá kết thúc">
                <input type="hidden" name="action" value="searchPrice">
                <input type="submit" value="tìm kiếm">
            </form>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Sale</th>
                </tr>
                <c:forEach items="${product}" var="p">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.name}</td>
                        <td>${p.price}</td>
                        <td>${p.quantity}</td>
                        <c:if test="${p.price >200}">
                            <td>10%</td>
                        </c:if>
                        <c:if test="${p.price <200}">
                            <td>20%</td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
