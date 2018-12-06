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
				<p><a href="Controller?action=shoppingCart">My Cart</a></p>
				<span>Web Shop</span>
			</h1>
			<nav>
				<ul>
					<li id="actual"><a href="Controller?action=home">Home</a></li>
					<li><a href="Controller?action=users">Users</a></li>
					<li><a href="Controller?action=products">Products</a></li>
					<li><a href="Controller?action=addProductForm">Add Product</a></li>
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

				<form id="login-form" action="Controller?action=logIn" method="post">
					<label for="userId">USERID:</label>
					<input id="userId" name="userId" type="text"><br>
					<label for="password">PASSWORD:</label>
					<input id="password" name="password" type="text"><br>
					<input type="submit" value="LOGIN">
				</form>

			</div>
		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>