
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>My Cart</title>
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
        <h2>My Shopping Cart</h2>

        <p id="message-order-updated">${fn:escapeXml(messageOrderUpdated)}</p>

        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Amount</th>
            </tr>
            <c:forEach var="order" items="${orders}">
            <tr>
                <td>${fn:escapeXml(order.product.name)}</td>
                <td>${fn:escapeXml(order.product.description)}</td>
                <td>${fn:escapeXml(order.product.price)}</td>
                <form id="order-update-form" method="post" action="Controller?action=updateOrder">
                    <input type="hidden" name="productIdToUpdate" value="${fn:escapeXml(order.productId)}">
                    <td><input name="newOrderAmount"  type="number" min="0" max="99" value="${fn:escapeXml(order.amount)}"></td>
                    <td><input type="submit" value="UPDATE"></td>
                </form>
            </tr>
            </c:forEach>
        </table>

        <p id="total-price">TOTAL PRICE:   ${totalPrice}</p>

        <form id="pay-form" action="Controller?action=pay" method="post">
            <input type="submit" value=" PAY ">
        </form>

    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
</div>
</body>
</html>
