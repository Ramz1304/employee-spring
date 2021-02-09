package employee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import employee.dao.AbstractDao;
import employee.pojo.EmployeePojo;
import employee.pojo.UserPojo;

@Repository
public class UserDao extends AbstractDao {

    private static String select_all = "select p from UserPojo p";
    private static String select_email = "select p from UserPojo p where email=:email";

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void insert(UserPojo user) {
        em.persist(user);
    }

    public UserPojo delete(int id) {
        UserPojo user = em.find(UserPojo.class, id);
        em.remove(user);
        return user;

    }

    public UserPojo selectId(int id) {

        return em.find(UserPojo.class, id);

    }


    public List<UserPojo> selectAll() {
        TypedQuery<UserPojo> query = getQuery(select_all, UserPojo.class);
        return query.getResultList();
    }

    public void update(UserPojo user) {
    }


    public UserPojo selectEmail(String email) {
        TypedQuery<UserPojo> query = getQuery(select_email, UserPojo.class);
        query.setParameter("email", email);
        return getSingle(query);
    }

}
