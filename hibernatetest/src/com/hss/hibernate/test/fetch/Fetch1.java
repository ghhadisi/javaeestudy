package com.hss.hibernate.test.fetch;
/**
*1.3.2.1抓取策略的概述
 * 通过一个对象抓取到关联对象需要发送SQL语句，SQL语句如何发送，发送成什么样格式通过策略进行配置。
 * 通过<set>或者<many-to-one>上通过fetch属性进行设置
 * fetch和这些标签上的lazy如何设置优化发送的SQL语句
*
* */

import com.hss.hibernate.test.bean.Customer;
import com.hss.hibernate.test.bean.LinkMan;
import com.hss.hibernate.test.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;

/**
 * 在<set>上的fetch和lazy
 * @author jt
 *
 * fetch：抓取策略，控制SQL语句格式
     * select		：默认值，发送普通的select语句，查询关联对象
     * join			：发送一条迫切左外连接查询关联对象
     * subselect		：发送一条子查询查询其关联对象
 *
 *
 * lazy：延迟加载，控制查询关联对象的时候是否采用延迟
     * true			：默认值，查询关联对象的时候，采用延迟加载
     * false			：查询关联对象的时候，不采用延迟加载
     * extra		：及其懒惰。
 * 在实际开发中，一般都采用默认值。如果有特殊的需求，可能需要配置join。
 */
public class Fetch1 {


    @Test
    /**
     * 默认情况：
     */
    public void demo1(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        // 查询2号客户
        Customer customer = session.get(Customer.class, 2l);// 发送一条查询客户的SQL
        System.out.println(customer.getCust_name());
        // 查看1号客户的每个联系人的信息
        for (LinkMan linkMan : customer.getLinkMans()) {// 发送一条根据客户ID查询联系人的SQL
            System.out.println(linkMan.getLkm_name());
        }
        tx.commit();
    }


    @Test
    /**
     * 设置fetch="select" lazy="true
     */
    public void demo2(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

// 查询2号客户
        Customer customer = session.get(Customer.class, 2l);// 发送一条查询客户的SQL
        System.out.println(customer.getCust_name());
        // 查看1号客户的每个联系人的信息

        System.out.println("################################");

        for (LinkMan linkMan : customer.getLinkMans()) {// 发送一条根据客户ID查询联系人的SQL
            System.out.println(linkMan.getLkm_name());
        }

        tx.commit();
    }

    @Test
    /**
     * fetch="select" lazy="false"
     */
    public void demo3(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

// 查询2号客户
        Customer customer = session.get(Customer.class, 2l);// 发送两条SQL语句：查询客户的名称，查询客户关联联系人
        System.out.println("****************************");

        System.out.println(customer.getCust_name());
        // 查看1号客户的每个联系人的信息
//        for (LinkMan linkMan : customer.getLinkMans()) {
//            System.out.println(linkMan.getLkm_name());
//        }

        System.out.println("################################");
        System.out.println(customer.getLinkMans().size());


        tx.commit();
    }


    @Test
    /**
     * fetch="select" lazy="extra"
     */
    public void demo4(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

// 查询2号客户
        Customer customer = session.get(Customer.class, 2l);// 发送一条查询1号客户的SQL语句
        System.out.println("****************************");
        System.out.println(customer.getCust_name());

        System.out.println("################################");
//        System.out.println(customer.getLinkMans().size());// 发送一条select count() from ...;

        for (LinkMan linkMan : customer.getLinkMans()) {// 发送一条根据客户ID查询联系人的SQL
            System.out.println(linkMan.getLkm_name());
        }
/*
* Hibernate:
    select
        count(lkm_id)
    from
        cst_linkman
    where
        lkm_cust_id =?
10
* */

        tx.commit();
    }


    @Test
    /**
     * 设置fetch="join" lazy=失效
     */
    public void demo5(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

// 查询2号客户
        Customer customer = session.get(Customer.class, 2l);// 发送一条迫切左外连接查询记录
        System.out.println("****************************");
        System.out.println(customer.getCust_name());

        System.out.println("################################");
        System.out.println(customer.getLinkMans().size());//不发送

        tx.commit();
    }


    @Test
    /**
     * 设置fetch="subselect" lazy="true"
     */
    public void demo6(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();


        List<Customer> list = session.createQuery("from Customer").list();// 发送查询所有客户的SQL
        for (Customer customer : list) {
            System.out.println(customer.getCust_name());
            System.out.println("################################");

            System.out.println(customer.getLinkMans().size());// 发送一条子查询
        }

        tx.commit();
    }



    @Test
    /**
     * 设置fetch="subselect" lazy="false"
     */
    public void demo7(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();


        List<Customer> list = session.createQuery("from Customer").list();// 发送查询所有客户的SQL，发送一条子查询
        System.out.println("################################");

        for (Customer customer : list) {
            System.out.println(customer.getCust_name());

            System.out.println(customer.getLinkMans().size());//
        }

        tx.commit();
    }
}
