package edu.school21.hibernate.Hibernate;

import edu.school21.hibernate.Entity.Message;
import edu.school21.hibernate.Hibernate.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
