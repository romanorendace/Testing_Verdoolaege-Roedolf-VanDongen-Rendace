<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Check Password</title>
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
                <li id="actual"><a href="Controller?action=home">Home</a></li>
                <c:if test="${person.role == 'ADMINISTRATOR'}">
                    <li><a href="Controller?action=users">Users</a></li>
                </c:if>
                <li><a href="Controller?action=products">Products</a></li>
                <c:if test="${person.role == 'ADMINISTRATOR'}">
                    <li><a href="Controller?action=addProductForm">Add Product</a></li>
                </c:if>
                <li><a href="Controller?action=signUp">Sign up</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <h2>Check Password</h2>

        <form method="POST" action="Controller?action=checkPassword" novalidate="novalidate">
            <input type="hidden" name="userid" value="${fn:escapeXml(userid)}">
            <p><labal for="password">Fill out your password</labal></p>
            <p><input name="password" id="password" type="text" autofocus></p>
            <p><input type="submit" id="check" value="CHECK"></p>
        </form>

    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>

</body>
</html>
