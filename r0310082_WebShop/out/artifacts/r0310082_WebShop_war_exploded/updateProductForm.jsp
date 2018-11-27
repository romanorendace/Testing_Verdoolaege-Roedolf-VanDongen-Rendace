<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 27/09/2018
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update Product</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
            <p><a href="Controller?action=shoppingCart">Shopping Cart</a></p>
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

        <h2>Update Product</h2>
    </header>
    <main>

        <c:if test="${fn:length(errors) != 0}">
            <div class="alert-danger">
                <ul>
                    <c:forEach var="err" items="${errors}">
                        <li>${err}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <c:if test="${error != null}">
            <div class="alert-danger">
                <ul>
                    <li>${error}</li>
                </ul>
            </div>
        </c:if>

        <form method="post" action="Controller?action=updateProduct" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <input type="hidden" name="productId" value="${fn:escapeXml(product.productId)}">
            <p><label for="name">Name</label>
                <input type="text" id="name" name="name" required value="${fn:escapeXml(product.name)}"> </p>
            <p><label for="description">Description</label>
                <input type="text" id="description" name="description" required value="${fn:escapeXml(product.description)}"> </p>
            <p><label for="price">Price</label>
                <input type="text" id="price" name="price" required value="${fn:escapeXml(product.price)}"> </p>
            <p><input type="submit" id="updateProduct" value="Update"></p>
        </form>

    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
