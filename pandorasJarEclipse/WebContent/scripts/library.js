$(document).ready(function () {
    $("#gameDetails").hide();
});

function showGame(event) {
    $.ajax({
        type: "GET",
        url: "/getGameDetails",
        data: {
            name: event.target.id //TODO : check on other browser
        },
        success: function (game) {
            $("#gameDetails").show();
            $("#title").text(game.name);
            $("#description").text(game.description);
            $("#specs").text(game.description);
            insertComments(game);
        },
        error: function (error) {
            alert("Hai messo mani dove non dovevi, verrai punito severamente per questo");
        }
    })
}

function insertComments(game) {
    let commentList = document.getElementById("commentList");
    let row = commentList.children[0];
    for(let i = 0; i < game.reviews.length; ++i){
        if(commentList.children[i] === undefined){
            let newRow = $(row).clone();
            $(commentList).append(newRow);
        }
        let username = commentList.children[i].children[0].children[0].children[0].children[1].children[0].children[0].children[0];
        let src = commentList.children[i].children[0].children[0].children[0].children[0].children[0];
        console.log(src);
        setUser(game.reviews[i].author, username, src);
        let rating = commentList.children[i].children[0].children[0].children[0].children[1].children[0].children[0].children[1];
        let stars = game.reviews[i].stars.length;
        for(let j = 1; j <= 5; ++j){
            rating.children[j-1].className = "fa fa-star";
            if(j <= stars){
                rating.children[j-1].style = "color: orange;"
            }
            else{
                rating.children[j-1].style = "";
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
            id : id
        },
        success: function (user) {
            $(username).html(user.username);
            username.href = "profile?id="+user.id;
            src.src = "/PrintImage?id="+user.id;
        },
        error: function () {
            alert("QUALCOSA NON VA NELLA SEZIONE COMMENTI");
        }
    })
}
