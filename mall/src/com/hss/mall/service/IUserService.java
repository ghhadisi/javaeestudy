package com.hss.mall.service;

import com.hss.mall.domain.User;

import java.sql.SQLException;

public interface IUserService {
    public boolean register(User user) throws SQLException;


    public boolean checkUserNameCanUse(String username) throws SQLException;


    public User userActive(String code) throws SQLException;

    void updateUser(User user)throws SQLException;

    public User login(User user) throws SQLException;

}
