package employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import employee.model.InfoData;
import employee.model.UserData;
import employee.model.UserForm;
import employee.pojo.UserPojo;
import employee.service.ApiException;
import employee.service.UserService;

import io.swagger.annotations.ApiOperation;

@Controller
public class InitApiController extends AbstractUiController {

    @Autowired
    private UserService service;
    @Autowired
    private InfoData info;

    @ApiOperation(value = "Initializes application")
    @RequestMapping(path = "/site/init", method = RequestMethod.GET)
    public ModelAndView showPage(UserForm form) throws ApiException {
        info.setMessage("");
        return mav("init.html");
    }

    @ApiOperation(value = "Initializes application")
    @RequestMapping(path = "/site/init", method = RequestMethod.POST)
    public ModelAndView initSite(UserForm form) throws ApiException {
        List<UserData> list = service.getAll();
        if (list.size() > 0) {
            info.setMessage("Application already initialized. Please use existing credentials");
        } else {
            form.setRole("admin");
            service.addUser(form);
            info.setMessage("Application initialized");
        }
        return mav("init.html");

    }

}
