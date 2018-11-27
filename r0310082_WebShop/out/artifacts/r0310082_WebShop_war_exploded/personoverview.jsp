<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>User Overview</title>
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
                    <li id="actual"><a href="Controller?action=users">Users</a></li>
                    <li><a href="Controller?action=products">Products</a></li>
                    <li><a href="Controller?action=addProductForm">Add Product</a></li>
                    <li><a href="Controller?action=signUp">Sign up</a></li>
                </ul>
            </nav>
    </header>

    <main>
        <h2>User Overview</h2>

        <form action="Controller?action=users" method="post">
            <select name="sort" id="sort-users">
                <option value="email" ${sortEmail} >Email</option>
                <option value="firstname"  ${sortFirstname} >Firstname</option>
                <option value="lastname" ${sortLastname}>Lastname</option>
            </select>
            <input type="submit" value="Order by">
        </form>
        
        <table>
        <tr>
            <th>E-mail</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Delete</th>
            <th>Check Password</th>
        </tr>

            <c:forEach var="person" items="${persons}">
            <tr>
                <td>${fn:escapeXml(person.email)}</td>
                <td>${fn:escapeXml(person.firstName)}</td>
                <td>${fn:escapeXml(person.lastName)}</td>
                <td><a href="Controller?action=deleteUserForm&userId=${fn:escapeXml(person.userid)}">DELETE</a></td>
                <td><a href="Controller?action=checkPasswordForm&userId=${fn:escapeXml(person.userid)}">CHECK</a></td>
            </tr>
            </c:forEach>

        <caption>Users Overview</caption>
        </table>
    </main>
        <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
        </footer>
    </div>
</body>
</html>