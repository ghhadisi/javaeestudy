package com.hss.mybatis;

import com.hss.mybatis.bean.OrderUser;
import com.hss.mybatis.bean.QueryVo;
import com.hss.mybatis.bean.User;
import com.hss.mybatis.bean.User2;
import com.hss.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Test2 {
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
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 6. 打印结果
        System.out.println(userMapper.queryUserById(1));

        // 7. 释放资源
        sqlSession.close();
    }


    @Test
    public void fun2(){
        // 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 执行SqlSession对象执行查询，获取结果User
        // 第一个参数是User.xml的statement的id，第二个参数是执行sql需要的参数；
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        QueryVo queryVo = new QueryVo();
        User user =new User();
        queryVo.setUser(user);
        user.setUsername("王");
        List<User> list  = userMapper.queryUserByQueryVo(queryVo);
        // 6. 打印结果
        for (User item : list) {
            System.out.println(item);
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
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 6. 打印结果
        System.out.println("count = "+userMapper.queryUserCount());

        // 7. 释放资源
        sqlSession.close();
    }

    @Test
    public void fun4(){
        // 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 执行SqlSession对象执行查询，获取结果User
        // 第一个参数是User.xml的statement的id，第二个参数是执行sql需要的参数；
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user =new User();
//        user.setSex("1");
        user.setUsername("张");
        List<User> list  = userMapper.queryUserByWhere(user);
        // 6. 打印结果
        for (User item : list) {
            System.out.println(item);
        }

        // 7. 释放资源
        sqlSession.close();
    }

    @Test
    public void testQueryUserByIds() {
// mybatis和spring整合，整合之后，交给spring管理
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        // 创建Mapper接口的动态代理对象，整合之后，交给spring管理
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 使用userMapper执行根据条件查询用户
        QueryVo queryVo = new QueryVo();
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(10);
        ids.add(24);
        queryVo.setIds(ids);

        List<User> list = userMapper.queryUserByIds(queryVo);

        for (User u : list) {
            System.out.println(u);
        }

        // mybatis和spring整合，整合之后，交给spring管理
        sqlSession.close();
    }


    @Test
    public void testQueryOrderUser(){
        // 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 执行SqlSession对象执行查询，获取结果User
        // 第一个参数是User.xml的statement的id，第二个参数是执行sql需要的参数；
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<OrderUser> list  = userMapper.queryOrderUser();
        // 6. 打印结果
        for (OrderUser item : list) {
            System.out.println(item);
        }

        // 7. 释放资源
        sqlSession.close();
    }

    @Test
    public void testQueryOrderUser2(){
        // 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 执行SqlSession对象执行查询，获取结果User
        // 第一个参数是User.xml的statement的id，第二个参数是执行sql需要的参数；
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User2> list  = userMapper.queryUserOrder2();
        // 6. 打印结果
        for (User2 item : list) {
            System.out.println(item);
        }

        // 7. 释放资源
        sqlSession.close();
    }
}
