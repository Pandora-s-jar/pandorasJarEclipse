$(document).ready(function () {
    $("#gameDetails").hide();
    $("#addComment").click(function (event) {
        $.ajax({
            type: "POST",
            url: "/addComment",
            data: {
                content: $("#commentContent").val(),
                stars: $("#commentStars").val(),
                game: sessionStorage.getItem("gameID")
            },
            success: function () {
                window.location.reload();
            }
        })
    });
    $("#download").click(function (event) {
        window.location.replace("/downloadGame?id=" + sessionStorage.getItem("gameID"));
    })
});

function showGame(event) {
    $.ajax({
        type: "GET",
        url: "/getGameDetails",
        data: {
            name: event.target.id //TODO : check on other browser
        },
        success: function (game) {
            sessionStorage.setItem("gameID", game.id);
            $("#gameDetails").show();
            $("#title").text(game.name);
            $("#description").text(game.description);
            $("#specs").text(game.description);
            insertPreviews(game);
            insertRank(game);
            insertComments(game);
        },
        error: function (error) {
            alert("Hai messo mani dove non dovevi, verrai punito severamente per questo");
        }
    })
}


function insertPreviews(game) {
    $.ajax({
        type: "GET",
        url: "/getPreviewGame",
        data: {
            name: game.name
        },
        success: function (data) {
            $("#carousel").empty();
            data.map(function (b64) {
                $("#carousel").append(
                    "<div class=\"swiper-slide\"\n" +
                    "                 style=\"background-image: url(data:image/*;base64," + b64 + ");\"></div>\n" +
                    "            "
                )
            })
        },
        error: function () {
            $("#carousel").empty();
            $("#carousel").append("<h1> Non ci sono immagini di preview disponibili </h1>");
        }
    });
    $.ajax({
        type: "GET",
        url: "/getGameLinks",
        data: {
            gameID: game.id
        },
        success: function (data) {
            data.map(function (link) {
                $("#carousel").append(
                    "<div class=\"swiper-slide\">" +
                    "<iframe width=\"560\" height=\"315\" src=\""+link+"\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>" +
                    " </div>\n"
                )
            })
        }
    })
}

function insertRank(game) {
    $.ajax({
        type: "GET",
        url: "/getRank",
        data: {
            id: game.id
        },
        success: function (data) {
            data.map(function (score) {
                $("#ranks").append("<li>".concat(score.value).concat(" <- ").concat(score.username).concat("</li>"));
            })
        }
    })
}

function insertComments(game) {
    let commentList = document.getElementById("commentList");
    let row = commentList.children[0];
    for (let i = 0; i < game.reviews.length; ++i) {
        if (commentList.children[i] === undefined) {
            let newRow = $(row).clone();
            $(commentList).append(newRow);
        }
        let username = commentList.children[i].children[0].children[0].children[0].children[1].children[0].children[0].children[0];
        let src = commentList.children[i].children[0].children[0].children[0].children[0].children[0];
        console.log(src);
        setUser(game.reviews[i].author, username, src);
        let rating = commentList.children[i].children[0].children[0].children[0].children[1].children[0].children[0].children[1];
        let stars = game.reviews[i].stars.length;
        for (let j = 1; j <= 5; ++j) {
            rating.children[j - 1].className = "fa fa-star";
            if (j <= stars) {
                rating.children[j - 1].style = "color: orange;"
            } else {
                rating.children[j - 1].style = "";
            }
        }
        let p = commentList.children[i].children[0].children[0].children[0].children[1].children[0].children[0].children[2];
        $(p).html(game.reviews[i].comment);
    }
}

function setUser(id, username, src) {
    $.ajax({
        type: "POST",
        url: "/getUserForComment",
        data: {
            id: id
        },
        success: function (user) {
            $(username).html(user.username);
            username.href = "profile?id=" + user.id;
            src.src = "/PrintImage?id=" + user.id;
        },
        error: function () {
            alert("QUALCOSA NON VA NELLA SEZIONE COMMENTI");
        },
    })
}
