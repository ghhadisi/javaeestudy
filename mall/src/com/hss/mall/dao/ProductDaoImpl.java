package com.hss.mall.dao;

import com.hss.mall.domain.Product;
import com.hss.mall.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

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

    @Override
    public List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

        return runner.query("select * from product where cid=? limit ?,?",new BeanListHandler<Product>(Product.class), cid, startIndex, pageSize);
    }

    @Override
    public int findTotalRecords(String cid) throws Exception {

        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        Long num = (Long) runner.query("select count(*) from product where cid=?",new ScalarHandler(),cid);
        return num.intValue();
    }
}
