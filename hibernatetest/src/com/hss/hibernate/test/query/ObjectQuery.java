package com.hss.hibernate.test.query;

import com.hss.hibernate.test.bean.Customer;
import com.hss.hibernate.test.bean.LinkMan;
import com.hss.hibernate.test.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ObjectQuery {
    public static void main(String[] args) {
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        LinkMan linkMan = session.load(LinkMan.class,1l);
        System.out.println("++++++++++++++++++++++++++");
        linkMan.getLkm_name();

        System.out.println("++++++++++++++++++++++++++");

        linkMan.getCustomer().getCust_name();
        transaction.commit();
    }
}
