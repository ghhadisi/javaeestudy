package com.hss.mall.dao;

import com.hss.mall.domain.Order;
import com.hss.mall.domain.OrderItem;
import com.hss.mall.domain.User;
import java.sql.Connection;

import java.util.List;

public interface IOrderDao {
    void saveOrder(Connection conn, Order order)throws Exception;

    void saveOrderItem(Connection conn, OrderItem item)throws Exception;

    int getTotalRecords(User user)throws Exception;

    List findMyOrdersWithPage(User user, int startIndex, int pageSize)throws Exception;

    Order findOrderByOid(String oid)throws Exception;

    void updateOrder(Order order)throws Exception;

    List<Order> findAllOrders()throws Exception;

    List<Order> findAllOrders(String st)throws Exception;
}
