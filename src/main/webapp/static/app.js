//HELPER METHOD
function toJson($form) {
    var serialized = $form.serializeArray();
    console.log(serialized);
    var s = '';
    var data = {};
    for (s in serialized) {
        data[serialized[s]['name']] = serialized[s]['value']
    }
    var json = JSON.stringify(data);
    return json;
}

function doAjax(url, type, data, successFx, errorFx) {
    let ajaxOptions = {
        url: url,
        type: type,
        data: data,

        headers: {

            'content-Type': 'application/json',
        },
        success: function (responseData) {

            if (successFx) {
                successFx.call(successFx, responseData);
            }
        },
        error: function (errData) {

            if (errorFx) {
                errorFx.call(errorFx, errData);
            }

        }
    };

    $.ajax(ajaxOptions);
}


function refresh() {
    var elements = document.getElementsByTagName("input");
    for (var ii = 0; ii < elements.length; ii++) {
        if (elements[ii].type == "text") {
            elements[ii].value = "";
        }
    }
}

function password() {
    var x = document.getElementById("password");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}

function init() {
    $('#showpassword').click(password);
}

$(document).ready(init);
