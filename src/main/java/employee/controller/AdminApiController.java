package employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import employee.model.UserData;
import employee.model.EmployeeForm;
import employee.model.UserData;
import employee.model.UserForm;
import employee.pojo.EmployeePojo;
import employee.pojo.UserPojo;
import employee.service.ApiException;
import employee.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
public class AdminApiController {

    @Autowired
    private UserService service;

    @ApiOperation(value = "Adds an employee")
    @RequestMapping(path = "/api/admin/user", method = RequestMethod.POST)
    public UserPojo add(@RequestBody UserForm form) {

        return service.addUser(form);

    }

    @ApiOperation(value = "Deletes a user")
    @RequestMapping(path = "/api/admin/user/{id}", method = RequestMethod.DELETE)
    public UserPojo delete(@PathVariable int id) {
        return service.delete(id);
    }


    @ApiOperation(value = "Gets list of all users")
    @RequestMapping(path = "/api/admin/user", method = RequestMethod.GET)
    public List<UserData> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Updates an user")
    @RequestMapping(path = "/api/admin/user/{id}", method = RequestMethod.PUT)
    public UserPojo update(@PathVariable int id, @RequestBody UserForm form) throws ApiException {
        return service.update(id, form);
    }

    @ApiOperation(value = "Gets an user by ID")
    @RequestMapping(path = "/api/admin/user/{id}", method = RequestMethod.GET)
    public UserPojo get(@PathVariable int id) throws ApiException {
        return service.getCheck(id);

    }


}

