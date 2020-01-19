<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap-4.4.1-dist/css/bootstrap.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="css/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>
    <script src="scripts/library.js"></script>
</head>
<body>
    <div class="row">
        <div class="col-2">
            <jsp:include page="libraryList.jsp"></jsp:include>
        </div>
        <div id="gameDetails" class="col-10">
            <jsp:include page="libraryGamePage.html"></jsp:include>
        </div>
    </div>
</body>
</html>