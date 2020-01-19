

<!DOCTYPE html>
<html>
    <head>
        <title>User statistics</title>
        <meta charset="UTF-8">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"  crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="css/bootstrap-4.4.1-dist/js/bootstrap.min.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/bootstrap-4.4.1-dist/css/bootstrap.css">
        <link rel="stylesheet" href="css/userStatsStyle.css">
        <script src="scripts/userStatsScript.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
    </head>
    <body>
        <header>
            <jsp:include page="header.jsp"></jsp:include>
        </header>
        <div id="row">
            <div id="first_column">
                <jsp:include page="profileMenu.html"></jsp:include>
            </div>
            <div id="second_column">
                <div class="row" height="200px">
                    <div class="col-sm-4">
                        <div class="jumbotron container-stat">
                            <h2>Giochi venduti in totale:</h2>
                            <h3>${totalSold}</h3>
                        </div>
                    </div>
                    <div class="col-sm-7">
                        <canvas id="soldGamesChart" class="canvas-size"></canvas>
                    </div>
                </div>
                <div class="row" height="200px">
                    <div class="col-sm-4">
                        <div class="jumbotron container-stat">
                            <h2>Totale guadagnato</h2>
                            <h3>${totalMoney}</h3>
                        </div>
                    </div>
                    <div class="col-sm-7">
                        <canvas id="moneyEarnedChart" class="canvas-size"></canvas>
                    </div>
                </div>
            </div>
        </div>

        <!-- script for charts -->
        <script>
            var ctx = document.getElementById('soldGamesChart').getContext('2d');
            var chart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: ${soldGameKeys},
                    datasets: [{
                        label: 'Numero giochi venduti',
                        backgroundColor: 'rgb(173, 216, 240)',
                        borderColor: 'rgb(255, 165, 0)',
                        data: ${soldGameValues}
                    }]
                },
                options: {}
            });
            var ctx = document.getElementById('moneyEarnedChart').getContext('2d');
            var chart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: ${moneyEarnedKeys},
                    datasets: [{
                        label: 'Guadagno totale',
                        backgroundColor: 'rgb(173, 216, 240)',
                        borderColor: 'rgb(255, 165, 0)',
                        data: ${moneyEarnedValues}
                    }]
                },
                options: {}
            });
        </script>


        <footer>
            <jsp:include page="footer.html"></jsp:include>
        </footer>
    </body>
</html>
