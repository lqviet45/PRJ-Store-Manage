<%-- 
    Document   : usPage
    Created on : May 29, 2023, 11:05:27 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>USER Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

    </head>
    <body style="background-color: #9de2ff;">
        <section>
            <h1 class="text-center">User's Information</h1>
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col col-md-9 col-lg-7 col-xl-5">
                        <div class="card" style="border-radius: 15px;">
                            <div class="card-body p-4">
                                <div class="d-flex text-black">                                   
                                    <div class="flex-grow-1 ms-3">
                                        <h5 class="mb-1">${sessionScope.LOGIN_USER.fullName}</h5>
                                        <p class="mb-2 pb-1" style="color: #2b2a2a;">User ID: ${sessionScope.LOGIN_USER.userID}</p>
                                        <div class="d-flex justify-content-start rounded-3 p-2 mb-2"
                                             style="background-color: #efefef;">
                                            <div>
                                                <p class="small text-muted mb-1">${sessionScope.LOGIN_USER.password}</p>
                                            </div>
                                            <div class="px-3">
                                                <p class="small text-muted mb-1">role ID</p>
                                                <p class="mb-0">${sessionScope.LOGIN_USER.roleID}</p>
                                            </div>
                                        </div>
                                        <div class="d-flex pt-1 justify-content-between">
                                            <a href="shopping.html" class="btn btn-outline-primary me-1">Go to Shopping</a>
                                            <form action="MainController">
                                                <button type="submit" class="btn btn-primary" name="action" value="View">View Cart</button>
                                            </form>
                                            <form action="MainController">
                                                <button type="submit" class="btn btn-primary" name="action" value="Logout">LogOut</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>
</html>
