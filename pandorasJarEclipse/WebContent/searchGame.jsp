<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Games</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/searchGameStyle.css">
</head>

<body style="background-color: #284c67;">
    <jsp:include page="header.jsp"></jsp:include>
    <h1 class="text-left d-block" id="h1GiochiRicercati">Ecco i giochi che hai ricercato:</h1>
    <c:set var="index" scope="request" value="${0}"></c:set>
    <c:forEach items="${games}" var="game">
        <c:if test="${index % 4 == 0}">
            </div>
            <div class="row row-games">
        </c:if>
            <div class="col-xl-3" style="height: 100%;"><a href="/GameDataSheet?gameId=${game.id}"><img style="width: 100%;height: 100%;" src="${game.frontImage}"></a></div>
        <c:set var="index" scope="request" value="${index + 1}"></c:set>
    </c:forEach>
    </div>
    <jsp:include page="footer.html"></jsp:include>
</body>

</html>