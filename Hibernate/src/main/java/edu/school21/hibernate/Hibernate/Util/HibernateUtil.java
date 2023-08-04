package edu.school21.hibernate.Hibernate.Util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.spi.SessionFactoryImplementor;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        final StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
        }

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
//    public static BatchingEntityLoaderBuilder getBuilder(SessionFactoryImplementor factory) {
//        switch(factory.getSessionFactoryOptions().getBatchFetchStyle()) {
//            case PADDED:
//                return PaddedBatchingEntityLoaderBuilder.INSTANCE;
//            case DYNAMIC:
//                return DynamicBatchingEntityLoaderBuilder.INSTANCE;
//            default:
//                return LegacyBatchingEntityLoaderBuilder.INSTANCE;
//        }
//    }

}