package com.jess.secondarymarket.dao;

import com.jess.secondarymarket.forms.UserOrderForm;
import com.jess.secondarymarket.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    Order selectByPrimaryKey(Integer id);

    List<Order> selectAll();

    int updateByPrimaryKey(Order record);

    List<UserOrderForm> selectByUserId(Long id);
}