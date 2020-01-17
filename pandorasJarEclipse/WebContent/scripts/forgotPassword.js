$(document).ready(function () {
    let urlString = window.location;
    let url = new URL(urlString);
    let c = url.searchParams.get("emailNotExists");
    if(c === "true"){
        alert("L'EMAIL INSERITA NON ESISTE");
    }
});