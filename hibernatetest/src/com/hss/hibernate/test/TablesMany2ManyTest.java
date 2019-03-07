package com.hss.hibernate.test;

import com.hss.hibernate.test.bean.Role;
import com.hss.hibernate.test.bean.SysUser;
import com.hss.hibernate.test.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TablesMany2ManyTest {
    public static void main(String[] args) {
        demo9();
    }

    public static void demo1(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        // 创建2个用户
        SysUser user1 = new SysUser();
        user1.setUser_name("赵洪");
        SysUser user2 = new SysUser();
        user2.setUser_name("李兵");

        // 创建3个角色
        Role role1 = new Role();
        role1.setRole_name("研发部");
        Role role2 = new Role();
        role2.setRole_name("市场部");
        Role role3 = new Role();
        role3.setRole_name("公关部");

        // 设置双向的关联关系:
        user1.getRoles().add(role1);
        user1.getRoles().add(role2);
        user2.getRoles().add(role2);
        user2.getRoles().add(role3);
        role1.getUsers().add(user1);
        role2.getUsers().add(user1);
        role2.getUsers().add(user2);
        role3.getUsers().add(user2);

        // 保存操作:多对多建立了双向的关系必须有一方放弃外键维护。
        // 一般是被动方放弃外键维护权。
        session.save(user1);
        session.save(user2);
        session.save(role1);
        session.save(role2);
        session.save(role3);

        tx.commit();

    }

    /**
     * 多对多的操作：
     * * 只保存一边是否可以？不可以，瞬时对象异常
     */
    public static void demo2(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        // 创建2个用户
        SysUser user1 = new SysUser();
        user1.setUser_name("赵洪");

        // 创建3个角色
        Role role1 = new Role();
        role1.setRole_name("研发部");

        // 设置双向的关联关系:
        user1.getRoles().add(role1);
        role1.getUsers().add(user1);
        // 只保存用户：
         session.save(user1);//object references an unsaved transient instance -
        session.save(role1);

        tx.commit();

    }

    /**
     * 多对多的级联保存：
     * * 保存用户级联保存角色。在用户的映射文件中配置。
     * * 在User.hbm.xml中的set上配置 cascade="save-update"
     */
    public static void demo3(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        // 创建2个用户
        SysUser user1 = new SysUser();
        user1.setUser_name("赵洪");

        // 创建3个角色
        Role role1 = new Role();
        role1.setRole_name("研发部");

        // 设置双向的关联关系:
        user1.getRoles().add(role1);
        role1.getUsers().add(user1);
        // 只保存用户：
        session.save(user1);

        tx.commit();

    }

    public static void demo4(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();


        // 创建2个用户
        SysUser user1 = new SysUser();
        user1.setUser_name("李兵");

        // 创建3个角色
        Role role1 = new Role();
        role1.setRole_name("公关部");

        // 设置双向的关联关系:
        user1.getRoles().add(role1);
        role1.getUsers().add(user1);
        // 只保存用户：
        session.save(role1);

        tx.commit();

    }

    /**
     * 多对多的级联删除：
     * * 删除用户级联删除角色
     * * 在User.hbm.xml中的set上配置 cascade="delete"
     */
    public static void demo5(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

// 查询1号用户:
        SysUser user  = session.get(SysUser.class, 1l);
        session.delete(user);


        tx.commit();

    }

    public static void demo6(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();


        // 查询2号角色:
        Role role  = session.get(Role.class, 2l);
        session.delete(role);

        tx.commit();

    }

    public static void demo7(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

// 给1号用户多选2号角色
        // 查询1号用户
        SysUser user  = session.get(SysUser.class, 1l);
        // 查询2号角色
        Role role = session.get(Role.class, 2l);
        user.getRoles().add(role);

        tx.commit();

    }

    public static void demo8(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

// 给2号用户将原有的2号角色改为3号角色
        // 查询2号用户
        SysUser user  = session.get(SysUser.class, 2l);
        // 查询2号角色
        Role role2 = session.get(Role.class, 2l);
        Role role3 = session.get(Role.class, 3l);
        user.getRoles().remove(role2);
        user.getRoles().add(role3);

        tx.commit();

    }

    public static void demo9(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        // 给2号用户删除1号角色
        // 查询2号用户
        SysUser user  = session.get(SysUser.class, 2l);
        // 查询2号角色
        Role role = session.get(Role.class, 3l);
        user.getRoles().remove(role);

        tx.commit();

    }

}
