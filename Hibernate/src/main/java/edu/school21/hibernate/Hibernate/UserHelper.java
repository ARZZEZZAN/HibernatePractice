package edu.school21.hibernate.Hibernate;

import edu.school21.hibernate.Entity.User;
import edu.school21.hibernate.Exceptions.UserParametersException;
import edu.school21.hibernate.Hibernate.Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserHelper {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private UserHelper() {
    }
    public static List<User> getUserList() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);

        Query query = session.createQuery(criteriaQuery);
        List<User> users = query.getResultList();
        for(User user : users) {
            Hibernate.initialize(user.getRoomsSocial());
            Hibernate.initialize(user.getMessages());
            Hibernate.initialize(user.getRoomCreated());
        }
        session.close();
        return users;
    }
    public static void saveUser(User user) throws UserParametersException {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        if(!checkUser(user)) {
            throw new UserParametersException("Not all arguments has set in User object");
        }
        session.persist(user);

        session.getTransaction().commit();
        session.close();
    }
    private static boolean checkUser(User user) {
        if(user.getLogin() != null && user.getPassword() != null) {
            return true;
        }
        return false;
    }
}
