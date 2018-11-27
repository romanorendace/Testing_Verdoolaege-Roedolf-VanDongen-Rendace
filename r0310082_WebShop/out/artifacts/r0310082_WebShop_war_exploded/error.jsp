<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Error</title>
</head>
<body>
    <div id="container">
        <header>
            <h1>
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
        </header>

        <main>
            <div id="error-message"><h2>Oops! Something went wrong...</h2>
                <img src="images/error.jpg" alt="">
            </div>
        </main>
        <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
    </div>

</body>
</html>
