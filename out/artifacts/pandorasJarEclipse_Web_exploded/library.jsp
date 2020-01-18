<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap-4.4.1-dist/css/bootstrap.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="css/bootstrap-4.4.1-dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="scripts/library.js"></script>
</head>
<body>
    <div class="row">
        <div class="col">
            <jsp:include page="gamesList.jsp"></jsp:include>
        </div>
        <div class="col">
            <jsp:include page="gamePage.jsp"></jsp:include>
        </div>
    </div>
</body>
</html>