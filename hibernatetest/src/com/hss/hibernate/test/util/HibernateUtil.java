package com.hss.hibernate.test.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
   private static final Configuration conf;
    private static final SessionFactory sessionFactory;


    static {
        conf = new Configuration().configure();
        sessionFactory = conf.buildSessionFactory();
    }

    //直接
    public static Session openSession(){
       return sessionFactory.openSession();
    }

    public static Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

}
