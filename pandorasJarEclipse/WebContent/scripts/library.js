function showGame(event) {
    $.ajax({
        type: "GET",
        url: "/getGameDetails",
        data: {
            name: event.target.id //TODO : check on other browser
        },
        success: function (game) {
            $("#title").text(game.name);
            $("#description").text(game.description);
            $("#specs").text(game.description);
            game.reviews.map(function (comment) {
                    $("#commentList").append("<li class=\"list-group-item\" style=\"margin-bottom:6px;\">\n" +
                        "                            <div class=\"media\">\n" +
                        "                                <div></div>\n" +
                        "                                <div class=\"media-body\">\n" +
                        "                                    <div class=\"media\" style=\"overflow:visible;\">\n" +
                        "                                        <div><img class=\"mr-3\" style=\"width: 25px; height:25px;\" src=\"assets/img/user-photo4.jpg\"></div>\n" +
                        "                                        <div class=\"media-body\" style=\"overflow:visible;\">\n" +
                        "                                            <div class=\"row\">\n" +
                        "                                                <div class=\"col-md-12\">\n" +
                        "                                                    <p><a href=\"#\">" + comment.username + "</a> "+comment.comment + " <br>\n" +
                        "                                                        <small class=\"text-muted\"> </small></p>\n" +
                        "                                                </div>\n" +
                        "                                            </div>\n" +
                        "                                        </div>\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                            </div>\n" +
                        "                        </li>")
                });
        },
        error: function (error) {
            alert("Hai messo mani dove non dovevi, verrai punito severamente per questo");
        }
    })
}

