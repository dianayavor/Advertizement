<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="css/adverts.css" type="text/css">
    <title> List adverts </title>
    <style>
        .advert-list {
            padding-left: 200px;
            padding-right: 200px;
            padding-top: 30px;
        }

        .card-title {
            padding: 7px 8px 7px 12px;
            background-color: #e0e0e0;
            color: black;
            display: inline-flex;
            border-radius: 16px;
            align-items: end;
            font-size: 12px;
            border-bottom: groove;
            border-right: groove;
            width: fit-content;
            float: right;
            margin: 10px;
        }

        .card {
            border-bottom: groove;
            border-right: groove;
            margin: 5px;
        }

        body {
            background: #ededed;
        }

        .container-header {
            margin-left: 100px;
        }

        .link-my-profile {
            align-self: end;
        }

        .btn-group {
            align-self: end;
            width: 200px;
        }
    </style>
    <div class="navbar navbar-dark bg-dark">
        <div class="container-header">
            <a href="users" class="navbar-brand">Users</a>
            <a href="my-adverts" class="navbar-brand">My adverts</a>
            <a href="adverts" class="navbar-brand">Adverts</a>
            <span class="link-my-profile">
                <a href="" class="navbar-brand">${requestScope.user.firstName} ${requestScope.user.lastName}</a>
            </span>
        </div>
    </div>
</head>
<body>

<div class="advert-list" align="center">
    <form method="get" action="add_user.jsp">
        <button type="submit" name="button-add-advert">Add</button>
    </form>
    <div class="card text-dark bg-light mb-3">
        <c:forEach items="${requestScope.adverts}" var="advert">
            <div class="card">
                <h5 class="card-header">${advert.title}</h5>
                <div class="card-body">
                    <h5 class="card-title" align="right">${advert.heading}</h5>
                    <p class="card-text" align="justify">${advert.description}</p>
                </div>
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-success">active</button>
                    <form action="adverts/edit" method="post">
                        <input type="hidden" name="advertId" value="${advert.id}">
                        <button type="submit" class="btn btn-light">Edit</button>
                    </form>
                    <form action="adverts/delete" method="post">
                        <input type="hidden" name="advertId" value="${advert.id}">
                        <button type="submit" class="btn btn-light">Delete</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>