$(document).ready(function () {
    $("#btn-add-link").click(function () {
        let row = $("#row-link");
        let secondRow = row.clone();
        //FIXME : rimuovere il bottone
        secondRow.insertAfter(row);
    })
})