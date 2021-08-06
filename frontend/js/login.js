$(document).ready(function () {
    $('#alert').hide();
})

$('form').submit(e => {

    let email = $("input[name=email]").val();
    let pwd = $("input[name=password]").val();

    $.ajax({
        type: "POST",
        url: SERVER_URL + "/api/authenticate",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({
            email: email,
            password : pwd
        })
    }).always(function (data) {
        if(data.status === 200){
            //document.cookie = "jwtToken= Bearer " + data.responseText;
            _token =  "Bearer " + data.responseText;
            //window.location.replace( "./advertisements.html?jwt=" +  _token);
            window.location.href = "./advertisements.html?jwt=" +  _token;
        }
        $('#alert').show();

    });

    return false;
});