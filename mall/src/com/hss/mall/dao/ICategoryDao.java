package com.hss.mall.dao;

import com.hss.mall.domain.Category;

import java.sql.SQLException;
import java.util.List;


public interface ICategoryDao {

	List<Category> findAllCats()throws SQLException;

	void saveCat(Category c)throws SQLException;

}
