package employee.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import employee.service.ApiException;
import employee.model.UserForm;
import employee.model.EmployeeData;
import employee.model.UserData;

import employee.dao.UserDao;
import employee.model.UserData;
import employee.model.UserForm;
import employee.model.UserForm;
import employee.pojo.UserPojo;
import employee.pojo.UserPojo;
import employee.pojo.UserPojo;
import employee.pojo.UserPojo;

@Service
public class UserService {

    @Autowired
    private UserDao dao;

    @Transactional(rollbackOn = ApiException.class)
    public UserPojo addUser(UserForm form) {
        UserPojo user = convert(form);
        normalize(user);
        dao.insert(user);
        return user;
    }

    @Transactional(rollbackOn = ApiException.class)
    public UserPojo get(int id) throws ApiException {
        return dao.selectId(id);
    }

    @Transactional(rollbackOn = ApiException.class)
    public UserPojo get(String email) throws ApiException {
        return dao.selectEmail(email);
    }


    @Transactional
    public List<UserData> getAll() {
        List<UserPojo> list = dao.selectAll();
        List<UserData> list2 = new ArrayList<UserData>();
        for (UserPojo user : list) {
            list2.add(convert(user));
        }
        return list2;
    }

    @Transactional(rollbackOn = ApiException.class)
    public UserPojo update(int id, UserForm form) throws ApiException {

        UserPojo user = convert(form);
        normalize(user);
        UserPojo user2 = getCheck(id);
        user2.setEmail(user.getEmail());
        user2.setRole(user.getRole());
        dao.update(user);
        return user;
    }

    @Transactional
    public UserPojo getCheck(int id) throws ApiException {
        UserPojo user = get(id);
        if (user == null) {
            throw new ApiException("User with given ID does not exit, id: " + id);
        }
        return user;
    }

    @Transactional
    public UserPojo delete(int id) {
        return dao.delete(id);
    }

    protected static void normalize(UserPojo user) {
        user.setEmail(user.getEmail().toLowerCase().trim());
        user.setRole(user.getRole().toLowerCase().trim());
    }

    private static UserData convert(UserPojo user) {
        UserData data = new UserData();
        data.setEmail(user.getEmail());
        data.setRole(user.getRole());
        data.setId(user.getId());
        return data;
    }

    private static UserPojo convert(UserForm form) {
        UserPojo user = new UserPojo();
        user.setEmail(form.getEmail());
        user.setRole(form.getRole());
        user.setPassword(form.getPassword());
        return user;
    }


}
