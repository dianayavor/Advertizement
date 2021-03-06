<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <style>
        .form-edit {
            width: 300px;
            align-content: center;
        }

        .label-edit {
            align-content: center;
        }

        .container {
            align-items: center;
        }

        body {
            background: #ededed;
        }

        .btn-edited {
            margin: 10px;
        }
    </style>
    <title>Edit profile</title>
</head>
<body>
<div class="edit-container" align="center">
    <h1 class="label-edit">Edit profile</h1>
    <div class="form-edit">
        <div class="container">
            <form action="edited" method="post">
                <div class="form-group">
                    <%--                    <label for="userId" class="form-label">Id</label>--%>
                    <input type="hidden" class="form-control" id="userId" name="userId" value="${requestScope.user.id}">
                </div>
                <div class="form-group">
                    <label for="inputFirstName" class="form-label">Please, enter your first name</label>
                    <input type="text" class="form-control" id="inputFirstName" name="firstname"
                           value="${requestScope.user.firstName}">
                </div>
                <div class="form-group">
                    <label for="inputLastName" class="form-label">Please, enter your last name</label>
                    <input type="text" class="form-control" id="inputLastName" name="lastname"
                           value="${requestScope.user.lastName}">
                </div>
                <div class="form-group">
                    <label for="inputEmail" class="form-label">Please, enter your email address</label>
                    <input type="email" class="form-control" id="inputEmail" aria-describedby="emailHelp"
                           name="email" placeholder="name@example.com" value="${requestScope.user.email}">
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="form-label">Enter password</label>
                    <input type="password" class="form-control" id="inputPassword" name="password" value="">
                </div>
                <div class="form-group">
                    <label for="repeatPassword" class="form-label">Repeat password</label>
                    <input type="password" class="form-control" id="repeatPassword" name="">
                </div>
                <div class="btn-edited">
                    <button type="submit" value="Submit" class="btn btn-primary">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
