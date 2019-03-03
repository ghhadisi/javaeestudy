package com.hss.mall.service;

import com.hss.mall.dao.CategoryDaoImpl;
import com.hss.mall.dao.ICategoryDao;
import com.hss.mall.domain.Category;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements ICategoryService{

    ICategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAllCats() throws SQLException {
        return categoryDao.findAllCats();
    }

    @Override
    public void saveCat(Category c) throws SQLException {
        categoryDao.saveCat(c);
    }

    @Override
    public void addCategory(Category c) throws SQLException {
        categoryDao.addCategory(c);
    }

    @Override
    public Category findCategory(String cid) throws SQLException {
        return categoryDao.findCategory(cid);
    }
}
