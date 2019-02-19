package com.hss.mall.dao;

import com.hss.mall.domain.Product;
import com.hss.mall.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements IProductDao {
    @Override
    public List<Product> findNewProducts() throws SQLException {

        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

        return runner.query("select * from product where pflag=0 ORDER BY pdate DESC LIMIT 0, 9", new BeanListHandler<Product>(Product.class));
    }

    @Override
    public List<Product> findHotProducts() throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

        return runner.query("select * from product where pflag=0 and is_hot=1 ORDER BY pdate DESC LIMIT 0, 9", new BeanListHandler<Product>(Product.class));
    }

    @Override
    public Product findProductByPid(String pid) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

        return runner.query("select * from product where pid=?", new BeanHandler<Product>(Product.class),pid);

    }
}
