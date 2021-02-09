package employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import employee.model.EmployeeData;
import employee.model.EmployeeForm;
import employee.pojo.EmployeePojo;
import employee.service.ApiException;
import employee.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
public class EmployeeApiController {

    @Autowired
    private EmployeeService service;

    @ApiOperation(value = "Adds an employee")
    @RequestMapping(path = "/api", method = RequestMethod.POST)
    public EmployeePojo add(@RequestBody EmployeeForm form) {

        return service.addEmployee(form);

    }

    @ApiOperation(value = "Deletes and employee")
    @RequestMapping(path = "/api/{id}", method = RequestMethod.DELETE)
    // /api/1
    public EmployeePojo delete(@PathVariable int id) {
        return service.delete(id);
    }

    @ApiOperation(value = "Gets an employee by ID")
    @RequestMapping(path = "/api/{id}", method = RequestMethod.GET)
    public EmployeePojo get(@PathVariable int id) throws ApiException {
        return service.getCheck(id);

    }

    @ApiOperation(value = "Gets list of all employees")
    @RequestMapping(path = "/api", method = RequestMethod.GET)
    public List<EmployeeData> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Updates an employee")
    @RequestMapping(path = "/api/{id}", method = RequestMethod.PUT)
    public EmployeePojo update(@PathVariable int id, @RequestBody EmployeeForm form) throws ApiException {
        return service.update(id, form);
    }


}


