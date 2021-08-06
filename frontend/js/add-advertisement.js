let advertisementId;
let token;

$( document ).ready(function() {
    token = getUrlParam("jwt"); //getCookie('jwtToken');
    if(token == null){
        window.location.replace("./login.html");
    }

    $("a#cancel").attr("href", "./advertisements.html?jwt=" + token)

    advertisementId = getUrlParam('id');
    if( advertisementId !== false ){

        let url = SERVER_URL + '/api/advertisement/get/' + advertisementId;

        $.ajax({
            type: "GET",
            url: url,
            contentType: "application/json",
            dataType: "json",
            headers: {'Authorization': token}
        }).done(function (data) {
            $("input[name=title]").val(data.title);
            $("textarea[name=description]").val(data.description);
            advertisementId = data.id;
        })

        setActionToEdit();
    }else{
        setActionToSave();
    }

});

function setActionToEdit(){

    $('#myBtn').text('Edit');

    $('form').submit(e => {
        const url = SERVER_URL + '/api/advertisement/edit?title=' + $("input[name=title]").val() + '&description=' + $("textarea[name=description]").val() + '&id=' + advertisementId;


        $.ajax({
            type: "POST",
            url: url,
            contentType: "application/json",
            dataType: "json",
            headers: {'Authorization': token}
        }).done(function () {
            //window.location.replace("./advertisements.html?jwt=" + token);
            window.location.href = "./advertisements.html?jwt=" + token;
        }).fail(function () {
            alert("Edit failed");
        });

        return false;
    });
}

function setActionToSave() {
    $('form').submit(e => {

        const url = SERVER_URL + '/api/advertisement/add?title=' + $("input[name=title]").val() + '&description=' + $("textarea[name=description]").val();

        $.ajax({
            type: "POST",
            url: url,
            contentType: "application/json",
            dataType: "json",
            headers: {'Authorization': token}
        }).done(function () {
            //window.location.replace("./advertisements.html?jwt=" + token);
            window.location.href = "./advertisements.html?jwt=" + token
        }).fail(function () {
            alert("Save failed");
        });

        return false;
    });
}
