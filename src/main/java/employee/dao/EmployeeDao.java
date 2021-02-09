package employee.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import employee.pojo.EmployeePojo;


@Repository
public class EmployeeDao extends AbstractDao {


    private static String select_all = "select p from EmployeePojo p";

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void insert(EmployeePojo employee) {
        em.persist(employee);
    }

    public EmployeePojo delete(int id) {
        EmployeePojo employee = em.find(EmployeePojo.class, id);
        em.remove(employee);
        return employee;
    }


    public EmployeePojo select(int id) {

        return em.find(EmployeePojo.class, id);

    }

    public List<EmployeePojo> selectAll() {
        TypedQuery<EmployeePojo> query = getQuery(select_all, EmployeePojo.class);
        return query.getResultList();
    }


    public void update(EmployeePojo employee) {
    }


}
