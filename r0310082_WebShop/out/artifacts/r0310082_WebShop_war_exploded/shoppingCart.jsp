
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
            <p><a href="Controller?action=shoppingCart">My Cart</a></p>
            <span>Web Shop</span>
        </h1>
        <nav>
            <ul>
                <li><a href="Controller?action=home">Home</a></li>
                <li><a href="Controller?action=users">Users</a></li>
                <li><a href="Controller?action=products">Products</a></li>
                <li><a href="Controller?action=addProductForm">Add Product</a></li>
                <li><a href="Controller?action=signUp">Sign up</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <h2>My Shopping Cart</h2>

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
                <td>${fn:escapeXml(order.amount)}</td>
            </tr>
            </c:forEach>
        </table>

    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
</div>
</body>
</html>
