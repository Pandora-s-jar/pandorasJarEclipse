let cont = 0;

function checkCode(event) {
    event.preventDefault();
    $.ajax({
       type: "POST",
       url: "/controlCode",
       data: {
           code : $("#code").val()
       },
        success : function(){
           window.location.replace("/nextPage");
        },
        error : function () {
            alert("CODICE ERRATO");
            cont++;
            if(cont >= 3){
                window.location.href = "http://www.google.it";
            }
        }
    });
}

function resendCode(event) {
    $.ajax({
        type: "GET",
        url: "/sendCode",
        success: function () {
            alert("CODICE REINVIATO");
        },
        error: function () {
            alert("IMPOSSIBILE REINVIARE IL CODICE. CONTATTARE L'ASSISTENZA");
        }
    });
}

function goBack(event) {
    window.location.replace("/previousPage");
}