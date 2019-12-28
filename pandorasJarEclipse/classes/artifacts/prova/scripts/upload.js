$(document).ready(function () {
    $("#confirmCode").hide()
});

function secondStep(){
    $.ajax({
        type : "POST",
        url : "/saveTemporarilyForUpload",
        data : {
            code : $("#code").val(),
            paymentCoordinates : $("#paymentCoordinates").val()
        },
        error : function () {
            alert("CODICE ERRATO");
        }
    })
}

//TODO: Refactorl pls

function firstStep() {
    console.log("prova");
    $.ajax({
        type: "GET",
        url: "/sendCode",
        data: {
            email: $("#email").val()
        },
        success: ()=>{
            $("#confirmCode").css("display", "block ");
        }
    })
}