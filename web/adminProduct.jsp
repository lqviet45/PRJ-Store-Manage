<%-- 
    Document   : adminProduct
    Created on : Jul 3, 2023, 9:56:07 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Product Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER.roleID ne 'AD'}">
            <c:redirect url="login.html"></c:redirect>
        </c:if>
        <div class="container">
            <h5>Welcome <mark>${sessionScope.LOGIN_USER.fullName}</mark></h5>

            <c:url var="logoutLink" value="MainController">
                <c:param name="action" value="Logout"></c:param>
            </c:url>

            <a href="${logoutLink}" class="btn btn-primary" >Logout</a>
            <div class="d-flex justify-content-between">

                <form action="MainController" class="my-3">
                    Search<input type="text" name="searchProduct" value ="${param.searchProduct}" class="ms-2"/>
                    <button type="submit" name="action" value="viewProduct" class="btn btn-info" style="padding-top: 3px; margin-bottom: 3px">Search</button>
                </form>

                <a href="insertProduct.jsp" class="btn btn-info" style="height: 40px; margin-top: 10px">Create Product</a>
                
                <a href="admin.jsp" class="btn btn-info" style="height: 40px; margin-top: 10px">Edit User</a>

            </div>
            <c:if test="${requestScope.LIST_PRODUCT != null}">
                <c:if test="${not empty requestScope.LIST_PRODUCT}">
                    <div class="table-responsive">
                        <table border="1" class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>image</th>
                                    <th>Update</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="p" varStatus="counter" items="${requestScope.LIST_PRODUCT}">
                                <form action="MainController">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>
                                            <input type="text" name="productID" value="${p.id}" readonly="" style="width: 80px" />
                                        </td>
                                        <td>
                                            <input type="text" name="pName" value="${p.name}" required="" />
                                        </td>
                                        <td>
                                            <input type="text" name="price" value="${p.price}" required="" style="width: 80px" />
                                        </td>
                                        <td>
                                            <input type="number" name="quantity" value="${p.quantity}" required="" min="0" style="width: 80px" />
                                        </td>
                                        <td>
                                            <input type="text" name="image" value="${p.img}" required="" style="width: 100%" />
                                        </td>
                                        <td>
                                            <input type="submit" name="action" value="UpdateProduct" />
                                            <input type="hidden" name="searchProduct" value="${param.searchProduct}" />
                                        </td>
                                        <td>
                                            <c:url var="deleteProductLink" value="MainController">
                                                <c:param name="productID" value="${p.id}" ></c:param>
                                                <c:param name="searchProduct" value="${param.searchProduct}" ></c:param>
                                                <c:param name="action" value="DeleteProduct" ></c:param>
                                            </c:url>
                                            <a href="${deleteProductLink}" >Delete</a>
                                        </td>
                                    </tr>
                                </form>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </c:if>
            <p class="text-danger">${requestScope.ERROR}</p>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>

    </body>
</html>