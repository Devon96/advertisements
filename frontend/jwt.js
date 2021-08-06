let _token = null;

function getUrlParam(str) {
    let url_str = window.location.search.substring(1),
        values = url_str.split('&'),
        paramName;

    for (i = 0; i < values.length; i++) {
        paramName = values[i].split('=');

        if (paramName[0] === str) {
            return typeof paramName[1] === undefined ? true : decodeURIComponent(paramName[1]);
        }
    }
    return false;
}