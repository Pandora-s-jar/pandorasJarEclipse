<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Homepage</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <script src="scripts/indexScript.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/indexStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
</head>

<body>
    <jsp:include page="header.jsp" />
    <h1 class="text-center">Giochi best sellers</h1>
    <div class="carousel slide bestSellers-size-slide" data-ride="carousel" id="carousel-1">
        <div class="carousel-inner bestSellers-size-inner" role="listbox">
            <div class="carousel-item bestSellers-size-item active">
                <a href="/GameDataSheet?gameId=${firstGameBestSellers.id}"><img class="d-block bestSellers-size-img" src="${firstGameBestSellers.frontImage}"></a>
            </div>
            <div class="carousel-item bestSellers-size-item">
                <a href="/GameDataSheet?gameId=${secondGameBestSellers.id}"><img class="d-block bestSellers-size-img " src="${secondGameBestSellers.frontImage}"></a>
            </div>
            <div class="carousel-item bestSellers-size-item">
                <a href="/GameDataSheet?gameId=${thirdGameBestSellers.id}"><img class="d-block bestSellers-size-img " src="${thirdGameBestSellers.frontImage}"></a>
            </div>
            <div>
                <a class="carousel-control-prev" href="#carousel-1" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carousel-1" role="button" data-slide="next">
                    <span class="carousel-control-next-icon"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
            <ol class="carousel-indicators">
                <li data-target="#carousel-1" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-1" data-slide-to="1"></li>
                <li data-target="#carousel-1" data-slide-to="2"></li>
            </ol>
        </div>
    </div>

    <div class="category-games border rounded">
        <h2>Shooter</h2>
        <div class="container-fluid category-games-container-fluid-size">
            <div id="multi-item-shooter" class="carousel slide carousel-multi-item category-games-multi-item-size" data-ride="carousel">

                <div class="controls-top bs">
                    <a class="btn-floating" href="#multi-item-shooter" data-slide="prev"><i class="fa fa-chevron-left color-arrow"></i></a>
                    <a class="btn-floating" href="#multi-item-shooter" data-slide="next"><i class="fa fa-chevron-right color-arrow"></i></a>
                </div>

                <ol class="carousel-indicators">
                    <c:forEach items="${lengthGamesDiv6Shooter}" var="indexGame">
                        <c:if test="${indexGame == 0}">
                            <li data-target="#multi-item-shooter" data-slide-to="${indexGame}" class="active"></li>
                        </c:if>
                        <c:if test="${indexGame != 0}">
                            <li data-target="#multi-item-shooter" data-slide-to="${indexGame}"></li>
                        </c:if>
                    </c:forEach>
                </ol>

                <c:set var="index" scope="request" value="${0}"></c:set>
                <c:set var="active" scope="request" value="active"></c:set>
                <div class="carousel-inner category-games-inner-size" role="listbox">
                    <c:forEach items="${shooterGames}" var="game">
                        <c:if test="${index > 5}">
                            <c:set var="active" scope="request" value=""></c:set>
                        </c:if>
                        <c:if test="${index%6 == 0}">
                            <c:if test="${index > 0}">
                                    </div> <!-- row -->
                                </div> <!-- item -->
                            </c:if>
                            <div class="carousel-item ${active} category-games-item" >
                                <div class="row category-games-row">
                        </c:if>
                        <div class="col-md-2 clearfix d-none d-md-block image-game-div" >
                            <a href="/GameDataSheet?gameId=${game.id}"><img class="card-img-top image-game" src="${game.frontImage}"></a>
                        </div>
                        <c:set var="index" scope="request" value="${index + 1}"></c:set>

                    </c:forEach>
                    </div> <!-- row -->
                    </div> <!-- item -->
                </div>
            </div>
        </div>
    </div>

    <div class="category-games border rounded">
        <h2>Arcade</h2>
        <div class="container-fluid category-games-container-fluid-size">
            <div id="multi-item-arcade" class="carousel slide carousel-multi-item category-games-multi-item-size" data-ride="carousel">

                <div class="controls-top bs">
                    <a class="btn-floating" href="#multi-item-arcade" data-slide="prev"><i class="fa fa-chevron-left color-arrow"></i></a>
                    <a class="btn-floating" href="#multi-item-arcade" data-slide="next"><i class="fa fa-chevron-right color-arrow"></i></a>
                </div>

                <ol class="carousel-indicators">
                    <c:forEach items="${lengthGamesDiv6Arcade}" var="indexGame">
                        <c:if test="${indexGame == 0}">
                            <li data-target="#multi-item-arcade" data-slide-to="${indexGame}" class="active"></li>
                        </c:if>
                        <c:if test="${indexGame != 0}">
                            <li data-target="#multi-item-arcade" data-slide-to="${indexGame}"></li>
                        </c:if>
                    </c:forEach>
                </ol>

                <c:set var="index" scope="request" value="${0}"></c:set>
                <c:set var="active" scope="request" value="active"></c:set>
                <div class="carousel-inner category-games-inner-size" role="listbox">
                    <c:forEach items="${arcadeGames}" var="game">
                    <c:if test="${index > 5}">
                        <c:set var="active" scope="request" value=""></c:set>
                    </c:if>
                    <c:if test="${index%6 == 0}">
                    <c:if test="${index > 0}">
                </div> <!-- row -->
            </div> <!-- item -->
            </c:if>
            <div class="carousel-item ${active} category-games-item">
                <div class="row category-games-row">
                    </c:if>
                    <div class="col-md-2 clearfix d-none d-md-block image-game-div" >
                        <a href="/GameDataSheet?gameId=${game.id}"><img class="card-img-top image-game" src="${game.frontImage}"></a>
                    </div>
                    <c:set var="index" scope="request" value="${index + 1}"></c:set>

                    </c:forEach>
                </div> <!-- row -->
            </div> <!-- item -->
        </div>
    </div>
    </div>
    </div>

    <div class="category-games border rounded">
        <h2>Azione</h2>
        <div class="container-fluid category-games-container-fluid-size">
            <div id="multi-item-azione" class="carousel slide carousel-multi-item category-games-multi-item-size" data-ride="carousel">

                <div class="controls-top bs">
                    <a class="btn-floating" href="#multi-item-azione" data-slide="prev"><i class="fa fa-chevron-left color-arrow"></i></a>
                    <a class="btn-floating" href="#multi-item-azione" data-slide="next"><i class="fa fa-chevron-right color-arrow"></i></a>
                </div>

                <ol class="carousel-indicators">
                    <c:forEach items="${lengthGamesDiv6Azione}" var="indexGame">
                        <c:if test="${indexGame == 0}">
                            <li data-target="#multi-item-azione" data-slide-to="${indexGame}" class="active"></li>
                        </c:if>
                        <c:if test="${indexGame != 0}">
                            <li data-target="#multi-item-azione" data-slide-to="${indexGame}"></li>
                        </c:if>
                    </c:forEach>
                </ol>

                <c:set var="index" scope="request" value="${0}"></c:set>
                <c:set var="active" scope="request" value="active"></c:set>
                <div class="carousel-inner category-games-inner-size" role="listbox">
                    <c:forEach items="${azioneGames}" var="game">
                    <c:if test="${index > 5}">
                        <c:set var="active" scope="request" value=""></c:set>
                    </c:if>
                    <c:if test="${index%6 == 0}">
                    <c:if test="${index > 0}">
                </div> <!-- row -->
            </div> <!-- item -->
            </c:if>
            <div class="carousel-item ${active} category-games-item">
                <div class="row category-games-row">
                    </c:if>
                    <div class="col-md-2 clearfix d-none d-md-block image-game-div" >
                        <a href="/GameDataSheet?gameId=${game.id}"><img class="card-img-top image-game" src="${game.frontImage}"></a>
                    </div>
                    <c:set var="index" scope="request" value="${index + 1}"></c:set>

                    </c:forEach>
                </div> <!-- row -->
            </div> <!-- item -->
        </div>
    </div>
    </div>
    </div>

    <div class="category-games border rounded">
        <h2>Avventura</h2>
        <div class="container-fluid category-games-container-fluid-size">
            <div id="multi-item-avventura" class="carousel slide carousel-multi-item category-games-multi-item-size" data-ride="carousel">

                <div class="controls-top bs">
                    <a class="btn-floating" href="#multi-item-avventura" data-slide="prev"><i class="fa fa-chevron-left color-arrow"></i></a>
                    <a class="btn-floating" href="#multi-item-avventura" data-slide="next"><i class="fa fa-chevron-right color-arrow"></i></a>
                </div>

                <ol class="carousel-indicators">
                    <c:forEach items="${lengthGamesDiv6Avventura}" var="indexGame">
                        <c:if test="${indexGame == 0}">
                            <li data-target="#multi-item-avventura" data-slide-to="${indexGame}" class="active"></li>
                        </c:if>
                        <c:if test="${indexGame != 0}">
                            <li data-target="#multi-item-avventura" data-slide-to="${indexGame}"></li>
                        </c:if>
                    </c:forEach>
                </ol>

                <c:set var="index" scope="request" value="${0}"></c:set>
                <c:set var="active" scope="request" value="active"></c:set>
                <div class="carousel-inner category-games-inner-size" role="listbox">
                    <c:forEach items="${avventuraGames}" var="game">
                    <c:if test="${index > 5}">
                        <c:set var="active" scope="request" value=""></c:set>
                    </c:if>
                    <c:if test="${index%6 == 0}">
                    <c:if test="${index > 0}">
                </div> <!-- row -->
            </div> <!-- item -->
            </c:if>
            <div class="carousel-item ${active} category-games-item">
                <div class="row category-games-row">
                    </c:if>
                    <div class="col-md-2 clearfix d-none d-md-block image-game-div" >
                        <a href="/GameDataSheet?gameId=${game.id}"><img class="card-img-top image-game" src="${game.frontImage}"></a>
                    </div>
                    <c:set var="index" scope="request" value="${index + 1}"></c:set>

                    </c:forEach>
                </div> <!-- row -->
            </div> <!-- item -->
        </div>
    </div>
    </div>
    </div>

    <jsp:include page="footer.html" />
</body>

</html>