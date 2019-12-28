$(document).ready(() => {
        $("#confirmCode").hide().submit((e)=>{
            $.ajax({
                type: "GET",
                url: "/confirmCode",
                data: {
                    email: $("#address").val(),
                    secretCode: $("#code").val()
                },
                success: ()=>{
                    //TODO: facciamo la grafica
                        alert("Email inviata");
                    },
                error: () =>{
                    //TODO: facciamo la grafica
                        alert("Il codice per questa email è errato");
                        window.location.replace(window.location); // simulate an HTTP GET req
                    }
                }
            );
        })
    }
);

function firstStep() {
    console.log("prova");
    $.ajax({
        type: "GET",
        url: "/sendForget",
        data: {
            email: $("#address").val()
        },
        success: ()=>{
            $("#confirmCode").css("display", "block ");
        }
    })
}