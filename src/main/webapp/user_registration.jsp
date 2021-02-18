<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"
            integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"
            integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="css/user.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>Sign up</title>
</head>
<body>
<div class="registration-container" align="center">
    <h1 class="label-sign-up">Sign up</h1>
    <div class="form-registration">
        <div class="container">
            <form action="registration" method="post">
                <div class="form-group">
                    <label for="inputFirstName" class="form-label">Please, enter your first name</label>
                    <input type="text" class="form-control" id="inputFirstName" name="firstname">
                </div>
                <div class="form-group">
                    <label for="inputLastName" class="form-label">Please, enter your last name</label>
                    <input type="text" class="form-control" id="inputLastName" name="lastname">
                </div>
                <div class="form-group">
                    <label for="inputEmail" class="form-label">Please, enter your email address</label>
                    <input type="email" class="form-control" id="inputEmail" aria-describedby="emailHelp"
                           name="email" placeholder="name@example.com">
                </div>
                <div class="form-group">
                    <label for="inputDOB" class="form-label">Please, choose your date of birthday</label>
                    <input type="date" class="form-control" id="inputDOB"
                           aria-describedby="emailHelp" name="dob">
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="form-label">Enter password</label>
                    <input type="password" class="form-control" id="inputPassword" name="password">
                </div>
                <div class="form-group">
                    <label for="repeatPassword" class="form-label">Repeat password</label>
                    <input type="password" class="form-control" id="repeatPassword" name="passwordRepeat">
                </div>
                <div class="btn-signup">
                    <button type="submit" value="Submit" class="btn btn-primary">Submit</button>
                </div>
                <div>
                    <p class="login-here">Already have an account?<a href="login" class="login-here-link">
                        Log in </a>
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
