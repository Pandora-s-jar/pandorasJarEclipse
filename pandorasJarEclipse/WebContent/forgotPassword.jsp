<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap-4.4.1-dist/css/bootstrap.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="css/bootstrap-4.4.1-dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/forgotPasswordStyle.css">
    <script src="scripts/forgotPasswordCheck.js"></script>
</head>
<body>
    <div class="container forgotPassword">
        <div class="form-group">
            <input type="email" class="form-input" name="email" id="address" placeholder="Inserisci la tua email" required/>
            <button id="email" onclick="firstStep()" class="btn-primary">RICHIEDI CODICE</button>
        </div>
        <form class="visible" id="confirmCode" method="get" action="${pageContext.request.contextPath}/resetPsw">
            <div class="form-group">
                <input type="text" class="form-input" name="code" id="code" placeholder="Inserisci il codice" required/>
                <input type="submit" class="form-submit" value="INVIA">
            </div>
        </form>
    </div>
</body>
</html>