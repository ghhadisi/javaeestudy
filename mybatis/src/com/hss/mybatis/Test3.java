package com.hss.mybatis;

import com.hss.mybatis.bean.Order;
import com.hss.mybatis.bean.Order2;
import com.hss.mybatis.bean.QueryVo;
import com.hss.mybatis.bean.User;
import com.hss.mybatis.mapper.OrderMapper;
import com.hss.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test3 {
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
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> list = orderMapper.queryOrderAll();
        // 6. 打印结果
        for (Order item : list) {
            System.out.println(item);
        }
        // 7. 释放资源
        sqlSession.close();
    }

    //一对一
    @Test
    public void fun2(){
        // 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 执行SqlSession对象执行查询，获取结果User
        // 第一个参数是User.xml的statement的id，第二个参数是执行sql需要的参数；
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<Order2> list = orderMapper.queryOrderUserResultMap();
        // 6. 打印结果
        for (Order2 item : list) {
            System.out.println(item);
        }
        // 7. 释放资源
        sqlSession.close();
    }


}
