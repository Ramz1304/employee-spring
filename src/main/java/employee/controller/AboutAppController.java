package employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import employee.model.AboutApp;
import employee.model.EmployeeData;
import employee.model.EmployeeForm;
import employee.pojo.EmployeePojo;
import employee.service.AboutAppService;
import employee.service.ApiException;
import employee.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController

public class AboutAppController {

    @Autowired
    private AboutAppService service;

    @ApiOperation(value = "gives application name version")
    @RequestMapping(path = "/api/about", method = RequestMethod.GET)
    public AboutApp getDetails() {
        AboutApp app = new AboutApp();
        app.setName(service.getName());
        app.setVersion(service.getVersion());
        return app;
    }


}


