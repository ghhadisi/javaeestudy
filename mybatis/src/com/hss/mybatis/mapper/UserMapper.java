package com.hss.mybatis.mapper;

import com.hss.mybatis.bean.QueryVo;
import com.hss.mybatis.bean.User;

import java.util.List;

/*
* 6.4.Mapper动态代理方式
*
* Mapper接口开发方法只需要程序员编写Mapper接口（相当于Dao接口），由Mybatis框架根据接口定义创建接口的动态代理对象，代理对象的方法体同上边Dao接口实现类方法。

Mapper接口开发需要遵循以下规范：
1、Mapper.xml文件中的namespace与mapper接口的类路径相同。
2、Mapper接口方法名和Mapper.xml中定义的每个statement的id相同
3、Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql 的parameterType的类型相同
4、Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同
 * */
public interface UserMapper {

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    User queryUserById(int id);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    List<User> queryUserByUsername(String username);

    /**
     * 保存用户
     *
     * @param user
     */
    void saveUser(User user);

    List<User> queryUserByQueryVo(QueryVo queryVo);

    int queryUserCount();
}
