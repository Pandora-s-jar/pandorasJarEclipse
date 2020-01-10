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