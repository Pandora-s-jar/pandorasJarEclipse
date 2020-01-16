<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>gameDataSheet</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/gameDataSheet.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
</head>

<body style="background-color: #284c67;">
    <jsp:include page="header.jsp" />
    <div class="row" style="margin-left: 5%;margin-right: 5%;margin-top: 5%;">
        <div class="col-xl-7" style="width: 60%;">
            <div class="carousel slide" data-ride="carousel" id="carousel-1">
                <div class="carousel-inner" role="listbox">
                    <div class="carousel-item active"><img class="w-100 d-block float-left" src="https://cdn.bootstrapstudio.io/placeholders/1400x800.png" alt="Slide Image"></div>
                    <div class="carousel-item"><img class="w-100 d-block" src="https://cdn.bootstrapstudio.io/placeholders/1400x800.png" alt="Slide Image"></div>
                    <div class="carousel-item"><img class="w-100 d-block" src="https://cdn.bootstrapstudio.io/placeholders/1400x800.png" alt="Slide Image"></div>
                </div>
                <div><a class="carousel-control-prev" href="#carousel-1" role="button" data-slide="prev"><span class="carousel-control-prev-icon"></span><span class="sr-only">Previous</span></a><a class="carousel-control-next" href="#carousel-1" role="button"
                        data-slide="next"><span class="carousel-control-next-icon"></span><span class="sr-only">Next</span></a></div>
                <ol class="carousel-indicators">
                    <li data-target="#carousel-1" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-1" data-slide-to="1"></li>
                    <li data-target="#carousel-1" data-slide-to="2"></li>
                </ol>
            </div>
        </div>
        <div class="col float-left" style="width: 40%;">
            <h1 class="text-center" style="color: rgb(255,165,0);">${game.name}</h1>
            <div>
                <textarea readonly class="border rounded" style="font-size: 20px; width: 100%; background-color: #e9ecef !important; resize: none;" rows="5">${game.description}</textarea>
                <div>
                    <label class="d-block" style="font-size: 20px;color: rgb(207,204,204);font-weight: bold;">Data Rilascio : ${game.release}</label>
                    <label class="d-block" style="font-size: 20px;color: rgb(207,204,204);font-weight: bold;">Sviluppatore : <a href="/profile?id=${game.idDeveloper}">${developer}</a></label>
                </div>
                <form class="text-center" method="post" action="/help?email=${game.helpEmail}">
                    <button class="btn btn-primary border rounded" type="submit" style="background-color: rgb(255,165,0);font-size: 18px;margin-top: 2%;">Richiedi assistenza</button>
                </form>
            </div>
        </div>
    </div>
    <div class="row" style="margin-left: 5%;margin-right: 5%;margin-top: 1%;">
        <div class="col-xl-7" style="width: 60%;">
            <p class="d-inline" style="font-size: 20px;color: rgb(207,204,204);">Questo gioco appartiene alla categoria :&nbsp;</p><a href="/?category=${game.category}" style="font-size: 20px;">${game.category}</a></div>
        <div class="col float-left" style="width: 40%;">
            <div></div>
        </div>
    </div>
    <div class="row" style="margin-right: 5%;margin-left: 5%;margin-top: 1%;">
        <div class="col">
            <c:forEach items="${reviews}" var="review">
                <div class="border rounded" style="background-color: rgb(26,26,78);padding-left:2%;">
                    <label class="d-block" style="font-size: 20px;color: rgb(255,165,0);"><a href="/profile?id=${review.author}">${review.username}</a></label>
                    <label class="d-block" style="font-size: 20px;color: rgb(207,204,204);">${review.stars}</label>
                    <p style="font-size: 20px;color: rgb(207,204,204); margin-left: ">${review.comment}</p>
                </div>
            </c:forEach>
        </div>
    </div>
    <!--<div class="row" style="margin-right: 5%;margin-left: 5%;margin-top: 2%;">
        <table id="ranking" class="table table-striped table-bordered" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th class="th-color">#</th>
                    <th class="th-color">Username</th>
                    <th class="th-color">Score</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="td-color">${rankI.position}</td>
                    <td class="td-color">${rankI.username}</td>
                    <td class="td-color">${rankI.score}</td>
                </tr>
            </tbody>
        </table>
    </div>-->
    <jsp:include page="footer.html" />

</body>

</html>