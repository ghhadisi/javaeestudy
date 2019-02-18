package com.hss.mall.dao;

import com.hss.mall.domain.Category;
import com.hss.mall.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements ICategoryDao {
    @Override
    public List<Category> findAllCats() throws SQLException {

        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

        return runner.query("select * from category", new BeanListHandler<Category>(Category.class));
    }

    @Override
    public void saveCat(Category c) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String[] params = {c.getCid(),c.getCname()};
        runner.update("insert into category values (?,?)", params);
    }
}
