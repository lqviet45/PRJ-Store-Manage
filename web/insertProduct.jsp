<%-- 
    Document   : insertProduct
    Created on : Jul 4, 2023, 8:17:41 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Product Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>
    <body>
        <h1 class="text-center">Insert Product</h1>
        <div class="container">
            <a href="adminProduct.jsp" class="btn btn-info" style="display: block; margin-left: 80%" >Go Back Edit Product Page</a>
            <form action="MainController">
                <div class="mb-3">
                    <label for="productID" class="form-label">Product ID</label>
                    <input type="text" class="form-control" id="productID" name="productID" required="" />
                    <p class="text-danger">${requestScope.ERROR.idError}</p>
                </div>
                <div class="mb-3">
                    <label for="productName" class="form-label">Product Name</label>
                    <input type="text" class="form-control" id="productName" name="pName" required="" />
                    <p class="text-danger">${requestScope.ERROR.nameError}</p>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="price">Product Price</label>
                    <input type="text" class="form-control" id="price" min="0" name="price" required="" />
                    <p class="text-danger">${requestScope.ERROR.priceError}</p>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="quantity">Product Quantity</label>
                    <input type="number" class="form-control" id="quantity" min="0" name="quantity" required="" />
                    <p class="text-danger">${requestScope.ERROR.quantityError}</p>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="img">Product image</label>
                    <input type="text" class="form-control" id="img" name="img" required="" />
                </div>
                <p class="text-danger">${requestScope.MESSAGE}</p>
                <button type="submit" class="btn btn-primary" name="action" value="InsertProduct">Insert</button>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>
</html>
