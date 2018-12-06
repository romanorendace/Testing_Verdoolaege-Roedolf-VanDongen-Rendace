<%@page import="java.util.List"%>
<%@page import="domain.model.Country"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<article>
		<h1>New Country</h1>
		<form method="POST" action="Controller">
			<%
				List<String> result = (List<String>) request.getAttribute("result");
				String statusMesageClass = "";
				StringBuilder statusMessage = new StringBuilder();
				if (result != null) {
					statusMesageClass = "danger";
			%>
			<div class="alert alert-<%=statusMesageClass%>">
				<%
					for (String message : result) {
				%>
				<p><%=message%></p>
				<%
					}
				}
				%>
			</div>
			<fieldset>
				<legend>Identification</legend>
				<p class="form-group <%=request.getAttribute("nameClass")%>">
					<label class="control-label" for="name">Name (required): </label> <input
						id="name" name="name" type="text"
						value="<%=request.getAttribute("namePreviousValue") != null ? request
					.getAttribute("namePreviousValue") : ""%>">
				</p>
				<p class="form-group <%=request.getAttribute("capitalClass")%>">
					<label class="control-label" for="capital">Capital: </label> <input
						id="capital" type="text" name="capital"
						value="<%=request.getAttribute("capitalPreviousValue") != null ? request
					.getAttribute("capitalPreviousValue") : ""%>">
				</p>
				<p class="form-group <%=request.getAttribute("inhabitantsClass")%>">
					<label class="control-label" for="inhabitants">Inhabitants:
					</label> <input id="inhabitants" name="inhabitants" type="text"
						value="<%=request.getAttribute("inhabitantsPreviousValue") != null ? request
					.getAttribute("inhabitantsPreviousValue") : ""%>">
				</p>
			</fieldset>
			<p class="form-group <%=request.getAttribute("votesClass")%>">
				<label class="control-label" for="votes">Votes: </label> <input
					id="votes" name="votes" type="text"
					value="<%=request.getAttribute("votesPreviousValue") != null ? request
					.getAttribute("votesPreviousValue") : ""%>">
			</p>

			<p>
				<label for="bewaar">&nbsp;</label><input id="bewaar" type="submit"
					value="Save">
			</p>

		</form>
	</article>
	</main>
</body>
</html>