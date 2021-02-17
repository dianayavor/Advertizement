<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="uk">
<head>
    <title>User list</title>
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
    <link href="<c:url value="css/user.css"/>" rel="stylesheet">
</head>
<body>
<div class="container" align="center">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">First name</th>
            <th scope="col">Last name</th>
            <th scope="col">Email</th>
            <th scope="col">Date of birth</th>
            <th scope="col">Is active account</th>
            <th scope="col">Role</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="i" value="1" scope="request"/>
        <c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.dateOfBirth}"/></td>
            <td><c:out value="${user.activeAccount}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td>
                <div class="btn-group" role="group">
                    <form action="users" method="post">
                        <input type="hidden" name="_method" value="post">
                        <input type="hidden" name="user" value="<c:if test="${user.id != null}">${user}</c:if>"/>
                        <button type="button" class="btn btn-outline-primary">Edit</button>
                    </form>
                    <form action="users" method="post">
                        <input type="hidden" name="_method" value="delete">
                        <input type="hidden" name="user" value="<c:if test="${user.id != null}">${user.id}</c:if>"/>
                        <button type="button" class="btn btn-outline-primary">Delete</button>
                    </form>
                        <%--                    <a href="edit?advertId=<c:out value='${user.id}' />"> Edit </a>--%>
                        <%--                    <a href="delete?advertId=<c:out value='${user.id}' />"> Delete </a>--%>
                </div>
            </td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
