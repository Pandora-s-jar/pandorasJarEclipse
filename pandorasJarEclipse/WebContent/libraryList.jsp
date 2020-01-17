<%@ page import="model.User" %>
<%@ page import="model.Game" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>LibraryMenu</title>
</head>

<body>
    <aside>
        <ul class="nav flex-column text-left shadow-none">
            <h1>${user.getUsername()}</h1>
            <c:forEach items="${user.getLibrary()}" var="game">
                <li class="nav-item shadow"><a class="nav-link active" id="${game.getName()}" onclick="showGame(event)">${game.getName()}</a></li>
            </c:forEach>
        </ul>
    </aside>

</body>

</html>