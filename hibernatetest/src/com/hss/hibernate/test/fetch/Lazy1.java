package com.hss.hibernate.test.fetch;

import com.hss.hibernate.test.bean.Customer;
import com.hss.hibernate.test.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
/*
* 1.3.1.1什么是延迟加载
延迟加载：lazy（懒加载）。执行到该行代码的时候，不会发送语句去进行查询，在真正使用这个对象的属性的时候才会发送SQL语句进行查询。
1.3.1.2延迟加载的分类
类级别的延迟加载
指的是通过load方法查询某个对象的时候，是否采用延迟。session.load(Customer.class,1l);
类级别延迟加载通过<class>上的lazy进行配置，如果让lazy失效
将lazy设置为false
将持久化类使用final修饰
Hibernate. Initialize()
* */
public class Lazy1 {
    @Test
    /**
     * 类级别的延迟加载
     * * 在<class>的标签上配置的lazy
     */
    public void demo1(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Customer customer = session.load(Customer.class, 1l);
//        Hibernate.initialize(customer);
//lazy="false"
        System.out.println("#################################");
        System.out.println(customer);

        tx.commit();
    }


    /*
    *
    * 关联级别的延迟加载
指的是在查询到某个对象的时候，查询其关联的对象的时候，是否采用延迟加载。
Customer customer = session.get(Customer.class,1l);
customer.getLinkMans();----通过客户获得联系人的时候，联系人对象是否采用了延迟加载，称为是关联级别的延迟。
抓取策略往往会和关联级别的延迟加载一起使用，优化语句。
    * */
    @Test
    public void demo2(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Customer customer = session.load(Customer.class, 1l);
//        Hibernate.initialize(customer);
//lazy="false"
        System.out.println("#################################");
        System.out.println(customer.getCust_name());
        System.out.println("#################################");

        System.out.println(customer.getLinkMans().size());

        tx.commit();
    }
}
