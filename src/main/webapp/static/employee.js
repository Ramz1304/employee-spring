function getEmployeeUrl() {
    var baseUrl = $("meta[name=baseUrl]").attr("content")
    return baseUrl + "/api";
}


//BUTTON ACTIONS
function addEmployee(event) {
    //Set the values to update
    let $form = $("#employee-form");
    let data = toJson($form);
    let url = getEmployeeUrl();
    let successFx = function (response) {
        console.log("success");
        getEmployeeList();
    };
    let errorFx = function () {
        alert("An error has occurred");

    };
    let type = 'POST';
    doAjax(url, type, data, successFx, errorFx);

    return false;
}

function updateEmployee(event) {
    $('#edit-employee-modal').modal('toggle');
    //Get the ID
    var id = $("#employee-edit-form input[name=id]").val();
    let url = getEmployeeUrl() + "/" + id;

    //Set the values to update
    var $form = $("#employee-edit-form");
    var data = toJson($form);
    let successFx = function (response) {
        console.log("success");
        getEmployeeList();
    };
    let errorFx = function () {
        alert("An error has occurred");

    };
    let type = 'PUT';
    doAjax(url, type, data, successFx, errorFx);

    return false;
}

function deleteEmployee(id) {
    var url = getEmployeeUrl() + "/" + id;
    var $form = $("#employee-form");
    var data = toJson($form);
    let successFx = function (response) {
        console.log("success");
        getEmployeeList();
    };
    let errorFx = function () {
        alert("An error has occurred");

    };
    let type = 'DELETE';
    doAjax(url, type, data, successFx, errorFx);
}

function getEmployeeList() {
    let $form = $("#employee-table");
    let data = toJson($form);
    let url = getEmployeeUrl();
    let successFx = function (data) {
        console.log("Employee data fetched");
        console.log(data);
        displayEmployeeList(data);
    };
    let errorFx = function () {
        alert("An error has occurred");

    };
    let type = 'GET';
    doAjax(url, type, data, successFx, errorFx);
}


//UI DISPLAY METHODS

function displayEmployeeList(data) {
    console.log('Printing employee data');
    var $tbody = $('#employee-table').find('tbody');
    $tbody.empty();
    for (var i in data) {
        var e = data[i];
        var buttonHtml = '<button onclick="deleteEmployee(' + e.id + ')"class="btn"><i class="fa fa-trash"></i> </button>'
        buttonHtml += ' <button onclick="displayEditEmployee(' + e.id + ')"class="btn"> <i class="fa fa-pencil" style="color:brown;" aria-hidden="true"></i></button>'
        var row = '<tr>'
            + '<td>' + e.id + '</td>'
            + '<td>' + e.name + '</td>'
            + '<td>' + e.age + '</td>'
            + '<td>' + buttonHtml + '</td>'
            + '</tr>';
        $tbody.append(row);
    }
}

function displayEditEmployee(id) {
    let url = getEmployeeUrl() + "/" + id;
    let $form = $("edit-employee-modal");
    let data = toJson($form);

    let successFx = function (data) {
        console.log("Employee data fetched");
        console.log(data);
        displayEmployee(data);
    };
    let errorFx = function () {
        alert("An error has occurred");

    };
    let type = 'GET';
    doAjax(url, type, data, successFx, errorFx);
}


function displayEmployee(data) {
    $("#employee-edit-form input[name=name]").val(data.name);
    $("#employee-edit-form input[name=age]").val(data.age);
    $("#employee-edit-form input[name=id]").val(data.id);
    $('#edit-employee-modal').modal('toggle');
}

function insert() {
    addEmployee();
    refresh();
}


//INITIALIZATION CODE
function init() {
    $('#add-employee').click(insert);
    $('#update-employee').click(updateEmployee);
    $('#refresh-data').click(refresh);
}

$(document).ready(init);
$(document).ready(getEmployeeList);

	