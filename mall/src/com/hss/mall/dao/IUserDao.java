package com.hss.mall.dao;

import com.hss.mall.domain.User;

import java.sql.SQLException;

public interface IUserDao {
    public boolean checkUserNameIsExist(String username)throws SQLException;


    public void register(User user)throws SQLException;

    public User userActive(String code)throws SQLException;

    public void updateUser(User user) throws SQLException;

    public User login(User user) throws SQLException;


}
