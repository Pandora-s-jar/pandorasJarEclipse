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
    <script src="scripts/gameDataSheetScript.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/gameDataSheet.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
</head>

<body style="background-color: #284c67;">
    <jsp:include page="header.jsp" />
    <div class="row" id="firstRow">
        <div class="col-xl-7" style="width: 60%;">
            <div class="carousel slide" data-ride="carousel" id="carousel-1">
                <div class="carousel-inner" role="listbox">
                    <c:set var="index" scope="request" value="${0}"></c:set>
                    <c:forEach items="${game.previewsIMG}" var="img">
                        <c:if test="${index == 0}">
                            <div class="carousel-item size-div-preview active">
                        </c:if>
                        <c:if test="${index > 0}">
                            <div class="carousel-item size-div-preview">
                        </c:if>
                                <img class="w-100 d-block float-left size-div-preview" src="${img}">
                            </div>

                        <c:set var="index" scope="request" value="${index + 1}"></c:set>
                    </c:forEach>

                    <c:forEach items="${game.previewsVID}" var="video">
                        <c:if test="${index == 0}">
                            <div class="carousel-item size-div-preview active">
                        </c:if>
                        <c:if test="${index > 0}">
                            <div class="carousel-item size-div-preview">
                        </c:if>
                            <iframe src="${video}" class="size-div-preview" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                        </div>
                        <c:set var="index" scope="request" value="${index + 1}"></c:set>
                    </c:forEach>

                </div>
                <div><a class="carousel-control-prev" href="#carousel-1" role="button" data-slide="prev"><span class="carousel-control-prev-icon"></span><span class="sr-only">Previous</span></a><a class="carousel-control-next" href="#carousel-1" role="button"
                        data-slide="next"><span class="carousel-control-next-icon"></span><span class="sr-only">Next</span></a></div>
                <ol class="carousel-indicators">
                    <c:forEach items="${totalSize}" var="i">
                        <c:if test="${i == 0}">
                            <li data-target="#carousel-1" data-slide-to="${i}" class="active"></li>
                        </c:if>
                        <c:if test="${i > 0}">
                            <li data-target="#carousel-1" data-slide-to="${i}"></li>
                        </c:if>
                    </c:forEach>
                </ol>
            </div>
        </div>
        <div class="col float-left" style="width: 40%;">
            <h1 class="text-center color-orange" >${game.name}</h1>
            <div>
                <textarea readonly class="border rounded" style="font-size: 20px; width: 100%; background-color: #e9ecef !important; resize: none;" rows="5">${game.description}</textarea>
                <div>
                    <label class="d-block label-game-info">Data Rilascio : ${game.release}</label>
                    <label class="d-block label-game-info">Sviluppatore : <a href="/profile?id=${game.idDeveloper}">${developer}</a></label>
                </div>
                <form class="text-center" method="post" action="/help?emailTo=${game.helpEmail}&send=false">
                    <button class="btn btn-primary border rounded background-color-orange" type="submit" id="richiediAssistenza">Richiedi assistenza</button>
                </form>
            </div>
        </div>
    </div>
    <div class="row" id="secondRow">
        <div class="col-xl-7" style="width: 60%;">
            <p class="d-inline" id="pCategory">Questo gioco appartiene alla categoria :&nbsp;</p><!--<a href="/?category=${game.category}" style="font-size: 20px;">-->${game.category}<!--</a>-->
        </div>
        <div class="col float-left" style="width: 40%;">
            <div class="float-left">
                <p id="prezzo"> PREZZO: ${game.price}€</p>
            </div>
            <c:if test="${logged}">
                <c:if test="${canBuy}">
                <!-- START PAYPAL PAYMENTS-->
                <div class ="float-right" id="paypal-button"></div>
                <script src="https://www.paypalobjects.com/api/checkout.js"></script>
                <script>
                    paypal.Button.render({
                        // Configure environment
                        env: 'sandbox',
                        client: {
                            sandbox: 'AYhJSV3hNi-glHZ2JbxjUXQrRf38UglWWi_HqB83rql0-0yZL_LeR1wr61bHRHsYXLwUArT6yFGadowe',
                            production: 'demo_production_client_id'
                        },
                        // Customize button (optional)
                        locale: 'it_IT',
                        style: {
                            size: 'large',
                            color: 'gold',
                            shape: 'pill',
                        },

                        // Enable Pay Now checkout flow (optional)
                        commit: true,

                        // Set up a payment
                        payment: function(data, actions) {
                            return actions.payment.create({
                                transactions: [{
                                    amount: {
                                        total: '${game.price}',
                                        currency: 'EUR'
                                    }
                                }]
                            });
                        },
                        // Execute the payment
                        onAuthorize: function(data, actions) {
                            return actions.payment.execute().then(function()
                            {
                                // Show a confirmation message to the buyer
                                var alert = window.alert('Pagamento avvenuto con successo!');
                                $.post("/PaymentSuccess",
                                    {
                                        data:JSON.stringify({idUser: ${userId}, idGame: ${game.id}, price: ${game.price}})
                                    });
                            });
                        }
                    }, '#paypal-button');

                </script>
                <!-- END PAYPAL PAYMENTS-->
            </c:if>
                <c:if test="${not canBuy}">
                    <h5 class="color-orange" id="giàAcquistato">Hai già acquistato questo gioco!</h5>
                </c:if>
            </c:if>
            <c:if test="${not logged}">
                <a href="#Login" class="background-color-orange border rounded" type="button" id="btnLogin2">Login</a>
            </c:if>
        </div>
    </div>
    <div class="row" id="thirdRow">
        <div class="col">
            <c:forEach items="${reviews}" var="review">
                <div class="border rounded" id="divReviews">
                    <label class="d-block color-orange" style="font-size: 20px;"><a href="/profile?id=${review.author}">${review.username}</a></label>
                    <label class="d-block color-orange" style="font-size: 20px;">${review.stars}</label>
                    <p class="p-review">${review.comment}</p>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="row" id="fourthRow">
        <table id="ranking" class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th class="th-color">#</th>
                    <th class="th-color">Username</th>
                    <th class="th-color">Score</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" scope="request" value="${0}"></c:set>
                <c:forEach items="${ranking}" var="rankI">
                    <c:set var="index" scope="request" value="${index + 1}"></c:set>
                    <tr>
                        <td class="td-color">${index}</td>
                        <td class="td-color">${rankI.username}</td>
                        <td class="td-color">${rankI.value}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <jsp:include page="footer.html" />

</body>

</html>