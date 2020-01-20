<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>User statistics</title>
        <meta charset="UTF-8">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="css/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap-4.4.1-dist/css/bootstrap.css">
        <link rel="stylesheet" href="css/userStatsStyle.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
        <script src="scripts/changeHeight.js"></script>

    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <div id="row">
            <div id="first_column">
                <jsp:include page="profileMenu.html"></jsp:include>
            </div>
            <div id="second_column">
                <div class="row" height="200px">
                    <div class="col-sm-4">
                        <div class="jumbotron container-stat">
                            <h2>Hours you played</h2>
                            <h3>${totalHoursPlayed}</h3>
                        </div>
                    </div>
                    <div class="col-sm-7">
                        <canvas id="hoursChart" class="canvas-size"></canvas>
                    </div>
                </div>
                <div class="row" height="200px">
                    <div class="col-sm-4">
                        <div class="jumbotron container-stat">
                            <h2>Games you played</h2>
                            <h3>${totalGamesPlayed}</h3>
                        </div>
                    </div>
                    <div class="col-sm-7">
                        <canvas id="gamesChart" class="canvas-size"></canvas>
                    </div>
                </div>
                <div class="row" height="200px">
                    <div class="col-sm-6">
                        <div class="jumbotron container-stat">
                            <h2>Best Score</h2>
                            <h3>${bestScoreValue}</h3>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="jumbotron container-stat">
                            <h2>Game</h2>
                            <h3>${bestScoreName}</h3>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- script for charts -->
        <script>
            var ctx = document.getElementById('hoursChart').getContext('2d');
            var chart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: ${hoursPlayedKeys},
                    datasets: [{
                        label: 'Hours',
                        backgroundColor: 'rgb(173, 216, 240)',
                        borderColor: 'rgb(255, 165, 0)',
                        data: ${hoursPlayedValues}
                    }]
                },
                options: {}
            });
            var ctx = document.getElementById('gamesChart').getContext('2d');
            var chart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: ${gamesPlayedKeys},
                    datasets: [{
                        label: 'Games',
                        backgroundColor: 'rgb(173, 216, 240)',
                        borderColor: 'rgb(255, 165, 0)',
                        data: ${gamesPlayedValues}
                    }]
                },
                options: {}
            });
        </script>


        <jsp:include page="footer.html"></jsp:include>
    </body>
</html>
