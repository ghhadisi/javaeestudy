package com.hss.hibernate.test.fetch;


import com.hss.hibernate.test.bean.LinkMan;
import com.hss.hibernate.test.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * many-to-one上的fetch和lazy测试
 *
 * fetch	：抓取策略，控制SQL语句格式。
     * select	：默认值，发送普通的select语句，查询关联对象。
     * join		：发送一条迫切左外连接。
 * lazy	：延迟加载，控制查询关联对象的时候是否采用延迟。
     * proxy	：默认值，proxy具体的取值，取决于另一端的<class>上的lazy的值。
     * false		：查询关联对象，不采用延迟。
     * no-proxy	：（不会使用）
 * 在实际开发中，一般都采用默认值。如果有特殊的需求，可能需要配置join。
 *
 */
public class Fetch2 {

    @Test
    /**
     * 默认值
     */
    public void demo1(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        LinkMan linkMan = session.get(LinkMan.class, 1l);// 发送一条查询联系人语句
        System.out.println(linkMan.getLkm_name());
        System.out.println(linkMan.getCustomer().getCust_name());// 发送一条select语句查询联系人所关联的客户

        tx.commit();
    }

    @Test
    /**
     * fetch="select" lazy="proxy"
     */
    public void demo2(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        LinkMan linkMan = session.get(LinkMan.class, 1l);// 发送一条查询联系人语句
        System.out.println(linkMan.getLkm_name());
        System.out.println(linkMan.getCustomer().getCust_name());// 发送一条select语句查询联系人所关联的客户

        tx.commit();
    }


    @Test
    /**
     * fetch="select" lazy="false"
     */
    public void demo3(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        LinkMan linkMan = session.get(LinkMan.class, 1l);// 发送一条查询联系人语句,发送一条select语句查询联系人所关联的客户
        System.out.println(linkMan.getLkm_name());
        System.out.println(linkMan.getCustomer().getCust_name());//

        tx.commit();
    }


    @Test
    /**
     * fetch="join" lazy=失效
     */
    public void demo4(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        LinkMan linkMan = session.get(LinkMan.class, 1l);// 发送一条迫切左外连接查询联系人所关联的客户。
        System.out.println(linkMan.getLkm_name());
        System.out.println(linkMan.getCustomer().getCust_name());//

        tx.commit();
    }
}
