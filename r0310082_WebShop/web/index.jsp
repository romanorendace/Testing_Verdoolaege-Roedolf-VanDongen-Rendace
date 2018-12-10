<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Home</title>
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
			<div id="welcome-text">
				<h2>All Hail The Donald !</h2>

				<p>Donald J. Trump is the very definition of the American success story,
					setting the standards of excellence in his business endeavors, and now,
					for the United States of America.</p>


				<form id="quote-form" method="post" action="Controller?action=home">
					<p>Do you want to see a Quote ?</p>

					<input id="quoteYes" type="radio" name="quote" value="yes" ${yesChecked}>
					<label for="quoteYes">Yes</label>

					<input id="quoteNo" type="radio" name="quote" value="no" ${noChecked}>
					<label for="quoteNo">No</label>
					<input type="submit" value=" SEND ">
				</form>
				<p id="signature">${fn:escapeXml(quote)}</p>

				<c:if test="${notAuthorized!=null }">
					<p id="message-not-authorized" class="error">${notAuthorized }</p>
				</c:if>
				<p id="message-log-in-failed">${fn:escapeXml(messageLogInFailed)}</p>

				<c:choose>
					<c:when test="${!isLoggedIn}">
						<form id="login-form" action="Controller?action=logIn" method="post">
							<label for="email">EMAIL:</label>
							<input id="email" name="email" type="text"><br>
							<label for="password">PASSWORD:</label>
							<input id="password" name="password" type="text"><br>
							<input type="submit" value=" LOGIN ">
						</form>
					</c:when>

					<c:otherwise>
						<h2>Welcome, ${person.firstName} !</h2>
						<form id="log-out-form" action="Controller?action=logOut" method="post">
							<input type="submit" value=" LOG OUT ">
						</form>
					</c:otherwise>
				</c:choose>


			</div>
		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>