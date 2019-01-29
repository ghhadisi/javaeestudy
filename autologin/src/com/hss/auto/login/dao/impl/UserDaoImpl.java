package com.hss.auto.login.dao.impl;

import java.sql.SQLException;

import com.hss.auto.login.bean.UserBean;
import com.hss.auto.login.dao.UserDao;
import com.hss.auto.login.utils.JDBCUtil02;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


public class UserDaoImpl implements UserDao {

	@Override
	public UserBean login(UserBean user) throws SQLException {
		
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		String sql = "select * from users where username = ? and password = ?";
		UserBean query = runner.query(sql, new BeanHandler<UserBean>(UserBean.class) , user.getUsername() , user.getPassword());
		return query;
	}

}
