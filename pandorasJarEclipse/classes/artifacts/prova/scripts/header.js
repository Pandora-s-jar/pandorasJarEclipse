$(document).ready(()=>{
    console.log(Cookies.get("logged"));
    if(Cookies.get("logged") === "false" || Cookies.get("logged") === undefined){
        $("#addGameLink").attr("href", "#Login");
        $("#addGameLink").attr("data-toggle", "modal");
    }
});