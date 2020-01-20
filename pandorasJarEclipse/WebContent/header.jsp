<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="css/headerStyle.css">
    <link rel="stylesheet" href="css/Login-Form-Dark.css">
    <script src="https://cdn.jsdelivr.net/npm/js-cookie@beta/dist/js.cookie.min.js"></script>
    <script src="scripts/header.js"></script>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-sm bg-info navbar-dark">
        <ul class="navbar-nav nav-fill w-100">
            <li class="navbar-brand">
                <a class="nav-link" href="/">
                    <figure>
                        <img alt="Logo" src="Assets/logo.png" width="100%" height="100%">
                    </figure>
                </a>
            </li>
            <li class="nav-item ">
                <a href="${pageContext.request.contextPath}/profile" id="profileLink" class="nav-link ">PROFILO</a>
            </li>
            <li class="nav-item ">
                <a href="library" class="nav-link" id="libraryLink">LIBRERIA</a>
            </li>
            <li class="nav-item ">
                <a href="upload" id="addGameLink" class="nav-link">AGGIUNGI UN GIOCO</a>
            </li>
            <li class="nav-item">
                <a href="help" class="nav-link">ASSISTENZA</a>
            </li>
            <li class="nav-item">
                <div class="search">
                    <form action="/Search" method="get">
                        <input type="text" name="ricerca" class="form-control input-sm" maxlength="64" placeholder="Ricerca"/>
                        <button type="submit" class="btn btn-primary btn-sm fa fa-search"></button>
                    </form>
                </div>
            </li>
            <% if (request.getSession().getAttribute("logged") == null || !(boolean) request.getSession().getAttribute("logged")) {%>
            <li>
                <nav class="navbar hidable">
                    <a class="nav-link" href="#Login" data-toggle="modal">ACCEDI</a>
                    <a style="font-size: 180%; color: #5a6268">|</a>
                    <a class="nav-link" href="register">REGISTRATI</a>
                </nav>
            </li>
            <%} else if (request.getSession().getAttribute("logged") != null && (boolean) request.getSession().getAttribute("logged")) {%>
            <li>
                <nav class="navbar hidable">
                    <a class="nav-link" href="${pageContext.request.contextPath}/logout">LOGOUT</a>
                </nav>
            </li>
            <%}%>
        </ul>
    </nav>
</header>
<div id="Login" class="modal fade modal-get-offer">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="login-dark">
                <form>
                    <h2 class="sr-only">Login Form</h2>
                    <div class="illustration"><i class="icon ion-ios-locked-outline"></i></div>
                    <div class="form-group"><input class="form-control" type="email" id="logEmail" name="email" placeholder="Email">
                    </div>
                    <div class="form-group"><input class="form-control" type="password" id="logPassword" name="password"
                                                   placeholder="Password"></div>
                    <div class="form-group">
                        <label id="errorLabel" style="display: none"> Email o password errate! Riprova </label>
                        <button type="button" class="btn btn-primary btn-block" id="loginBtn">Log In</button>
                    </div>
                    <a class="forgot" href="forgotPassword">Hai dimenticato la tua password?</a></form>
            </div>
        </div>
    </div>
</div>
</body>
</html>