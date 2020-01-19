$(document).ready(function () {
    let urlString = window.location;
    let url = new URL(urlString);
    let c = url.searchParams.get("captcha");
    if(c === "false"){
        alert("CONTROLLO ANTI-SPAM NON RIUSCITO");
    }
});
