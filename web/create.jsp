<%-- Document : create Created on : Jun 8, 2023, 10:07:44 AM Author : DELL --%>

<%@page import="sample.user.UserError" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet" />

        <link href="./style/main.css" rel="stylesheet">
    </head>

    <body>
        <div class="main">

            <form action="MainController" method="POST" class="form" id="form-1">
                <h3 class="heading">Create new user</h3>

                <div class="spacer"></div>

                <div class="form-group">
                    <label for="userID" class="form-label">User ID</label>
                    <input id="userID" name="userID" type="text" placeholder="Enter your user ID"
                           class="form-control">
                    <span class="form-message">
                        ${requestScope.USER_ERROR.userIDError}
                    </span>
                </div>

                <div class="form-group">
                    <label for="fullname" class="form-label">Full Name</label>
                    <input id="fullname" name="fullName" type="text" placeholder="Enter your fullname"
                           class="form-control">
                    <span class="form-message">
                        ${requestScope.USER_ERROR.fullNameError}
                    </span>
                </div>

                <div class="form-group">
                    <label for="roleID" class="form-label">Role ID</label>
                    <input id="roleID" name="roleID" type="text" value="US" class="form-control"
                           readonly="">
                    <span class="form-message"></span>
                </div>

                <div class="form-group">
                    <label for="email" class="form-label">Email</label>
                    <input id="email" name="email" type="text" placeholder="Enter your email" class="form-control">
                    <span class="form-message">
                        ${requestScope.USER_ERROR.email}
                    </span>
                </div>

                <div class="form-group">
                    <label for="password" class="form-label">Password</label>
                    <input id="password" name="password" type="password" placeholder="Enter your password"
                           class="form-control">
                    <span class="form-message"></span>
                </div>

                <div class="form-group">
                    <label for="password_confirmation" class="form-label">Confirm Password</label>
                    <input id="password_confirmation" name="confirm" placeholder="Enter your password again"
                           type="password" class="form-control">
                    <span class="form-message">
                        ${requestScope.USER_ERROR.confirmError}
                    </span>
                </div>

                <input type="submit" name="action" value="Create" class="form-submit" />
                <input type="reset" value="Reset" class="form-reset" />

                <p>or sign up with:</p>
                <div class="login-google">
                    <a
                        href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=http://localhost:8080/PRJ301_T2S2_JSP/MainController?action=CreateGoogle&response_type=code&client_id=725358676694-le4itfrb327t4k6fe91h79t1qbddpsu0.apps.googleusercontent.com&approval_prompt=force">
                        <i class="fab fa-google" style="color: #9912f3;"></i>
                    </a>
                    <br/>
                    <span class="form-message">
                        ${requestScope.GOOGLE_USER_ERROR}
                    </span>
                </div>
                <span class="login-page-link">have account
                    <a href="MainController">login?</a>
                </span>
            </form>
        </div>
    </body>

</html>