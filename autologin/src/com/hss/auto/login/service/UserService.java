package com.hss.auto.login.service;

import com.hss.auto.login.bean.UserBean;

import java.sql.SQLException;

public interface UserService {

    /**
     * 执行登录，并且返回该用户的所有信息
     * @param user 执行登录的用户信息
     * @return
     */
    UserBean login(UserBean user) throws SQLException;
}
