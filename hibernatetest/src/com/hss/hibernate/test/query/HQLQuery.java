package com.hss.hibernate.test.query;

import com.hss.hibernate.test.bean.Customer;
import com.hss.hibernate.test.bean.LinkMan;
import com.hss.hibernate.test.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class HQLQuery {


    @Test
    public   void demo1(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.beginTransaction();


        // 创建一个客户
        Customer customer = new Customer();
        customer.setCust_name("李杰");

        for (int i = 1; i <= 10; i++) {
            LinkMan linkMan = new LinkMan();
            linkMan.setLkm_name("何军" + i);
            linkMan.setCustomer(customer);

            customer.getLinkMans().add(linkMan);

            session.save(linkMan);
        }
        session.save(customer);

        transaction.commit();
    }

    @Test
    public   void demo2(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        // 简单的查询
        Query query = session.createQuery("from Customer");
        List<Customer> list = query.list();
        // sql中支持*号的写法：select * from cst_customer; 但是在HQL中不支持*号的写法。
        /*
         * Query query = session.createQuery("select * from Customer");// 报错
         * List<Customer> list = query.list();
         */
        for (Customer customer : list) {
            System.out.println(customer);
        }
        transaction.commit();
    }


    @Test
    public   void demo3(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        // 简单的查询
//        Query query = session.createQuery("from Customer c");
        Query query = session.createQuery("select c from Customer c");

        List<Customer> list = query.list();
        for (Customer customer : list) {
            System.out.println(customer);
        }
        transaction.commit();
    }

    /**
     * 排序查询
     */
    @Test
    public   void demo4() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();
        // 排序的查询
        // 默认情况 升序
        // 设置降序排序 升序使用asc 降序使用desc

        Query query = session.createQuery("from Customer order by cust_id desc");

        // 设置降序排序 升序使用asc 降序使用desc
        List<Customer> list = query.list();

        for (Customer customer : list) {
            System.out.println(customer);
        }
        tx.commit();
    }

    @Test
    public   void demo5() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();
        // 条件的查询
        // 一、按位置绑定：根据参数的位置进行绑定。
        // 一个条件
//        Query query = session.createQuery("from Customer where cust_name=?");
//        query.setParameter(0, "王东");


        // 多个条件
//        Query query = session.createQuery("from Customer where cust_source = ? and cust_name like ?");
//        query.setParameter(0,"1");
//        query.setParameter(1,"李%");


        // 二、按名称绑定
        Query query = session.createQuery("from Customer where cust_source = :aaa and cust_name like :bbb");
        query.setParameter("aaa","1");
        query.setParameter("bbb","李%");

        List<Customer> list = query.list();

        for (Customer customer : list) {
            System.out.println(customer);
        }
        tx.commit();
    }


    /**
     * 投影查询
     */
    @Test
    public  void demo6() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        // 投影查询
        // 单个属性
//        Query query = session.createQuery("select c.cust_name from Customer c");
//        List<Object> list = query.list();
//        for (Object object : list) {
//            System.out.println(object);
//        }


        // 多个属性:
//        Query query = session.createQuery("select c.cust_name,c.cust_source from Customer c");
//        List<Object[]> list = query.list();
//        for (Object[] objects : list) {
//            System.out.println(Arrays.toString(objects));
//        }

        // 查询多个属性，但是我想封装到对象中。
//        Query query = session.createQuery("select new Customer(cust_name,cust_source) from Customer");
        Query query = session.createQuery("select new Customer(c.cust_name,c.cust_source) from Customer c");

        List<Customer> list = query.list();
        for (Customer customer : list) {
            System.out.println(customer);
        }
        tx.commit();

    }


    @Test
    /**
     * 分页查询
     */
    public void demo7() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        // 分页查询
        Query query = session.createQuery("from LinkMan");
        query.setFirstResult(5);
        query.setMaxResults(5);
        List<LinkMan> list = query.list();

        for (LinkMan linkMan : list) {
            System.out.println(linkMan);
        }
        tx.commit();
    }
    @Test
    /**
     * 分组统计查询
     */
    public void demo8() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        // 聚合函数的使用：count(),max(),min(),avg(),sum()
//         Object object = session.createQuery("select count(*) from LinkMan").uniqueResult();
//        System.out.println(object);

        Query query = session.createQuery("select cust_source,  count(*) from Customer group by cust_source");
        List<Object[]> list = query.list();

        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }

        tx.commit();
    }


    @Test
    /**
     * HQL的多表查询
     */
    public void demo9() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();
        // SQL:SELECT * FROM cst_customer c INNER JOIN cst_linkman l ON c.cust_id = l.lkm_cust_id;
        // HQL:内连接 from Customer c inner join c.linkMans

//        Query query = session.createQuery("from Customer c inner join  c.linkMans ");
//        List<Object[]> list = query.list();
//
//        for (Object[] objects : list) {
//            System.out.println(Arrays.toString(objects));
//        }

        // HQL:迫切内连接 其实就在普通的内连接inner join后添加一个关键字fetch. from Customer c inner join fetch c.linkMans
        // 通知hibernate，将另一个对象的数据封装到该对象中
        Query query = session.createQuery("select distinct c  from  Customer c inner join fetch  c.linkMans ");

        List<Customer> list = query.list();

        for (Customer customer : list) {
            System.out.println(customer);
            System.out.println(customer.getLinkMans().size());
        }


        tx.commit();
    }
}
