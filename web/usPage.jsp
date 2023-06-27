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
    </head>
    <body>
    <h1>User's Information</h1>

    User ID ${sessionScope.LOGIN_USER.userID}<br/>
    Full Name ${sessionScope.LOGIN_USER.fullName}<br/>
    Password ${sessionScope.LOGIN_USER.password}<br/>
    Role ID ${sessionScope.LOGIN_USER.roleID}

</body>
</html>
