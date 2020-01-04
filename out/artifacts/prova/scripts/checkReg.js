$(document).ready(function () {
    //A QUANTO HO CAPITO VA MESSO TUTTO QUELLO CHE RIGUARDA JQUERY IN QUESTO METODO
    $("#regForm").submit(function (e) {
        let pswd =$("#password");
        let cpswd = $("#confirm");
        if(pswd.val().length <8){
            alert("La password deve essere lunga almeno 8 caratteri");
            e.preventDefault(); //QUESTO FA FALLIRE IL SUBMIT, AL SERVER NON ARRIVA LA RICHIESTA POST!
        }
        if (pswd.val() !== cpswd.val()) {
            alert("Le password non coincidono!");
            e.preventDefault();
        }
    })
});