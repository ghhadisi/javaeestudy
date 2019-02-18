package com.hss.mall.service;

import java.sql.SQLException;
import java.util.List;

import com.hss.mall.domain.Category;

public interface ICategoryService {

	List<Category> findAllCats()throws SQLException;

	void saveCat(Category c)throws SQLException;

}
