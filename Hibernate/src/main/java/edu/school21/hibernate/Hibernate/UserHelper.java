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
    public static List<User> getUserList(Boolean dependencies) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));

        Query query = session.createQuery(criteriaQuery);

        List<User> users = query.getResultList();
        if(dependencies) {
            for (User user : users) {
                Hibernate.initialize(user.getRoomsSocial());
                Hibernate.initialize(user.getMessages());
                Hibernate.initialize(user.getRoomCreated());
            }
        } else {
            for (User user : users) {
                user.setMessages(null);
                user.setRoomsSocial(null);
                user.setRoomCreated(null);
            }
        }
        session.close();
        return users;
    }
    public static User getUserById(Long id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        User user = session.get(User.class, id);
        session.getTransaction().commit();
        session.close();
        return user;
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
    public static void deleteUser(User user) throws UserParametersException {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        if(!checkUser(user) && user.getId() == null) {
            throw new UserParametersException("Not all arguments has set in User object");
        }
        session.delete(user);

        session.getTransaction().commit();
        session.close();
    }
    public static void updateUserPassword(Long id, String password) throws UserParametersException {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        User userTmp = session.load(User.class, id);
        if(userTmp != null) {
            Query query = session.createQuery("UPDATE User SET password = :password WHERE id = :id");
            query.setParameter("password", password);
            query.setParameter("id", id);
            query.executeUpdate();
        }
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
