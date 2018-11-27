<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 25/09/2018
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>ProductOverview</title>
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
                    <li id="actual"><a href="Controller?action=products">Products</a></li>
                    <li><a href="Controller?action=addProductForm">Add Product</a></li>
                    <li><a href="Controller?action=signUp">Sign up</a></li>
                </ul>
            </nav>
        </header>

        <main>
            <h2>Product Overview</h2>
            <table>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                </tr>

                <c:forEach var="product" items="${products}">
                <tr>
                    <td><a href="Controller?action=updateProductForm&productId=${fn:escapeXml(product.productId)}">${fn:escapeXml(product.name)}</a></td>
                    <td>${fn:escapeXml(product.description)}</td>
                    <td>${fn:escapeXml(product.price)}</td>
                    <td><a href="Controller?action=deleteProductForm&productId=${fn:escapeXml(product.productId)}">DELETE</a></td>
                        <form id="order-form" action="Controller?action=addOrder" method="post">
                            <input type="hidden" name="orderProductId" value="${fn:escapeXml(product.productId)}">
                            <td><input type="submit" value="ORDER"></td>
                            <td><input id="orderAmount" type="number" name="orderAmount" min="1" max="99"></td>
                        </form>
                </tr>
                </c:forEach>

                <caption>Product Overview</caption>
            </table>

        </main>
        <footer>
            &copy; Webontwikkeling 3, UC Leuven-Limburg
        </footer>
    </div>
</body>
</html>
