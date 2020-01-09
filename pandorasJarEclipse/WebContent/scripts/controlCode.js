let cont = 0;
function checkCode(event) {
    $.ajax({
       type: "POST",
       url: "/controlCode",
       data: {
           code : $("#code").val()
       },
        success : function(){
            window.location.replace(sessionStorage.getItem("nextPage"))
        },
        error : function () {
            alert("CODICE ERRATO");
            cont++;
            if(cont >= 3){
                window.location.replace("www.google.it");
            }
        }
    });
}