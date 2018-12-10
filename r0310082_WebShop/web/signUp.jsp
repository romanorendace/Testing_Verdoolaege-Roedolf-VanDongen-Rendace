<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Sign Up</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
    <h1>
        <c:if test="${person.role == 'CUSTOMER' || person.role == 'ADMINISTRATOR'}">
            <p id="order-history"><a href="Controller?action=orderHistory">Order History</a></p>
            <p><a href="Controller?action=shoppingCart">My Cart</a></p>
        </c:if>

        <span>Web Shop</span>
    </h1>
        <nav>
            <ul>
                <li><a href="Controller?action=home">Home</a></li>
                <c:if test="${person.role == 'ADMINISTRATOR'}">
                    <li><a href="Controller?action=users">Users</a></li>
                </c:if>
                <li><a href="Controller?action=products">Products</a></li>
                <c:if test="${person.role == 'ADMINISTRATOR'}">
                    <li><a href="Controller?action=addProductForm">Add Product</a></li>
                </c:if>
                <li id="actual"><a href="Controller?action=signUp">Sign up</a></li>

            </ul>
        </nav>
    </header>

    <main>
        <h2>Sign Up</h2>

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

            <form method="post" action="Controller?action=addPerson" novalidate="novalidate">
                <!-- novalidate in order to be able to run tests correctly -->
                <p><label for="userid">User id</label><input type="text" id="userid" name="userid"
                 required value="${fn:escapeXml(userIdPreviousValue)}"> </p>
                <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
                 required value="${fn:escapeXml(firstNamePreviousValue)}"> </p>
                <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName"
                 required value="${fn:escapeXml(lastNamePreviousValue)}"> </p>
                <p><label for="email">Email</label><input type="email" id="email" name="email"
                 required value="${fn:escapeXml(emailPreviousValue)}"></p>
                <p><label for="password">Password</label><input type="password" id="password"  name="password"
                 required> </p>
                <p><input type="submit" id="signUp" value="Sign Up"></p>

            </form>
    </main>
    <footer>
    &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
