linkSize = 1;
tagSize = 1;

function addRow(event) {
    let actualRow = $(event.target.parentNode.parentNode);
    let newRow = actualRow.clone();
    if (event.target.id === "btn-add-link") {
        ++linkSize;
        let newInputText = newRow[0].children[0].children[0];
        newInputText.value = "";
        let oldInputText = actualRow[0].children[0].children[0];
        let actualName = $(oldInputText).attr("name");
        let id = (Number(actualName.split("-")[1]) + 1).toString();
        $(newInputText).attr("name", "link-".concat(id));
        newRow.insertAfter(actualRow);
        reformatName(newRow, true);
    } else if (event.target.id === "btn-add-tag") {
        ++tagSize;
        let newInputTag = newRow[0].children[0].children[0];
        let oldInputTag = actualRow[0].children[0].children[0];
        let actualName = $(oldInputTag).attr("name");
        let id = (Number(actualName.split("-")[1])+1).toString();
        $(newInputTag).attr("name","tag-".concat(id));
        newRow.insertAfter(actualRow);
        reformatName(newRow, true);
    }
}

function removeRow(event) {
    if ((event.target.id === "btn-remove-link" && linkSize > 1) || (event.target.id === "btn-remove-tag" && tagSize > 1)) {
        let actualRow = $(event.target.parentNode.parentNode);
        if (event.target.id === "btn-remove-link") {
            reformatName(actualRow, false);
            --linkSize;
        } else if (event.target.id === "btn-remove-tag") {
            reformatName(actualRow, false);
            --tagSize;
        }
        console.log(actualRow[0].children[0].children[0]);
        actualRow.remove();
    }
}

function reformatName(actualRow, up) {
    let nextRow = actualRow[0].nextSibling;
    if(nextRow != null){
        while(nextRow.nextSibling !== null){
            let inputText = nextRow.children[0].children[0];
            let name = $(inputText).attr("name").split("-")[0];
            name.concat("-");
            let id = Number($(inputText).attr("name").split("-")[1]);
            console.log(id);
            if(up){
                ++id;
            }
            else{
                --id;
            }
            $(inputText).attr("name", name.concat(String(id)));
            nextRow = nextRow.nextSibling;
        }
    }
}