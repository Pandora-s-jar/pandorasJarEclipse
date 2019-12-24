<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap-4.4.1-dist/css/bootstrap.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="css/bootstrap-4.4.1-dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/headerStyle.css">
</head>
<body>
    <header> 
        <nav class="navbar navbar-expand-sm bg-info navbar-dark">
            <ul class="navbar-nav nav-fill w-100">
                <li class="navbar-brand">
                    <a class="nav-link" href="home.html">
                        <figure>
                            <img alt="Logo" src = "Assets/logo.png" width="100%" height="100%">
                        </figure>
                    </a>
                </li>
                <li class="nav-item ">
                    <a href="#" class="nav-link ">PROFILO</a>
                </li>
                <li class="nav-item ">
                    <a href="#" class="nav-link">LIBRERIA</a>
                </li>
                <li class="nav-item ">
                    <a href="#" class="nav-link">AGGIUNGI UN GIOCO</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">ASSISTENZA</a>
                </li>
                <li class="nav-item">
                    <div class="search">
                        <input type="text" class="form-control input-sm" maxlength="64" placeholder="Ricerca"/>
                        <button type="submit" class="btn btn-primary btn-sm fa fa-search"></button>
                    </div>
                </li>
                <% if(request.getSession().getAttribute("logged") == null || !(boolean)request.getSession().getAttribute("logged")){%>
                    <li>
                        <span class="navbar hidable">
                                <a class="nav-link" href="#Login" data-toggle="modal">ACCEDI</a>
                                <a style="font-size: 180%; color: #5a6268">|</a>
                                <a class="nav-link" href="register">REGISTRATI</a>
                        </span>
                    </li>
                <%}else if (request.getSession().getAttribute("logged") != null && (boolean)request.getSession().getAttribute("logged")){%>
                    <li>
                        <form method="post" action="${pageContext.request.contextPath}/logout">
                            <input type="submit" value="LogOut"/> <% //TODO: E' il cenone di natale, lo sposto il 26
                            %>
                        </form>
                    </li>
                <%}%>
            </ul>
        </nav>
    </header>
    <div id="Login" class="modal fade modal-get-offer">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h2>ACCEDI</h2>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                </div>
                <div class="modal-body">
                    <form class="form" method="post" action="${pageContext.request.contextPath}/login">
                        <div class="form-group">
                            <input type="email" class="form-control" name="email" placeholder="Inserisci la tua email" required/>
                            <input type="password" class="form-control" name="password" placeholder="Inserisci la tua password" required/>
                            Hai dimenticato la password? <a href="forgot.html">Clicca Qui!</a><br>
                            Non hai un account? <a href="register.html">Registrati!</a><br>
                            <button type="submit" id="btnLogin">LOGIN!</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>