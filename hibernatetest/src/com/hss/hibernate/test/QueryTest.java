package com.hss.hibernate.test;

import com.hss.hibernate.test.bean.User;
import com.hss.hibernate.test.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class QueryTest {

    public static void main(String[] args) {
        criteria2();
    }

    public static void hql(){

      Session session = HibernateUtil.openSession();
//        Transaction tx = session.beginTransaction();
      org.hibernate.Query query = session.createQuery("from User");
        List<User> list = query.list();
        for (User customer : list) {
            System.out.println(customer);
        }
        session.close();
    }

    public static void hql1(){

        Session session = HibernateUtil.openSession();
//        Transaction tx = session.beginTransaction();
        org.hibernate.Query query = session.createQuery("from User where username=?");
        query.setString(0,"aaab");
        List<User> list = query.list();
        for (User customer : list) {
            System.out.println(customer);
        }
        session.close();
    }

    public static void hql2(){

        Session session = HibernateUtil.openSession();
//        Transaction tx = session.beginTransaction();
        org.hibernate.Query query = session.createQuery("from User where username=:aaa");
        query.setString("aaa","aaa");
//        query.uniqueResult();//接收唯一的查询结果
//        query.setParameter(0,"aaa");
        List<User> list = query.list();
        for (User customer : list) {
            System.out.println(customer);
        }
        session.close();
    }

    public static void hql3(){

        Session session = HibernateUtil.openSession();
//        Transaction tx = session.beginTransaction();
        org.hibernate.Query query = session.createQuery("from User");
        query.setFirstResult(0);
        query.setMaxResults(2);
        List<User> list = query.list();
        for (User customer : list) {
            System.out.println(customer);
        }
        session.close();
    }

    public static void criteria(){
        Session session = HibernateUtil.openSession();
        Criteria criteria = session.createCriteria(User.class);
        List<User> list = criteria.list();
        for (User customer : list) {
            System.out.println(customer);
        }
        session.close();
    }

    public static void criteria1(){
        Session session = HibernateUtil.openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("username","aaa"));
//        criteria.add(Restrictions.eq("age",38));

        List<User> list = criteria.list();
        for (User customer : list) {
            System.out.println(customer);
        }
        session.close();
    }

    public static void criteria2(){
        Session session = HibernateUtil.openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.setFirstResult(0);
        criteria.setMaxResults(2);
        List<User> list = criteria.list();
        for (User customer : list) {
            System.out.println(customer);
        }
        session.close();
    }
}
