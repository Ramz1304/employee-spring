function getUserUrl() {
    var baseUrl = $("meta[name=baseUrl]").attr("content")
    return baseUrl + "/api/admin/user";
}

//BUTTON ACTIONS
function addUser(event) {
    //Set the values to update
    let $form = $("#user-form");
    let data = toJson($form);
    let url = getUserUrl();


    let successFx = function (response) {
        getUserList();
    };
    let errorFx = function () {
        alert("An error has occurred");

    };
    let type = 'POST';
    doAjax(url, type, data, successFx, errorFx);

    return false;
}

function updateUser(event) {
    $('#edit-user-modal').modal('toggle');
    //Get the ID
    var id = $("#user-edit-form input[name=id]").val();
    let url = getUserUrl() + "/" + id;

    //Set the values to update
    var $form = $("#user-edit-form");
    var data = toJson($form);
    let successFx = function (response) {
        console.log("success");
        getUserList();
    };
    let errorFx = function () {
        alert("An error has occurred");

    };
    let type = 'PUT';
    doAjax(url, type, data, successFx, errorFx);

    return false;
}

function getUserList() {
    let $form = $("#user-table");
    let data = toJson($form);
    let url = getUserUrl();
    let successFx = function (data) {
        displayUserList(data);
    };
    let errorFx = function () {
        alert("An error has occurred");

    };
    let type = 'GET';
    doAjax(url, type, data, successFx, errorFx);
}

function deleteUser(id) {
    let url = getUserUrl() + "/" + id;
    let $form = $("#user-form");
    let data = toJson($form);
    let successFx = function (response) {
        console.log("success");
        getUserList();
    };
    let errorFx = function () {
        alert("An error has occurred");

    };
    let type = 'DELETE';
    doAjax(url, type, data, successFx, errorFx);
}

//UI DISPLAY METHODS

function displayUserList(data) {
    console.log('Printing user data');
    var $tbody = $('#user-table').find('tbody');
    $tbody.empty();
    for (var i in data) {
        var e = data[i];
        var buttonHtml = '<button onclick="deleteUser(' + e.id + ')"class="btn"><i class="fa fa-trash" ></i> </button>'
        buttonHtml += ' <button onclick="displayEditUser(' + e.id + ')"class="btn"> <i class="fa fa-pencil" style="color:brown;" aria-hidden="true"></i></button>'
        var row = '<tr>'
            + '<td>' + e.id + '</td>'
            + '<td>' + e.email + '</td>'
            + '<td>' + e.role + '</td>'
            + '<td>' + buttonHtml + '</td>'
            + '</tr>';
        $tbody.append(row);
    }
}

function displayEditUser(id) {
    let url = getUserUrl() + "/" + id;
    let $form = $("edit-user-modal");
    let data = toJson($form);

    let successFx = function (data) {
        console.log("User data fetched");
        console.log(data);
        displayUser(data);
    };
    let errorFx = function () {
        alert("An error has occurred");

    };
    let type = 'GET';
    doAjax(url, type, data, successFx, errorFx);
}

function displayUser(data) {
    $("#user-edit-form input[name=email]").val(data.email);
    $("#user-edit-form input[name=role]").val(data.role);

    $("#user-edit-form input[name=id]").val(data.id);
    $('#edit-user-modal').modal('toggle');
}

function insert() {
    addUser();
    refresh();
}


//INITIALIZATION CODE
function init() {
    $('#add-user').click(insert);
    $('#refresh-data').click(refresh);
    $('#update-user').click(updateUser);
}

$(document).ready(init);
$(document).ready(getUserList);

