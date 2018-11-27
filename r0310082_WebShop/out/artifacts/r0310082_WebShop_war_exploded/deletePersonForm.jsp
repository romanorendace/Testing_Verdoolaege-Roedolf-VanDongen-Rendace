<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <title>DeletePersonForm</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div id="container">
        <main>
            <h2>Are You Sure You Want To Delete User ?</h2>

            <form method="post" action="Controller?action=deleteUser">
                <input type="hidden" name="userId" value="${fn:escapeXml(userId)}">
                <input type="submit" value="Yes"></form>
            <form method="post" action="Controller?action=users">
                <input type="submit" value="No"></form>

        </main>
        <footer>
            &copy; Webontwikkeling 3, UC Leuven-Limburg
        </footer>
    </div>

</body>
</html>
