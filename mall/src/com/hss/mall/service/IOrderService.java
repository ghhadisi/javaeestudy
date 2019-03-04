package com.hss.mall.service;

import com.hss.mall.domain.Order;
import com.hss.mall.domain.PageModel;
import com.hss.mall.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface IOrderService {
    void saveOrder(Order order)throws SQLException;

    PageModel findMyOrdersWithPage(User user, int curNum)throws Exception;

    Order findOrderByOid(String oid)throws Exception;

    void updateOrder(Order order)throws Exception;


    List<Order> findAllOrders()throws Exception;

    List<Order> findAllOrders(String st)throws Exception;
}
