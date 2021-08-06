const url = SERVER_URL + '/api/advertisement/list';

$( document ).ready(function() {

    loadItemsAdvertisement();

});
let token;
function loadItemsAdvertisement() {

    $('#advertisements').empty();

    token = getUrlParam("jwt");

    $("a#add").attr("href", "./add-advertisement.html?jwt=" + token)

    let url = SERVER_URL + '/api/advertisement/list';

    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json",
        dataType: "json",
        headers: {'Authorization':token}
    }).done(function (data) {
        for(item of data){
            addChild(item);
        }
    }).fail(function (data) {

        window.location.replace( "./login.html" );

    });

}

function getCookie(name)
{
    var re = new RegExp(name + "=([^;]+)");
    var value = re.exec(document.cookie);
    return (value != null) ? unescape(value[1]) : null;
}

let init;
function addChild(item) {

    init = true;

    let p_element = document.createElement("p");
    p_element.classList.add('card-text');
    p_element.innerText = item.description;

    let a_edit = document.createElement("a");
    a_edit.classList.add('card-link');
    a_edit.innerText = 'Edit';
    a_edit.href = '#';
    $(a_edit).click(function () {
        editAdvertisement(item.id);
    });

    let a_delete = document.createElement("a");
    a_delete.classList.add('card-link', 'delete');
    a_delete.innerText = 'Delete';
    a_delete.href = '#';
    a_delete.onclick = function () {
        deleteAdvertisement(item.id);
    };

    let h5_element = document.createElement("h5");
    h5_element.classList.add('card-title');
    h5_element.innerText = item.title;

    let div_inner = document.createElement("div");
    div_inner.classList.add('card-body');
    div_inner.append(h5_element);
    div_inner.append(p_element);
    div_inner.append(a_edit);
    div_inner.append(a_delete);

    let div_advertisement = document.createElement("div");
    div_advertisement.classList.add('card', 'advertisement');
    div_advertisement.id = 'advertisement-' + item.id;
    div_advertisement.append(div_inner);


    $('#advertisements').append(div_advertisement);

    init = false
}

function deleteAdvertisement(id){
    const url = SERVER_URL + '/api/advertisement/delete/' + id;

    $.ajax({
        type: "GET",
        url: url,
        contentType: "application/json",
        dataType: "json",
        headers: {'Authorization': token}
    }).always(function () {
        var q = '#advertisement-' + id;
        $(q).remove();
    })


}

function editAdvertisement(id) {
    //window.location.replace( "./add-advertisement.html?id=" + id + "&jwt=" + token );
    window.location.href = "./add-advertisement.html?id=" + id + "&jwt=" + token;
}