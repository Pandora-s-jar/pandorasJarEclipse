$(document).ready(()=>{
    if(Cookies.get("logged") === "false" || Cookies.get("logged") === undefined){
        $("#addGameLink").attr("href", "#Login");
        $("#addGameLink").attr("data-toggle", "modal");
        $("#libraryLink").attr("href", "#Login");
        $("#libraryLink").attr("data-toggle", "modal");
    }
});