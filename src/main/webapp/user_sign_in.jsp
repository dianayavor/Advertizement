<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="uk">
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
    <link rel="stylesheet" href="css/user.css" type="text/css">
    <title>Sign in</title>
</head>
<body>
<div class="login-container">
    <form class="form-login" method="post" action="adverts">
        <h1 class="label-sign-in">Log in</h1>
        <div class="container">
            <div class="sign-up-content">
                <div class="mb-4">
                    <label for="inputEmail" class="form-label">Please, enter your email address</label>
                    <input type="email" id="inputEmail" name="username">
                </div>
                <div class="mb-4">
                    <label for="inputPassword" class="form-label">Enter password</label>
                    <input type="password" id="inputPassword" name="password">
                </div>
                <button type="submit" class="btn btn-primary">Log in</button>
                <div>
                    <p class="registration-here">Haven't account yet?<a href="registration"
                                                                        class="registration-here-link"> Sign up </a>
                    </p>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
