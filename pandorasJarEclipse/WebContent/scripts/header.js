$(document).ready(()=>{
    if(Cookies.get("logged") === "false" || Cookies.get("logged") === undefined){
        $("#addGameLink").attr("href", "#Login");
        $("#addGameLink").attr("data-toggle", "modal");
        $("#libraryLink").attr("href", "#Login");
        $("#libraryLink").attr("data-toggle", "modal");
        $("#profileLink").attr("href", "#Login");
        $("#profileLink").attr("data-toggle", "modal");
    }
    $("#loginBtn").click((event)=>{
        $.ajax({
            type: "POST",
            url: "/login",
            data: {
                email: $("#logEmail").val(),
                password: $("#logPassword").val()
            },
            success: function () {
                $("#errorLabel").hide();
                window.location.replace(window.location);
            },
            error: function () {
                $("#errorLabel").show();
            }
        })
    })
});

$(document).ready(function () {
    let urlString = window.location;
    let url = new URL(urlString);
    let c = url.searchParams.get("registered");
    if(c === "true"){
        alert("REGISTRAZIONE EFFETTUATA");
    }
    //TODO : testami
});