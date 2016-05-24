


function createTableRow(jsons) {
    var tbody = document.getElementById("tbody");
    if(!tbody) {
        return;
    }
    jsons.forEach(function(json, i, array){
        var tr = document.createElement("tr");
        tr.innerHTML += createTableData(i+1);
        tr.innerHTML += createTableData(json.title);
        tr.innerHTML += createTableData(json.tag);
        tr.innerHTML += createTableData(json.priority);
        tr.innerHTML += createTableData(json.limitDate);
        tr.innerHTML += createTableData(json.content);
        tr.innerHTML += createTableData(json.status);
        tr.innerHTML += createButton();
        tbody.appendChild(tr);
    });
}

function createTableData(value) {
    return ''<td>'+ value +'</td>'
}

function createButton() {
    document.createElement("td");
    td.innerHTML = '<a href=¥"${s.url(Controllers.todo.showUrl, ¥"id¥" -> item.id)}¥" class=¥"btn btn-default¥">${s.i18n.getOrKey(¥"detail¥")}</a>'
        + '<a href=¥"${s.url(Controllers.todo.editUrl, ¥"id¥" -> item.id)}¥" class=¥"btn btn-info¥">${s.i18n.getOrKey(¥"edit¥")}</a>'
        + '<a data-method="post" data-confirm="${s.i18n.getOrKey("todo.delete.confirm")}" "
        +'href=¥"${s.url(Controllers.todo.destroyUrl, ¥"id¥" -> item.id)}¥" rel=¥"nofollow¥" class=¥"btn btn-danger¥">${s.i18n.getOrKey(¥"delete¥")}</a>';
    return td;
}