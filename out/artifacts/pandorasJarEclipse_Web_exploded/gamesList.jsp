<%@ page import="model.User" %>
<%@ page import="model.Game" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <nav class="navbar">
        <ul>
            <c:forEach items="${user.getLibrary()}" var="game">
                <li class="nav-link">
                    <button id="${game.getName()}" onclick="showGame(event)">${game.getName()}</button>
                </li>
            </c:forEach>
        </ul>
    </nav>
</body>
</html>