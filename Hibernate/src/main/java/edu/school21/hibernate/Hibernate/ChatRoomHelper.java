package edu.school21.hibernate.Hibernate;

import edu.school21.hibernate.Entity.ChatRoom;
import edu.school21.hibernate.Hibernate.Util.HibernateUtil;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;


public class ChatRoomHelper {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private ChatRoomHelper() {
    }
    public static List<ChatRoom> getRoomList() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ChatRoom> criteriaQuery = criteriaBuilder.createQuery(ChatRoom.class);
        Root<ChatRoom> root = criteriaQuery.from(ChatRoom.class);
        criteriaQuery.select(root);

        Query query = session.createQuery(criteriaQuery);
        List<ChatRoom> rooms = query.getResultList();
        session.close();
        return rooms;
    }
}
