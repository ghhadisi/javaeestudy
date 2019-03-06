package com.hss.hibernate.test;

import com.hss.hibernate.test.bean.User;
import com.hss.hibernate.test.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Test {
/*    public static void main(String[] args) {
        //加载配置
        *//*默认回去src下 hibernate.cfg.xml 默认配置文件
        * 如果咬指定目录文件则传递文件路径到configure("路径" )中
        * *//*
        Configuration cfg = new Configuration().configure();
        //手动加载映射文件
//        cfg.addResource("com/hss/hibernate/test/bean/User.hbm.xml");
        //创建一个sessionfactory
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        *//*SessionFactory 特点:
            它是线程安全的,它的同一个实例能提供多个线程共享
            它是重量级的,不能随意的创建和销毁它的实例
        * *//*
        //创建session 对象  类似connection
        Session session = sessionFactory.openSession();

        *//*
        * session 是非线程安全的
        *
        * sessionFactory.openSession(); 直接创建一个session 需要手动关闭
        *
        * sessionFactory.getCurrentSession(); 将session与线程绑定 在提交或回滚时自动关闭

         * *//*
        //开启事务
        Transaction tx = session.beginTransaction();
        //执行相关操作
        User user = new User();
        user.setUsername("lisi");
        user.setPassword("123456");
        session.save(user);
        tx.commit();
        session.close();
    }*/

    public static void main(String[] args) {
        demo6();
    }

    public static void demo1(){
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();
        User user = new User();
        user.setUsername("zhaowu");
        user.setPassword("123465756");
        Serializable id = session.save(user);
        System.out.println(id);
        tx.commit();
        session.close();
    }

    public static void demo2(){
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();
/**
 * get方法
 * 	* 采用的是立即加载，执行到这行代码的时候，就会马上发送SQL语句去查询。
 *  * 查询后返回是真实对象本身。
 * 	* 查询一个找不到的对象的时候，返回null
 *
 * load方法
 * 	* 采用的是延迟加载（lazy懒加载），执行到这行代码的时候，不会发送SQL语句，当真正使用这个对象的时候才会发送SQL语句。
 *  * 查询后返回的是代理对象。javassist-3.18.1-GA.jar 利用javassist技术产生的代理。
 *  * 查询一个找不到的对象的时候，返回ObjectNotFoundException
 */
//        User user = session.get(User.class, 1);
        User user = session.load(User.class, 1);

        System.out.println(user);

        tx.commit();
        session.close();
    }

    public static void demo3(){
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();
// 直接创建对象，进行修改
		/*Customer customer = new Customer();
		customer.setCust_id(1l);
		customer.setCust_name("王聪");
		session.update(customer);*/

        // 先查询，再修改(推荐)  能级联删除
        User user = session.get(User.class, 3);
        user.setUsername("王小贱");
        session.update(user);
        tx.commit();
        session.close();
    }

    public static void demo4(){
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();
        User user = session.get(User.class, 4);
        session.delete(user);
        tx.commit();
        session.close();
    }

    public static void demo5(){
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();

        User user = new User();
        user.setId(4);
        user.setUsername("zhaowu");
        user.setPassword("123465756");
        session.saveOrUpdate(user);

        tx.commit();
        session.close();
    }

    public static void demo6(){
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();

        // 接收HQL：Hibernate QueryTest Language 面向对象的查询语言
     /*   QueryTest query = session.createQuery("from users");
        List<User> list = query.list();

        for (User customer : list) {
            System.out.println(customer);
        }
*/

        // 接收SQL：
        SQLQuery query = session.createSQLQuery("select * from users");
        List<Object[]> list = query.list();
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }

        tx.commit();
        session.close();
    }
}
