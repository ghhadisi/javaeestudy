package com.hss.mybatis.mapper;

import com.hss.mybatis.bean.Order;
import com.hss.mybatis.bean.Order2;

import java.util.List;

public interface OrderMapper {
    List<Order> queryOrderAll();

    List<Order2> queryOrderUserResultMap();
}
