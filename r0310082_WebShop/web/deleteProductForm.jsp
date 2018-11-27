<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 28/09/2018
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>DeleteProductForm</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div id="container">
        <main>
            <h2>Are You Sure You Want To Delete Product ?</h2>

            <form method="post" action="Controller?action=deleteProduct">
                <input type="hidden" name="productId" value="${fn:escapeXml(productId)}">
                <input type="submit" value="Yes"></form>
            <form method="post" action="Controller?action=products">
                <input type="submit" value="No"></form>

        </main>
        <footer>
            &copy; Webontwikkeling 3, UC Leuven-Limburg
        </footer>
    </div>
</body>
</html>
