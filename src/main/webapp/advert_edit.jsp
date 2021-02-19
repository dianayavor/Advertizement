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
    <style>
        .form-edit-advert {
            width: 600px;
            align-content: center;
        }

        .label-edit {
            align-content: center;
        }

        body {
            background: #ededed;
        }

        .dropdown, .btn-edited {
            margin: 10px;
        }

        .form-check {
            width: fit-content;
            align-content: end;
        }

        .form-label {
            align-content: start;
        }
    </style>
    <title>Edit advert</title>
</head>
<body>
<div class="edit-container-advert" align="center">
    <h1 class="label-edit">Edit advert: <i>${requestScope.advert.title}</i></h1>
    <form class="form-edit-advert" method="post" action="edited">
        <input type="hidden" name="advertId" value="${requestScope.advert.id}">
        <div class="form-group">
            <label for="title" class="form-label">Title</label>
            <input type="text" class="form-control" id="title" name="title" value="${requestScope.advert.title}">
        </div>

        <div class="form-group">
            <label for="description" class="form-label">Description</label>
            <div class="description-teaxarea">
                            <textarea rows="10" class="form-control" id="description"
                                      name="description">${requestScope.advert.description}</textarea>
            </div>
        </div>

        <div class="form-group">
            <label for="heading" class="form-label">Heading</label>
            <input type="text" class="form-control" id="heading" name="heading"
                   value="${requestScope.advert.heading}">
        </div>
<%--        <div class="form-check">--%>
<%--            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>--%>
<%--            <label class="form-check-label" for="flexCheckChecked">--%>
<%--                active--%>
<%--            </label>--%>
<%--        </div>--%>
<%--                    <div class="dropdown">--%>
        <%--                <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown"--%>
        <%--                        aria-expanded="false" aria-haspopup="true">--%>
        <%--                    Heading--%>
        <%--                </button>--%>
        <%--                <div class="dropdown-menu">--%>
        <%--                    <a class="dropdown-item" href="#">HAPPINESS</a>--%>
        <%--                    <a class="dropdown-item" href="#">SPORT</a>--%>
        <%--                    <a class="dropdown-item" href="#">GAMES</a>--%>
        <%--                    <a class="dropdown-item" href="#">PROGRAMING</a>--%>
        <%--                    <a class="dropdown-item" href="#">ANIMAL</a>--%>
        <%--                </div>--%>
        <%--            </div>--%>

<%--        <div class="form-check form-switch">--%>
<%--            <label for="isActive" class="form-label">active</label>--%>
<%--            <input class="form-check-input" type="checkbox" id="isActive" checked title="active">--%>
<%--        </div>--%>
        <div class="btn-edited">
            <button type="submit" value="Submit" class="btn btn-primary">Save</button>
        </div>
    </form>
</div>
</body>
</html>
