<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
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

            <a href="${logoutLink}" class="btn btn-primary">Logout</a>
            <div class="d-flex justify-content-between">

                <form action="MainController" class="my-3">
                    Search<input type="text" name="search" value ="${param.search}" class="ms-2"/>
                    <input type="submit" name="action" value="Search" />
                </form>

                <a href="adminProduct.jsp" class="btn btn-info" style="height: 40px" >Edit Product</a>
                
            </div>
            <c:if test="${requestScope.LIST_USER != null}">
                <c:if test="${not empty requestScope.LIST_USER}">
                    <table border="1" class="table table-bordered">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>User ID</th>
                                <th>Full Name</th>
                                <th>Role ID</th>
                                <th>Email</th>
                                <th>Password</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                            <form action="MainController" method="POST">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>
                                        <input type="text" name="userID" value="${user.userID}" readonly="readonly" />
                                    </td>
                                    <td>
                                        <input type="text" name="fullName" value="${user.fullName}" required="" />
                                    </td>
                                    <td>
                                        <input type="text" name="roleID" value="${user.roleID}" required="" />
                                    </td>
                                    <td>${user.email}</td>
                                    <td>${user.password}</td>
                                    <td>
                                        <input type="submit" value="Update" name="action" />
                                        <input type="hidden" name="search" value="${param.search}" />
                                    </td>
                                    <td>
                                        <c:url var="deleteLink" value="MainController">
                                            <c:param name="action" value="Delete"></c:param>
                                            <c:param name="search" value="${param.search}"></c:param>
                                            <c:param name="userID" value="${user.userID}"></c:param>
                                        </c:url>
                                        <a href="${deleteLink}">Delete</a>
                                    </td>
                                </tr>
                            </form>

                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </c:if>
            ${requestScope.ERROR}  
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>

    </body>
</html>
