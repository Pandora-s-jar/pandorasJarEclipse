<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Help</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Actor">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Andada">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="css/help.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</head>

<body>
    <div class="float-left divSinistra">
        <h3 class="text-monospace text-center">Benvenuto nella pagina dell'assistenza!</h3>
        <p class="lead text-monospace text-center text-secondary">Questa email sara' inviata a ${emailTo}</p>
        <img class="img-fluid" src="Assets/mailLogo.png" style="margin-left: 25%;"></div>
    <div class="float-left divDestra">
        <section class="td-form">
            <div class="row td-form-wrapper">
                <div class="col td-glass">
                    <form class="td-form-wrapper" method="POST" action="/help?send=true">
                        <div class="form-group">
                            <div class="col-md-12"><label class="text-dark" for="name" style="font-weight: bold;">Nome</label>
                                <div class="d-flex td-input-container">
                                    <div class="input-group" style="margin-left: 2%;">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text fa fa-address-book"></span>
                                        </div>
                                        <c:if test="${not logged}">
                                            <input id="name" class="form-control" type="text" placeholder="Il tuo nome" required>
                                        </c:if>
                                        <c:if test="${logged}">
                                            <input id="name" class="form-control" type="text" value = ${name} readonly>
                                        </c:if>
                                        <div class="input-group-append"></div>
                                </div>
                            </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12"><label class="text-dark" for="email" style="font-weight: bold;">Email</label>
                                <div class="d-flex td-input-container">
                                    <div class="input-group" style="margin-left: 2%;">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text fa fa-envelope"></span>
                                        </div>
                                        <c:if test="${not logged}">
                                            <input name="email" id="email" class="form-control" type="text" placeholder="something@example.com" required>
                                        </c:if>
                                        <c:if test="${logged}">
                                            <input name="email" id="email" class="form-control" type="text" value=${email} readonly>
                                        </c:if>
                                        <div class="input-group-append"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <label class="text-dark" for="subject" style="font-weight: bold;">Oggetto</label>
                                <div class="d-flex td-input-container">
                                    <div class="input-group" style="margin-left: 2%;">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text fa fa-info-circle"></span>
                                        </div>
                                            <input name="subject" id="subject" class="form-control" type="text" placeholder="Oggetto della richiesta" required>
                                        <div class="input-group-append"></div>
                                </div>
                            </div>
                        </div>
    </div>
    <div class="form-group">
        <div class="col-md-12">
            <label class="text-dark" for="message" style="font-weight: bold;">Messaggio</label>
            <div class="d-flex td-input-container">
                <i class="icon ion-android-create align-self-center"></i>
                <textarea name="message" class="form-control" placeholder="Corpo della richiesta" id="message" rows="6" cols="50"></textarea>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-12"><button class="btn btn-dark float-right" type="submit">Invia</button></div>
    </div>
    </form>
    </div>
    </div>
    </section>
    </div>
</body>

</html>