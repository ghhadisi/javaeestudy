package com.hss.hibernate.test.query;

import com.hss.hibernate.test.bean.Customer;
import com.hss.hibernate.test.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class QIDQuery {

    public static void main(String[] args) {
        loadMethod();
    }

    public static void getMethod(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class,1l);

        System.out.println("++++++++++++++++++++++++++");

        customer.getLinkMans().size();
        transaction.commit();
        System.out.println(customer);


    }

    public static void loadMethod(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.load(Customer.class,1l);

        System.out.println("++++++++++++++++++++++++++");
        customer.getCust_name();
        System.out.println("++++++++++++++++++++++++++");

        customer.getLinkMans().size();
        transaction.commit();
        System.out.println(customer);
    }
}
