$(document).ready(() => {
        $("#confirmCode").hide();
    }
);

function secondStep() {
    $.ajax({
            type: "GET",
            url: "/confirmCode",
            data: {
                email: $("#address").val(),
                secretCode: $("#code").val()
            },
            success: ()=>{
                alert("Email inviata");
                window.location.replace("/resetPsw")
            },
            error: () =>{
                alert("Errore");
                window.location.replace(window.location);
            }
        },
    );
}
function firstStep() {
    console.log("prova");
    $.ajax({
        type: "GET",
        url: "/sendCode",
        data: {
            email: $("#address").val()
        },
        success: ()=>{
            $("#confirmCode").css("display", "block ");
        }
    })
}