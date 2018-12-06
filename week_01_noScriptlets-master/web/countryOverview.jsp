<%@page import="domain.model.Country"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/sample.css">
<meta charset="UTF-8">
<title>Countries</title>
</head>
<body>
	<header role="banner">
		<img alt="Toscane" src="images/toscaneRibbon.jpg">
	</header>

	<main id="container">
	<p>
		<a href="index.html">Home</a>
	</p>
	<article>
		<h1>Countries</h1>
		<%
			Country mostPopularCountry = (Country) request.getAttribute("popular");
			if (mostPopularCountry != null) {
		%>
		<p>
			The most popular country is
			<%=mostPopularCountry.getName()%>
		</p>
		<%
			}
		%>
		<%
			Collection<Country> countries = (Collection<Country>) request.getAttribute("countries");
			if (countries != null) {
		%>
		<table id="overview">
			<tr>
				<th>Name</th>
				<th>Capital</th>
				<th class="getal">Inhabitants</th>
				<th class="getal">Votes</th>
			</tr>
			<%
				for (Country country : countries) {
			%>
			<tr>
				<td><%=country.getName()%></td>
				<td><%=country.getCapital()%></td>
				<td class="getal"><%=country.getNumberInhabitants()%></td>
				<td class="getal"><%=country.getVotes()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			}
		%>
		<p>
			<a href="countryForm.jsp">Add new country</a>
		</p>
	</article>
	</main>
</body>
</html>