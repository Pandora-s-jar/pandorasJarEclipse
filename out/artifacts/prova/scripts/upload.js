$(document).ready(function () {
    $("#confirmCode").hide();
});

function secondStep(){
    $.ajax({
        type : "POST",
        url : "/saveTemporarilyForUpload",
        data : {
            code : $("#code").val(),
            paymentCoordinates : $("#paymentCoordinates").val()
        },
        success: function(){
            window.location.replace("/gameDetails");
        },
        error : function () {
            alert("CODICE ERRATO");
        }
    })
}

//TODO: Refactorl pls

function firstStep() {
    $.ajax({
        type: "GET",
        url: "/sendCode",
        data: {
            email: $("#email").val()
        },
        success: ()=>{
            $("#confirmCode").css("display", "block");
        }
    })
}