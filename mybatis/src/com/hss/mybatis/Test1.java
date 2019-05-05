package com.hss.mybatis;

import com.hss.mybatis.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class Test1 {
    SqlSessionFactory sqlSessionFactory;
    @Before
    public void init(){
        // 1. 创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
// 2. 加载SqlMapConfig.xml配置文件
        try {
            InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");

            // 3. 创建SqlSessionFactory对象
             sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void fun1(){
        // 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 执行SqlSession对象执行查询，获取结果User
        // 第一个参数是User.xml的statement的id，第二个参数是执行sql需要的参数；
        Object user = sqlSession.selectOne("queryUserById", 1);
        // 6. 打印结果
        System.out.println(user);

        // 7. 释放资源
        sqlSession.close();
    }
    //5.6.3.selectList
    @Test
    public void fun2(){
        // 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 执行SqlSession对象执行查询，获取结果User
        // 第一个参数是User.xml的statement的id，第二个参数是执行sql需要的参数；
        List<Object> list  = sqlSession.selectList("queryUserByName", "%王%");
        // 6. 打印结果
        for (Object user : list) {
            System.out.println(user);
        }

        // 7. 释放资源
        sqlSession.close();
    }


    @Test
    public void fun3(){
        // 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 执行SqlSession对象执行查询，获取结果User
        // 第一个参数是User.xml的statement的id，第二个参数是执行sql需要的参数；
        List<Object> list  = sqlSession.selectList("queryUserByName2", "王");
        // 6. 打印结果
        for (Object user : list) {
            System.out.println(user);
        }

        // 7. 释放资源
        sqlSession.close();
    }


    @Test
    public void fun4(){
        // 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 执行SqlSession对象执行查询，获取结果User
        // 第一个参数是User.xml的statement的id，第二个参数是执行sql需要的参数；
        // 创建需要保存的User
        User user = new User();
        user.setUsername("张飞");
        user.setSex("1");
        user.setBirthday(new Date());
        user.setAddress("蜀国");
        sqlSession.insert("saveUser", user);
        // 需要进行事务提交
        sqlSession.commit();

        // 7. 释放资源
        sqlSession.close();
    }
    //5.7.4.mysql自增主键返回
    @Test
    public void fun5(){
        // 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 执行SqlSession对象执行查询，获取结果User
        // 第一个参数是User.xml的statement的id，第二个参数是执行sql需要的参数；
        // 创建需要保存的User
        User user = new User();
        user.setUsername("张飞3");
        user.setSex("1");
        user.setBirthday(new Date());
        user.setAddress("蜀国");
        int result = sqlSession.insert("saveUser2", user);
        System.out.println("result = "+result);
        System.out.println(user);

        // 需要进行事务提交
        sqlSession.commit();

        // 7. 释放资源
        sqlSession.close();
    }

    //5.7.5.Mysql使用 uuid实现主键
    @Test
    public void fun6(){
        // 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 执行SqlSession对象执行查询，获取结果User
        // 第一个参数是User.xml的statement的id，第二个参数是执行sql需要的参数；
        // 创建需要保存的User
        User user = new User();
        user.setUsername("张飞4");
        user.setSex("1");
        user.setBirthday(new Date());
        user.setAddress("蜀国");
        int result = sqlSession.insert("saveUser3", user);
        System.out.println("result = "+result);
        System.out.println(user);

        // 需要进行事务提交
        sqlSession.commit();

        // 7. 释放资源
        sqlSession.close();
    }

    //5.8.修改用户
    @Test
    public void fun7(){
        // 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 执行SqlSession对象执行查询，获取结果User
        // 第一个参数是User.xml的statement的id，第二个参数是执行sql需要的参数；
        // 创建需要保存的User
        User user = new User();
        user.setUsername("张飞41");
        user.setId(29);
        int result = sqlSession.insert("updateUserById", user);
        System.out.println(user);

        // 需要进行事务提交
        sqlSession.commit();

        // 7. 释放资源
        sqlSession.close();
    }


    //删除用户
    @Test
    public void fun8(){
        // 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 执行SqlSession对象执行查询，获取结果User
        // 第一个参数是User.xml的statement的id，第二个参数是执行sql需要的参数；
        // 创建需要保存的User
        int result = sqlSession.delete("deleteUserById", 29);
        System.out.println("result = "+result);

        // 需要进行事务提交
        sqlSession.commit();

        // 7. 释放资源
        sqlSession.close();
    }
}
