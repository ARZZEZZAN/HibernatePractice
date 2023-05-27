package edu.school21.hibernate.Hibernate;


import edu.school21.hibernate.Entity.Message;
import edu.school21.hibernate.Hibernate.Util.HibernateUtil;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;


public class MessageHelper {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private MessageHelper() {
    }
    public static List<Message> getMessageList() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<Message> root = criteriaQuery.from(Message.class);
        criteriaQuery.select(root);

        Query query = session.createQuery(criteriaQuery);
        List<Message> messages = query.getResultList();
        session.close();
        return messages;
    }
}
