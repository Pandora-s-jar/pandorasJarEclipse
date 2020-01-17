$(document).ready(function () {
    let urlString = window.location;
    let url = new URL(urlString);
    let c = url.searchParams.get("emailAlreadyExists");
    if(c === "true"){
        alert("EMAIL GIA' ESISTENTE");
        //TODO : Hai dimenticato la password?
    }
});
