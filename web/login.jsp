<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet" />
        <link href="./assets/style/main.css" rel="stylesheet" type="text/css" >
    </head>
    <body>
        <div class="main">
            <form action="MainController" method="POST" class="form" >
                <h3 class="heading">Input your information</h3>
                <div class="spacer"></div>
                <!--UserID <input type="text" name="userID" required="" /><br/>-->
                <div class="form-group">
                    <label for="UserID" class="form-label">UserID</label>
                    <input id="UserID" name="userID" type="text" placeholder="Enter UserID" class="form-control" required="">
                    <span class="form-message"></span>
                </div>

                <!--Password <input type="password" name="password" required="" /><br/>-->
                <div class="form-group">
                    <label for="password" class="form-label">Password</label>
                    <input id="password" name="password" type="password" placeholder="Enter password" class="form-control" required="">
                    <span class="form-message"></span>
                </div>

                <div class="g-recaptcha" data-sitekey="6LfVUokmAAAAAKFa960WgSh_UcWZ1GPCwii5bwOn"></div>
                <div class="error" style="font-size: 16px; padding-top: 12px"></div>

                <input type="submit" name="action" value="Login" class="form-submit"/>
                <input type="reset" value="Reset" class="form-reset"/>

                <p class="warning-mess">${requestScope.ERROR}</p>

                <span class="create-page-link">Don't have account 
                    <a href="MainController?action=CreatePage">Create user?</a>
                </span>
                <p>or sign in with:</p>
                <div class="login-google">
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=http://localhost:8080/PRJ301_T2S2_JSP_JSTL/MainController?action=LoginGoogle&response_type=code&client_id=725358676694-p17kmkch5e7snmmvic2mftgfa337vkf2.apps.googleusercontent.com&approval_prompt=force">
                        <i class="fab fa-google" style="color: #9912f3;"></i>
                    </a>
                </div>

            </form>
        </div>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>

        <script>
            var form = document.querySelector(".form");
            var error = document.querySelector(".error");
            form.addEventListener("submit", function (e) {
                const response = grecaptcha.getResponse();
                if (response) {
                    form.submit();
                } else {
                    e.preventDefault();
                    error.innerHTML = "Please check the captcha";
                }
            });
        </script>
    </body>
</html>
