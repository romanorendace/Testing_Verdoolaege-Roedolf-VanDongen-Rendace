<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 10/12/2018
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Order History</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div id="container">
    <header>
        <h1>
            <c:if test="${person.role == 'CUSTOMER' || person.role == 'ADMINISTRATOR'}">
                <p id="order-history"><a href="Controller?action=orderHistory">Order History</a></p>
            </c:if>
            <p><a href="Controller?action=shoppingCart">My Cart</a></p>
            <span>Web Shop</span>
        </h1>
        <nav>
            <ul>
                <li><a href="Controller?action=home">Home</a></li>
                <c:if test="${person.role == 'ADMINISTRATOR'}">
                    <li><a href="Controller?action=users">Users</a></li>
                </c:if>
                <li id="actual"><a href="Controller?action=products">Products</a></li>
                <c:if test="${person.role == 'ADMINISTRATOR'}">
                    <li><a href="Controller?action=addProductForm">Add Product</a></li>
                </c:if>
                <li><a href="Controller?action=signUp">Sign up</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <h2>Order History</h2>


        <table>
            <tr>
                <th>Date</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Amount</th>
            </tr>
            <c:forEach var="order" items="${orderHistoryList}">
                <tr>
                    <td>${fn:escapeXml(order.date) }</td>
                    <td>${fn:escapeXml(order.product.name)}</td>
                    <td>${fn:escapeXml(order.product.description)}</td>
                    <td>${fn:escapeXml(order.product.price)}</td>
                    <td>${fn:escapeXml(order.amount)}</td>
                </tr>
            </c:forEach>
        </table>


    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
    </div>
</body>
</html>
