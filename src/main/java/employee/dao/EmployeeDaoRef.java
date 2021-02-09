package employee.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import employee.pojo.EmployeePojo;

@Repository
public class EmployeeDaoRef {

    private HashMap<Integer, EmployeePojo> rows;
    private int lastid;


    @PostConstruct
    public void init() {
        rows = new HashMap<Integer, EmployeePojo>();
    }

    public void insert(EmployeePojo p) {
        lastid++;
        p.setId(lastid);
        rows.put(lastid, p);
    }

    public void delete(int id) {
        rows.remove(id);
    }

    public EmployeePojo select(int id) {
        return rows.get(id);
    }

    public List<EmployeePojo> selectAll() {
        ArrayList<EmployeePojo> list = new ArrayList<EmployeePojo>();
        list.addAll(rows.values());
        return list;
    }

    public void update(int id, EmployeePojo p) {
        rows.put(id, p);
    }


}
